package hello.factories;

import hello.dtos.contractor.dineka.DinekaCompanyDto;
import hello.dtos.contractor.dineka.DinekaDataDto;
import hello.dtos.contractor.dineka.DinekaResponseDto;
import hello.entities.ContractorCheck;
import hello.enums.StatusEnum;

import java.util.ArrayList;
import java.util.List;

public class DinekaResponseDtoFactory {
    public static DinekaResponseDto create(List<ContractorCheck> checkList) {
        DinekaResponseDto dto = new DinekaResponseDto();
        DinekaDataDto dataDto = new DinekaDataDto();
        List<DinekaCompanyDto> list = new ArrayList<>();
        checkList.forEach(i -> {
            list.add(new DinekaCompanyDto(i));
        });

        dataDto.setCompanies(list);
        dataDto.setTotal_count(list.size());
        dto.setStatus(StatusEnum.SUCCESS_MESSAGE);
        dto.setData(dataDto);

        return dto;
    }
}
