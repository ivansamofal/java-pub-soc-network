package hello.repositories;

import hello.entities.Message;
import hello.entities.Row;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface MessageRepository extends CrudRepository<Message, Long> {
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update Message m set m.status = 2 where m.status = 1 and m.recipientUser.id = :recipientUserId and m.authorUser.id = :authorUserId")//todo
//    @Query("update Message m set m.status = 2 where m.status = 1")//todo
    void markAsRead(@Param("authorUserId") Integer authorUserId, @Param("recipientUserId") Integer recipientUserId);
//    public void markAsRead();

    List<Message> findAllByStatusAndAuthorUser_IdAndRecipientUser_Id(Integer status, Integer authorUserId, Integer recipientUserId);
}
