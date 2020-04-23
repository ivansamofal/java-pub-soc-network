package hello.repositories;

import hello.entities.Row;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RowRepository extends CrudRepository<Row, Long> {
    Row findById(Integer id);
}
