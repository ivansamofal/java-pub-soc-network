package hello.dtos.contractor.dineka;

import hello.entities.ContractorCheck;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DinekaCompanyDto extends AbstractCompanyDto {
    private String name;
    private String country_code;
    private String reg_number;
    private String dt_begin;
    private String dt_end;
    private String company_type;
    private String current_status;

    public DinekaCompanyDto(ContractorCheck entity) {
        this.name = entity.getName();
        this.country_code = entity.getCountryCode();
        this.reg_number = entity.getRegNumber();

        if (entity.getDtBegin() != null) {
            this.dt_begin = entity.getDtBegin().toString();
        }

        if (entity.getDtEnd() != null) {
            this.dt_end = entity.getDtEnd().toString();
        }

        this.company_type = entity.getCompanyType();
        this.current_status = entity.getCurrentStatus();
    }
}
