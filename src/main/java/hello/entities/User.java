package hello.entities;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;

@Data
@Entity(name = "users")
//@Table(name = "users")
public class User implements Serializable, UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="users_id_seq")
    @SequenceGenerator(name="users_id_seq", sequenceName="users_id_seq", allocationSize=1)
    private Integer id;

    @Column(name = "username")
    private String username;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "auth_key", unique = true)
    @Length(max = 32)
    private String authKey;

    @Column(name = "enabled")
    private Boolean status;

    @Column(name = "password")
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

//    @Override
//    public String getPassword() {
//        return "password";
//    }

    @Override
    public boolean isAccountNonExpired() {
        return this.getStatus();
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.getStatus();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.getStatus();
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
