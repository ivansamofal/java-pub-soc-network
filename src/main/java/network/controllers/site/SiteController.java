package network.controllers.site;

import network.dtos.UserDto;
import network.services.ProfileService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller("/")
public class SiteController {
    private final ProfileService profileService;

    public SiteController(ProfileService profileService) {
        this.profileService = profileService;
    }

//    //Логин
//    @RequestMapping(value = "/login", method = RequestMethod.POST)
//    public String create(@RequestBody ServiceRequestDto serviceDto, ModelMap model) throws Exception {
//        ServiceDto dbServiceDto = serviceService.save(serviceDto, getCurrentUserName(), null);
//        model.addAttribute("action", "create");
//        model.addAttribute("service", dbServiceDto);
//
//        return "service-form";
//    }
//
//    //Логоут
//    @RequestMapping(value = "/logout", method = RequestMethod.POST)
//    public String create(@RequestBody ServiceRequestDto serviceDto, ModelMap model) throws Exception {
//        ServiceDto dbServiceDto = serviceService.save(serviceDto, getCurrentUserName(), null);
//        model.addAttribute("action", "create");
//        model.addAttribute("service", dbServiceDto);
//
//        return "service-form";
//    }

    //Регистрация
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@RequestBody UserDto userDto, ModelMap model) throws Exception {
        UserDto dbServiceDto = profileService.save(userDto);
        model.addAttribute("action", "create");
        model.addAttribute("service", dbServiceDto);

        return "registration";
    }
}
