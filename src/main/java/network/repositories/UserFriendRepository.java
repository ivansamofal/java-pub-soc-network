package network.repositories;

import network.entities.User;
import network.entities.UserFriend;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserFriendRepository extends CrudRepository<UserFriend, Integer> {
    UserFriend findByUserFromAndUserTo(User userFrom, User userTo);

    @Query("SELECT CASE WHEN count(uf) > 0 THEN true ELSE false END FROM user_friends uf WHERE uf.userFrom.id = ?1 and uf.userTo.id = ?2")
    Boolean existsByUserFromIdAndUserToId(Integer userFromId, Integer userToId);
}
