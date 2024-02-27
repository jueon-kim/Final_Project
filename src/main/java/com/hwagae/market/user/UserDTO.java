package com.hwagae.market.user;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDTO {

    private Integer user_num;
    private String user_id;
    private String user_pw;
    private String user_nick;
    private String user_phone;
    private String user_email;
    private String user_birth;
    private String user_name;
    private String user_role;
    private String user_joindate;
    private String user_photo;
    private MultipartFile upLoadFile;
    private String user_place;




    public static UserDTO toUserDTO(UserEntity userEntity) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUser_num(userEntity.getUserNum());
        userDTO.setUser_id(userEntity.getUserId());
        userDTO.setUser_pw(userEntity.getUserPw());
        userDTO.setUser_nick(userEntity.getUserNick());
        userDTO.setUser_phone(userEntity.getUserPhone());
        userDTO.setUser_email(userEntity.getUserEmail());
        userDTO.setUser_birth(userEntity.getUserBirth());
        userDTO.setUser_photo(userEntity.getUserPhoto());
        userDTO.setUser_name(userEntity.getUserName());
        userDTO.setUser_joindate(userEntity.getUserJoindate());
        userDTO.setUser_place(userEntity.getUserPlace());
        return userDTO;
    }

}
