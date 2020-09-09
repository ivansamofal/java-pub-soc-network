package hello.repositories;

import hello.entities.Partner;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartnerRepository extends CrudRepository<Partner, Integer> {
    Partner findByAlias(String alias);
}
