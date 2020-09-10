package hello.services;

import hello.dtos.services.ServiceDto;
import hello.dtos.services.ServiceRequestDto;
import hello.entities.User;
import hello.repositories.ServiceRepository;
import hello.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServiceService {

    private ServiceRepository serviceRepository;

    public ServiceService(ServiceRepository serviceRepository, UserRepository userRepository) {
        this.serviceRepository = serviceRepository;
        this.userRepository = userRepository;
    }

    private UserRepository userRepository;

    public List<ServiceDto> getList() {
        List<hello.entities.Service> services = serviceRepository.findAllByActive(true);

        return services.stream()
                .map(ServiceDto::new)
                .collect(Collectors.toList());
    }

    public ServiceDto save(ServiceRequestDto serviceDto, String username, Integer serviceId) throws Exception {
        hello.entities.Service service = null;
        if (serviceId != null) {
            service = serviceRepository.findById(serviceId).orElse(null);
        }

        if (service == null) {
            service = new hello.entities.Service();

            User user = userRepository.findByUsername(username);

            if (user == null) {
                throw new Exception("User not found!");
            }
            service.setUser(user);
            service.setViewsCount(0);
            service.setApprovalPassed(false);
            service.setRatingKeys("");
        }

        service.setPrice(serviceDto.getPrice());
        service.setActive(serviceDto.getActive());

        service.setCategoryId(serviceDto.getCategoryId());
        serviceRepository.save(service);
        System.out.println("SUCCESSFULLY SAVED");

        return new ServiceDto(service);
    }
}
