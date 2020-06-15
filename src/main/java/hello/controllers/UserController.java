package hello.controllers;

import hello.dtos.UserDto;
import hello.dtos.UserInputDto;
import hello.dtos.response.JsonResponse;
import hello.services.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user")
    public JsonResponse create(@RequestBody UserInputDto userInputDto) {
        JsonResponse response = new JsonResponse();
        try {
            this.userService.create(userInputDto);
            response.setData("Данные поставлены в очередь на создание пользователя");
            response.setMessage("Success");
        } catch (Exception e) {
            response.setMessage(e.getMessage());
        }

        return response;
    }
}
