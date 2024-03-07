package com.hwagae.market.chat;

import com.hwagae.market.user.UserDTO;
import com.hwagae.market.user.UserEntity;
import com.hwagae.market.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ChatController {

    private final ChatRepository chatRepository; // ChatEntity를 저장하고 불러오기 위한 Repository
    private final ChatService chatService;
    private final UserRepository userRepository;
    @Autowired
    public ChatController(ChatRepository chatRepository, ChatService chatService, UserRepository userRepository) {
        this.chatRepository = chatRepository;
        this.chatService = chatService;
        this.userRepository = userRepository;
    }

    @GetMapping("/chat/{userNum}")
    public String chatPage(@PathVariable("userNum") Integer userNum, Model model) {

        UserEntity userEntity = userRepository.findByUserNum(userNum);
        if (userEntity != null) {
            // userEntity 닉네임 추출하여 모델에 추가
            model.addAttribute("toID", userEntity.getUserNick());
        }
        System.out.println(" ' " + model + " ' "+"과의 채팅방으로 이동");
        return "views/user/chat2";
    }



    @PostMapping("/chat/send")
    @ResponseBody
    public ChatDTO sendMessage(@ModelAttribute ChatDTO chatDTO) {
        chatService.saveChatMessage(chatDTO);
        System.out.println("전송 성공 " + chatDTO);
        return chatDTO;
    }




/*    @GetMapping("/chat/list")
    @ResponseBody
    public List<ChatEntity> getChatList(@RequestParam("toID") String toID, @RequestParam("fromID") String fromID) {
        System.out.println("받는사람 = " + toID);
        // 세션에 저장된 사용자의 user_num을 가져옴
        System.out.println("보낸사람 = " + fromID);
        // 채팅 데이터베이스에서 해당하는 채팅 리스트를 가져옴
        List<ChatEntity> chatEntities = chatRepository.findByFromIDAndToIDOrFromIDAndToIDOrderByChatTime(fromID, toID, toID, fromID);
        System.out.println("채팅리스트 = " + chatEntities);

        return chatEntities;
    }*/


    @PostMapping("/chat/list")
    @ResponseBody
    public List<Map<String, Object>> getChatList(@RequestParam("toID") String toID, @RequestParam("fromID") String fromID) {
        List<ChatEntity> chatEntities = chatRepository.findByFromIDAndToIDOrFromIDAndToIDOrderByChatTime(fromID, toID, toID, fromID);
        System.out.println("chatEntities = " + chatEntities);

        List<Map<String, Object>> chatList = new ArrayList<>();
        for (ChatEntity chatEntity : chatEntities) {
            Map<String, Object> chatMap = new HashMap<>();
            chatMap.put("chatID", chatEntity.getChatID());
            chatMap.put("fromID", chatEntity.getFromID());
            chatMap.put("toID", chatEntity.getToID());
            chatMap.put("chatContent", chatEntity.getChatContent());
            chatMap.put("chatTime", chatEntity.getChatTime());
            chatList.add(chatMap);
        }

        return chatList;
    }

}
