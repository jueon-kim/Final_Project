package com.hwagae.market.user;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userNum;

    @Column(nullable = false, unique = true)
    private String userId;

    @Column(nullable = false)
    private String userPw;

    @Column(nullable = false, unique = true)
    private String userNick;

    @Column(nullable = false, unique = true)
    private String userPhone;

    @Column(nullable = false, unique = true)
    private String userEmail;

    @Column(nullable = false)
    private String userBirth;

    @Column(nullable = false)
    private String userPhoto;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private String userJoindate;

    @Column
    private int fileAttached;

    public static UserEntity toUserEntity(UserDTO userDTO) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUserNum(userDTO.getUser_num());
        userEntity.setUserId(userDTO.getUser_id());
        userEntity.setUserPw(userDTO.getUser_pw());
        userEntity.setUserNick(userDTO.getUser_nick());
        userEntity.setUserPhone(userDTO.getUser_phone());
        userEntity.setUserEmail(userDTO.getUser_email());
        userEntity.setUserBirth(userDTO.getUser_birth());
        userEntity.setUserPhoto(userDTO.getUser_photo());
        userEntity.setUserName(userDTO.getUser_name());
        userEntity.setUserJoindate(userDTO.getUser_joindate());
        return userEntity;
    }

    public static UserEntity toUserUpdateEntity(UserDTO userDTO) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUserNum(userDTO.getUser_num());
        userEntity.setUserId(userDTO.getUser_id());
        userEntity.setUserPw(userDTO.getUser_pw());
        userEntity.setUserNick(userDTO.getUser_nick());
        userEntity.setUserPhone(userDTO.getUser_phone());
        userEntity.setUserEmail(userDTO.getUser_email());
        userEntity.setUserBirth(userDTO.getUser_birth());
        userEntity.setUserPhoto(userDTO.getUser_photo());
        userEntity.setUserName(userDTO.getUser_name());
        userEntity.setUserJoindate(userDTO.getUser_joindate());
        userEntity.setFileAttached(0);
        return userEntity;
    }
}
