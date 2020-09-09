package hello.dtos.contractor.dineka;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DinekaResponseDto extends AbstractResponseDto {
    private String status;
    private DinekaDataDto data;
}
