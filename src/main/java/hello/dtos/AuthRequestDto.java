package hello.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AuthRequestDto {
    private String username;
    private String password;

    public AuthRequestDto(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
