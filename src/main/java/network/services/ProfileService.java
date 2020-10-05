package network.services;

import lombok.NonNull;
import network.dtos.UserDto;
import network.dtos.UserFriendDto;
import network.dtos.UserLikeDto;
import network.entities.User;
import network.entities.UserFriend;
import network.entities.UserLike;
import network.enums.StatusEnum;
import network.repositories.UserFriendRepository;
import network.repositories.UserLikeRepository;
import network.repositories.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProfileService {
    private final UserRepository userRepository;
    private final UserLikeRepository userLikeRepository;
    private final UserFriendRepository userFriendRepository;

    public ProfileService(UserRepository userRepository, UserLikeRepository userLikeRepository, UserFriendRepository userFriendRepository) {
        this.userRepository = userRepository;
        this.userLikeRepository = userLikeRepository;
        this.userFriendRepository = userFriendRepository;
    }

    public UserDto findById(@NonNull Integer id) throws Exception {
        User user = this.userRepository.findById(id).orElse(null);

        if (user != null) {
            user.getLikes().forEach(i -> System.out.println(i.getId()));
            return new UserDto(user);
        }

        throw new Exception("User not found!");
    }

    public UserDto findCurrent() throws Exception {
        User currentUser = this.userRepository.findByUsername(this.getCurrentUsername());

        if (currentUser != null) {
            return new UserDto(currentUser);
        }

        throw new Exception("User not found!");
    }

    public List<UserDto> findAll() {
        List<User> users = this.userRepository.findAllByStatus(StatusEnum.ACTIVE);//todo
//        List<User> users = (List<User>)this.userRepository.findAll();

        return users.stream().map(UserDto::new).collect(Collectors.toList());
    }

//    @Transactional
    public UserDto save(@NonNull UserDto userDto) throws Exception {
        User user;

        if (userDto.getId() == null) {
            User dbUser = this.userRepository.findByUsername(userDto.getUsername());
            if (dbUser != null) {
                throw new Exception("UserName already has in db!");
            }

            user = new User();
            user.setEnabled(Boolean.TRUE);
            user.setStatus(StatusEnum.ACTIVE);
            user.setCreatedAt(LocalDateTime.now());

            if (userDto.getPassword() != null) {
                PasswordEncoder encoder = new BCryptPasswordEncoder();
                user.setPassword(encoder.encode(userDto.getPassword()));
            }
        } else {
            user = this.userRepository.findById(userDto.getId()).orElse(null);

            if (user == null) {
                throw new Exception("User not found!");
            }
        }

        user.setUsername(userDto.getUsername());
        user.setDateOfBirth(userDto.getDateOfBirth());

        this.userRepository.save(user);

        return new UserDto(user);
    }

    public Boolean hasLike(UserDto userDtoFrom, UserDto userDtoTo) {
        return this.userLikeRepository.existsByUserFromIdAndUserToId(userDtoFrom.getId(), userDtoTo.getId());
    }

    public Boolean hasFriendship(UserDto userDtoFrom, UserDto userDtoTo) {
        return this.userFriendRepository.existsByUserFromIdAndUserToId(userDtoFrom.getId(), userDtoTo.getId());
    }

    // Поставить / убрать лайк
    public UserLikeDto like(@NonNull Integer id) throws Exception {
        User userTo = this.userRepository.findById(id).orElse(null);

        if (userTo == null) {
            throw new Exception("User not found!");
        }

        User currentUser = this.userRepository.findByUsername(this.getCurrentUsername());

        UserLike userLike = this.userLikeRepository.findByUserFromAndUserTo(currentUser, userTo);

        if (userLike == null) {
            userLike = new UserLike();
            userLike.setUserFrom(currentUser);
            userLike.setUserTo(userTo);
            userLike.setCreatedAt(LocalDateTime.now());
            userLike = this.userLikeRepository.save(userLike);

            return new UserLikeDto(userLike);
        }

        this.userLikeRepository.delete(userLike);

        UserLikeDto userLikeDto = new UserLikeDto();
        userLikeDto.setIdFrom(currentUser.getId());
        userLikeDto.setIdTo(userTo.getId());
        userLikeDto.setStatus("Deleted");

        return userLikeDto;
    }

    // Добавить в друзья / удалить из друзей
    public UserFriendDto friendship(@NonNull Integer id) throws Exception {
        User userTo = this.userRepository.findById(id).orElse(null);

        if (userTo == null) {
            throw new Exception("User not found!");
        }

        User currentUser = this.userRepository.findByUsername(this.getCurrentUsername());
        UserFriend userFriend = this.userFriendRepository.findByUserFromAndUserTo(currentUser, userTo);

        if (userFriend == null) {
            userFriend = new UserFriend();
            userFriend.setUserTo(userTo);
            userFriend.setUserFrom(currentUser);
            userFriend.setCreatedAt(LocalDateTime.now());
            this.userFriendRepository.save(userFriend);

            return new UserFriendDto(userFriend);
        }

        this.userFriendRepository.delete(userFriend);
        UserFriendDto userFriendDto = new UserFriendDto();
        userFriendDto.setIdFrom(currentUser.getId());
        userFriendDto.setIdTo(userTo.getId());
        userFriendDto.setStatus("Deleted");

        return userFriendDto;
    }

    // Получение username текущего авторизованного пользователя
    public String getCurrentUsername() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();

        return authentication.getName();
    }
}
