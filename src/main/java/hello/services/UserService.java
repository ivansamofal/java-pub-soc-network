package hello.services;

import com.google.gson.Gson;
import hello.constants.KafkaTopic;
import hello.dtos.UserDto;
import hello.dtos.UserInputDto;
import hello.entities.User;
import hello.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import sun.security.provider.MD5;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class UserService {
    final private UserRepository userRepository;
    final private KafkaProducerService kafkaProducerService;

    public UserService(UserRepository userRepository, KafkaProducerService kafkaProducerService) {
        this.userRepository = userRepository;
        this.kafkaProducerService = kafkaProducerService;
    }

    public void create(UserInputDto userInputDto) {
        Gson gson = new Gson();
        String json = gson.toJson(userInputDto);
        kafkaProducerService.produce(KafkaTopic.USER, json);
    }

    public UserDto save(UserInputDto userInputDto) throws Exception {
        User user = new User();
        user.setCreatedAt(LocalDateTime.now());
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] array = new byte[32]; // length is bounded by 7
        new Random().nextBytes(array);
        String generatedString = new String(array, StandardCharsets.UTF_8);
        byte[] data = md.digest(array);
        System.out.println(generatedString);
        byte[] test = DigestUtils.md5Digest(data);
        System.out.println("test: " + new String(test));
        user.setAuthKey(generatedString);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(userInputDto.getPassword()));
        user.setUsername(userInputDto.getUsername());
        user.setStatus(true);
        System.out.println("data: " + new String(data));
        user = userRepository.save(user);

        UserDto outputUserDto = new UserDto();
        outputUserDto.setId(user.getId());
        outputUserDto.setUsername(user.getUsername());
        outputUserDto.setCreatedAt(user.getCreatedAt());

        return outputUserDto;
    }
}
