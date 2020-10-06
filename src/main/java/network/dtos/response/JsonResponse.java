package network.dtos.response;

import lombok.Data;

@Data
public class JsonResponse {
    private String message = "success";
    private Object data;
}
