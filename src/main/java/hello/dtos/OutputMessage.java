package hello.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class OutputMessage {
    private WsMessage message;
    private String from;
    private String text;

    public OutputMessage(WsMessage message, String from, String text) {
        this.message = message;
        this.from = from;
        this.text = text;
    }
}
