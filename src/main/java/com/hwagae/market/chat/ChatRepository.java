package com.hwagae.market.chat;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ChatRepository extends JpaRepository<ChatEntity, Integer> {


    List<ChatEntity> findByFromIDAndToIDOrFromIDAndToIDOrderByChatTime(String fromID1, String toID1, String fromID2, String toID2);
}