package network.controllers.rest;

import network.dtos.UserDto;
import network.dtos.UserFriendDto;
import network.dtos.UserLikeDto;
import network.dtos.response.JsonResponse;
import network.services.ProfileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.annotation.Secured;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@RestController(value = "/api")
public class ProfileController {
    private final ProfileService profileService;
    private final Logger logger = LoggerFactory.getLogger(ProfileController.class);

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    //Регистрация
    //    @PreAuthorize("hasAuthority('USER')")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public JsonResponse create(@RequestBody UserDto userDto) throws Exception {

        JsonResponse response = new JsonResponse();
        try {
            UserDto dbServiceDto = profileService.save(userDto);
            response.setData(dbServiceDto);
        } catch (Exception e) {
            logger.error(e.getMessage());
            response.setMessage(e.getMessage());
        }

        return response;
    }

//    @PostMapping("/registration")
//    public String doRegister(@RequestBody UserDto userDto) {
//        String encodedPassword  = passwordEncoder.encode(userDto.getPassword1());
//
//        User user = new User();
//        user.setEnabled(Boolean.TRUE);
//        user.setPassword(encodedPassword);
//        user.setUsername(userDto.getUsername());
//
//        UserAuthority boardAuthority = new UserAuthority();
//        boardAuthority.setAuthority("BOARD");
//        boardAuthority.setUser(user);
//        user.getAuthorities().add(boardAuthority);
//        userRepository.save(user);
//
//        return "register-success";
//    }

    // Поставить / убрать лайк
//    @PreAuthorize("hasAuthority('USER')")
    @GetMapping(value = "/users/like/{id}")
    public JsonResponse like(@PathVariable Integer id) {
        JsonResponse response = new JsonResponse();
        try {
            UserLikeDto userLikeDto = this.profileService.like(id);
            response.setData(userLikeDto);
        } catch (Exception e) {
            logger.error(e.getMessage());
            response.setMessage(e.getMessage());
        }

        return response;
    }

    // Добавить в друзья / удалить из друзей
//    @PreAuthorize("hasAuthority('USER')")
    @GetMapping(value = "/users/friend/{id}")
    public JsonResponse friendship(@PathVariable Integer id) {
        JsonResponse response = new JsonResponse();
        try {
            UserFriendDto userFriendDto = this.profileService.friendship(id);
            response.setData(userFriendDto);
        } catch (Exception e) {
            logger.error(e.getMessage());
            response.setMessage(e.getMessage());
        }

        return response;
    }

    @GetMapping(value = "/users/test")
    public JsonResponse test() {
        JsonResponse response = new JsonResponse();
        try {
            response.setData(profileService.findAll());
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        return response;
    }
}
