package hello.services.contractor;

import hello.dtos.contractor.dineka.AbstractResponseDto;

public interface ContractorServiceInterface {
    AbstractResponseDto getInfo(String regNumber) throws Exception;
}
