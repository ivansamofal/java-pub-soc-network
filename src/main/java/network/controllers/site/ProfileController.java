package network.controllers.site;

import network.dtos.UserDto;
import network.services.ProfileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Period;

@Controller
public class ProfileController {
    private final ProfileService profileService;

    Logger logger = LoggerFactory.getLogger(ProfileController.class);

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    //Страница одного профиля
    @PreAuthorize("hasAuthority('ROLE_USER')")
//    @Secured("ROLE__USER")
    @GetMapping("/profiles/{id}")
    String profile(ModelMap model, @PathVariable Integer id) throws Exception {
        try {
            UserDto userDto = profileService.findById(id);
            UserDto currentUserDto = profileService.findCurrent();
            model.addAttribute("user", userDto);
            model.addAttribute("currentUser", currentUserDto);
            Boolean hasLike = this.profileService.hasLike(currentUserDto, userDto);
            Boolean hasFriendship = this.profileService.hasFriendship(currentUserDto, userDto);
            System.out.println(hasLike);
            model.addAttribute("hasLike", hasLike);
            model.addAttribute("hasFriendship", hasFriendship);

            LocalDate dateOfBirth = LocalDate.of(2020, 9, 11);
            Period period = null;
            Integer diff = null;
            if (userDto.getDateOfBirth() != null) {
                period = Period.between(userDto.getDateOfBirth(), LocalDate.now());
                diff = Math.abs(period.getYears());
            }
            model.addAttribute("userAge", diff);

        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        return "profile";
    }

    //Список профилей
//    @PreAuthorize("hasAuthority('USERA')")
    @RequestMapping(value = "/profiles/list", method = RequestMethod.GET)
    public String getList(ModelMap model) {
        try {
            model.addAttribute("users", profileService.findAll());
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        return "profiles-list";
    }

    //Создание профиля
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@RequestBody UserDto userDto, ModelMap model) throws Exception {
        UserDto dbUserDto = profileService.save(userDto);
        model.addAttribute("action", "create");
        model.addAttribute("user", dbUserDto);

        return "create-profile";
    }

    //Редактирование профиля
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String edit(@RequestBody UserDto userDto, ModelMap model) throws Exception {
        UserDto dbUserDto = profileService.save(userDto);
        model.addAttribute("action", "edit");
        model.addAttribute("user", dbUserDto);

        return "edit-profile";
    }
}
