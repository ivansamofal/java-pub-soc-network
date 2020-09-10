package hello.dtos.services;

import lombok.Data;

@Data
public class ServiceRequestDto {
    private Boolean active;
    private Float price;
    private Integer rating;
//    private Integer viewsCount;
    private Integer categoryId;
    private String title;
}
