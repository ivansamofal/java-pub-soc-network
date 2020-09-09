package hello.factories;

import hello.dtos.contractor.dineka.AbstractCompanyDto;
import hello.dtos.contractor.dineka.DinekaCompanyDto;
import hello.entities.ContractorCheck;
import hello.entities.Partner;

import java.time.LocalDateTime;

public class ContractorCheckDinekaFactory {
    public static ContractorCheck create(DinekaCompanyDto dto, Partner partner) {
        ContractorCheck newCheck = new ContractorCheck();
        newCheck.setCompanyType(dto.getCompany_type());
        newCheck.setCountryCode(dto.getCountry_code());
        newCheck.setCreatedAt(LocalDateTime.now());
        newCheck.setUpdatedAt(LocalDateTime.now());
        if (partner != null) {
            newCheck.setPartner(partner);
        }

        if (dto.getDt_begin() != null) {
            newCheck.setDtBegin(java.time.LocalDate.parse(dto.getDt_begin()));
        }

        if (dto.getDt_end() != null) {
            newCheck.setDtEnd(java.time.LocalDate.parse(dto.getDt_end()));
        }

        newCheck.setName(dto.getName());
        newCheck.setRegNumber(dto.getReg_number());

        return newCheck;
    }
}
