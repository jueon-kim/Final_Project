package com.hwagae.market.chat;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ChatDTO {

    int chatID;
    String fromID;
    String toID;
    String chatContent;
    String chatTime;


}
