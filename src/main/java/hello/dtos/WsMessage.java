package hello.dtos;

import lombok.Data;

@Data
public class WsMessage {
    private Integer id;
    private String from;
    private String text;
}
