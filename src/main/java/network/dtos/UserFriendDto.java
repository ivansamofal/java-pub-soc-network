package network.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;
import lombok.NoArgsConstructor;
import network.entities.UserFriend;
import network.entities.UserLike;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class UserFriendDto {
    private Integer idFrom;
    private Integer idTo;
    private String status;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    public UserFriendDto(UserFriend userFriend) {
        this.idFrom = (userFriend.getUserFrom() != null ? userFriend.getUserFrom().getId() : 0);
        this.idTo = userFriend.getUserTo() != null ? userFriend.getUserTo().getId() : 0;
        this.status = "Created";
    }
}
