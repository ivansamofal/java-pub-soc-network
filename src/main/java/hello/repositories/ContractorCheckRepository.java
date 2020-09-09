package hello.repositories;

import hello.entities.ContractorCheck;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ContractorCheckRepository extends CrudRepository<ContractorCheck, Integer> {
    List<ContractorCheck> findAllByRegNumber(String regNumber);
}
