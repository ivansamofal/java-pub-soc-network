package hello.repositories;

import hello.entities.Partner;
import hello.entities.Service;
import hello.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceRepository extends CrudRepository<Service, Integer> {
    Service findByUser(User user);
    List<Service> findAllByActive(boolean active);
}
