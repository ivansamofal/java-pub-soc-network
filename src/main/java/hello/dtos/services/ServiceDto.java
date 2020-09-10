package hello.dtos.services;

import hello.dtos.UserDto;
import hello.entities.Service;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ServiceDto {

    private boolean active;
    private UserDto user;
    private Float price;
    private Integer rating;
    private Integer viewsCount;
//    private Category category;//todo

    public ServiceDto(Service service) {
        this.user = new UserDto(service.getUser());
        this.price = service.getPrice();
        this.rating = service.getRating();
        this.viewsCount = service.getViewsCount();
        this.active = service.getActive();
    }
}
