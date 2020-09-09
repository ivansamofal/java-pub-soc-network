package hello.services.contractor.seldon;

import hello.dtos.contractor.dineka.AbstractResponseDto;
import hello.services.contractor.ContractorServiceInterface;
import org.springframework.stereotype.Service;

@Service
public class SeldonService implements ContractorServiceInterface {
    @Override
    public AbstractResponseDto getInfo(String regNumber) throws Exception {
        return null;
    }
}
