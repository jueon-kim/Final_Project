package com.hwagae.market.chat;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Getter
@Setter
@Table(name = "chat")
public class ChatEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer chatID;

    @Column
    private String fromID;

    @Column
    private String toID;

    @Column
    private String chatContent;

    @Column
    private String chatTime;


}
