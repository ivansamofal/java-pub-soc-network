package hello.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class OutputMarkAsReadMessageDto {
    private String from;
    private String to;

    public OutputMarkAsReadMessageDto(String from, String to) {
        this.from = from;
        this.to = to;
    }
}
