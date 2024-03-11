package com.hwagae.market.chat;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface ChatRepository extends JpaRepository<ChatEntity, Integer> {


    List<ChatEntity> findByFromIDAndToIDOrFromIDAndToIDOrderByChatTime(String fromID1, String toID1, String fromID2, String toID2);

/*
    List<ChatEntity> findLatestChatsByFromID(String userNick);
*/

/*    @Query("SELECT c FROM ChatEntity c WHERE (c.fromID = :userNick OR c.toID = :userNick) AND c.chatID = (SELECT MAX(cc.chatID) FROM ChatEntity cc WHERE cc.fromID = c.fromID AND cc.toID = c.toID)")
    List<ChatEntity> findLatestChatsByFromID(@Param("userNick") String userNick);*/

    @Query("SELECT c FROM ChatEntity c WHERE c.toID != :userNick AND c.chatID = (SELECT MAX(cc.chatID) FROM ChatEntity cc WHERE cc.fromID = c.fromID AND cc.toID = c.toID)")
    List<ChatEntity> findLatestChatsNotToUser(@Param("userNick") String userNick);

    @Query("SELECT c FROM ChatEntity c WHERE c.fromID = :userNick AND c.toID IN (SELECT DISTINCT c1.toID FROM ChatEntity c1) AND c.chatID = (SELECT MAX(cc.chatID) FROM ChatEntity cc WHERE cc.fromID = c.fromID AND cc.toID = c.toID)")
    List<ChatEntity> findLatestChatsByFromID(@Param("userNick") String userNick);

}