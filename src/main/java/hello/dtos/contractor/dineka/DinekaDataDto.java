package hello.dtos.contractor.dineka;

import lombok.Data;

import java.util.List;

@Data
public class DinekaDataDto {
    private long total_count;
    private List<DinekaCompanyDto> companies;
}
