package network.entities;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@Entity(name = "user_likes")
public class UserLike implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name="user_id_from")
    private User userFrom;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name="user_id_to")
    private User userTo;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
