package hello.entities;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity(name = "partners")
public class Partner implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="partners_id_seq")
    @SequenceGenerator(name="partners_id_seq", sequenceName="partners_id_seq", allocationSize=1)
    private Integer id;

    private String alias;
    private String logo;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
