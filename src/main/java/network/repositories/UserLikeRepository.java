package network.repositories;

import network.entities.User;
import network.entities.UserLike;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserLikeRepository extends CrudRepository<UserLike, Integer> {
    UserLike findByUserFromAndUserTo(User userFrom, User userTo);

    @Query("SELECT CASE WHEN count(ul) > 0 THEN true ELSE false END FROM user_likes ul WHERE ul.userFrom.id = ?1 and ul.userTo.id = ?2")
    Boolean existsByUserFromIdAndUserToId(Integer userFromId, Integer userToId);
}
