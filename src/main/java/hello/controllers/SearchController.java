package hello.controllers;

import hello.dtos.services.ServiceDto;
import hello.dtos.services.ServiceRequestDto;
import hello.services.ServiceService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
class SearchController {
    final private ServiceService serviceService;

    public SearchController(ServiceService serviceService) {
        this.serviceService = serviceService;
    }

    @PreAuthorize("hasAuthority('USER')")
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String getList(ModelMap model) throws Exception {
        model.addAttribute("services", serviceService.getList());

        return "search";
    }

    @PreAuthorize("hasAuthority('USER')")
    @RequestMapping(value = "/services", method = RequestMethod.POST)
    public String create(@RequestBody ServiceRequestDto serviceDto, ModelMap model) throws Exception {
        ServiceDto dbServiceDto = serviceService.save(serviceDto, getCurrentUserName(), null);
        model.addAttribute("action", "create");
        model.addAttribute("service", dbServiceDto);

        return "service-form";
    }

    @PreAuthorize("hasAuthority('USER')")
    @RequestMapping(value = "/services/{id}", method = RequestMethod.POST)
    public String update(@RequestBody ServiceRequestDto serviceRequestDto, ModelMap model, @PathVariable Integer id) throws Exception {
        ServiceDto dbServiceDto = serviceService.save(serviceRequestDto, getCurrentUserName(), id);
        model.addAttribute("action", "update");
        model.addAttribute("service", dbServiceDto);

        return "service-form";
    }

    private String getCurrentUserName() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        return authentication.getName();
    }
}