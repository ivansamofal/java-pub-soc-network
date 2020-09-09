package hello.entities;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Cacheable
@Entity(name = "contractor_checks")
public class ContractorCheck implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="contractor_checks_id_seq")
    @SequenceGenerator(name="contractor_checks_id_seq", sequenceName="contractor_checks_id_seq", allocationSize=1)
    private Integer id;

    private String name;
    private String regNumber;

    @OneToOne(fetch = FetchType.EAGER)
    private Partner partner;

    @Length(max = 10)
    private String countryCode;
    private String companyType;
    private String currentStatus;

    @Column(name = "dt_begin")
    private LocalDate dtBegin;

    @Column(name = "dt_end")
    private LocalDate dtEnd;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
