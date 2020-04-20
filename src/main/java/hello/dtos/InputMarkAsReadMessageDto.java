package hello.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class InputMarkAsReadMessageDto {
    private String from;
    private String to;

    public InputMarkAsReadMessageDto(String from, String to) {
        this.from = from;
        this.to = to;
    }
}
