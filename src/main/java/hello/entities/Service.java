package hello.entities;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity(name = "services")
public class Service implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="services_id_seq")
    @SequenceGenerator(name="services_id_seq", sequenceName="services_id_seq", allocationSize=1)
    private Integer id;

    private Float price;
//    private Currency currency;//todo

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;
//    private City city;//todo
    private Integer rating;
    private Boolean active;
    @Column(name = "view_count")
    private Integer viewsCount;
    private Integer status;
    private String ratingKeys;
    private Boolean approvalPassed;

    @Column(name = "field_of_activity_id")
    private Integer categoryId;//todo relation with category

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    public void validate() {
        System.out.println("VALIDATION");
    }
}
