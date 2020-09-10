package hello.controllers;

import hello.dtos.contractor.dineka.DinekaCompanyDto;
import hello.dtos.contractor.dineka.DinekaResponseDto;
import hello.services.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
class ContractorController {
    final private ContractorService contractorService;

    public ContractorController(ContractorService contractorService) {
        this.contractorService = contractorService;
    }

    @PreAuthorize("hasAuthority('USER')")
    @RequestMapping(value = "/dineka2/{regNumber}", method = RequestMethod.GET)
    public String printHello(ModelMap model, @PathVariable String regNumber) throws Exception {
        DinekaResponseDto responseDto = contractorService.getDinekaData(regNumber);
        List<DinekaCompanyDto> companies =  responseDto.getData().getCompanies();
        model.addAttribute("message", "Hello Spring MVC Framework!");
        model.addAttribute("dto", responseDto);
        model.addAttribute("companies", companies);

        return "main";
    }
}