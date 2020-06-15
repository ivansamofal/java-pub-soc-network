package hello.dtos.response;

import lombok.Data;

@Data
public class JsonResponse {
    private String message;
    private Object data;
}
