package hello.entities;

import hello.annotations.InjectRandomInt;
import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity(name = "rows")
//@Table(name = "rows")
public class Row implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="rows_id_seq")
    @SequenceGenerator(name="rows_id_seq", sequenceName="rows_id_seq", allocationSize=1)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "created_at")
    private LocalDateTime created_at;
}
