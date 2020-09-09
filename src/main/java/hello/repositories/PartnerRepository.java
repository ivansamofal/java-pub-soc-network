package hello.repositories;

import hello.entities.ContractorCheck;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContractorCheckRepository extends CrudRepository<ContractorCheck, Integer> {
    ContractorCheck findByRegNumber(String regNumber);
}
