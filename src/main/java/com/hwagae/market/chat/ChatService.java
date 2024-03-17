package com.hwagae.market.chat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class ChatService {

    private final ChatRepository chatRepository;

    @Autowired
    public ChatService(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }

    public void saveChatMessage(ChatDTO chatDTO) {

        // ChatDTO에서 ChatEntity로 변환하여 저장합니다.
        ChatEntity chatEntity = new ChatEntity();
        chatEntity.setFromID(chatDTO.getFromID());
        chatEntity.setToID(chatDTO.getToID());
        chatEntity.setChatContent(chatDTO.getChatContent());
        chatEntity.setChatTime(chatDTO.getChatTime());

        // 저장
        chatRepository.save(chatEntity);
    }


    public List<ChatEntity> getChatListByID(String fromID, String toID) {
        return chatRepository.findByFromIDAndToIDOrFromIDAndToIDOrderByChatTime(fromID, toID, toID, fromID);
    }

}
