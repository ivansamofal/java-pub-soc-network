package network.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity(name = "user_friends")
public class UserFriend implements Serializable {
    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="user_friends_id_seq")
    @GeneratedValue(strategy=GenerationType.AUTO)
//    @SequenceGenerator(name="user_friends_id_seq", sequenceName="user_friends_id_seq", allocationSize=1)
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
