package hello.services;

import hello.dtos.contractor.dineka.DinekaResponseDto;
import hello.services.contractor.dineka.DinekaService;
import org.springframework.stereotype.Service;

@Service
public class ContractorService {
    private final DinekaService dinekaService;

    public ContractorService(DinekaService dinekaService) {
        this.dinekaService = dinekaService;
    }

    public DinekaResponseDto getDinekaData(String regNumber) throws Exception {
        return dinekaService.getInfo(regNumber);
    }
}
