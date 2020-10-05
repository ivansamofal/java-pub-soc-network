package network.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;
import lombok.NoArgsConstructor;
import network.entities.User;
import network.entities.UserLike;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class UserLikeDto {
    private Integer idFrom;
    private Integer idTo;
    private String status;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    public UserLikeDto(UserLike userLike) {
        this.idFrom = (userLike.getUserFrom() != null ? userLike.getUserFrom().getId() : 0);
        this.idTo = userLike.getUserTo() != null ? userLike.getUserTo().getId() : 0;
        this.status = "Created";
    }
}
