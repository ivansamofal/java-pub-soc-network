package hello.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AuthResponseDto {
    private String jwt;

    public AuthResponseDto(String jwt) {
        this.jwt = jwt;
    }
}
