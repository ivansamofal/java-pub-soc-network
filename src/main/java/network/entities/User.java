package network.entities;

import network.entities.Role;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@Entity(name = "users")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "status")
    private Integer status;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "auth_key", unique = true)
    @Length(max = 32)
    private String authKey;

    @Column(name = "password")
    private String password;

    private boolean enabled;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;

    @ManyToMany(targetEntity = UserLike.class)
    private Set<UserLike> likes;

    @ManyToMany(targetEntity = UserFriend.class)
    private Set<UserFriend> friends;
}
