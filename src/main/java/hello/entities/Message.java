package hello.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "messages")
public class Message implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="messages_id_seq")
    @SequenceGenerator(name="messages_id_seq", sequenceName="messages_id_seq", allocationSize=1)
    private Integer id;

    @JoinColumn(name = "author_user_id")
    @OneToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    private User authorUser;

    @JoinColumn(name = "recipient_user_id")
    @OneToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    private User recipientUser;

    @Column(name = "text")
    private String text;

    private Integer status;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
