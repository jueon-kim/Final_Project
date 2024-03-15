package com.hwagae.market.restrictedUser;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ResUserDTO {
    private Integer res_num;
    private Integer res_unum;
    private String res_uname;
    private String res_uphone;
    private String res_uid;
    private String res_account;
    private String res_reason;

    public String getRes_uid() {
        return res_uid;
    }

    public void setRes_uid(String res_uid) {
        this.res_uid = res_uid;
    }

    public String getRes_uname() {
        return res_uname;
    }

    public void setRes_uname(String res_uname) {
        this.res_uname = res_uname;
    }

    public String getRes_uphone() {
        return res_uphone;
    }

    public void setRes_uphone(String res_uphone) {
        this.res_uphone = res_uphone;
    }
    public String getRes_account() {
        return res_account;
    }

    public void setRes_account(String res_account) {
        this.res_account = res_account;
    }



    private static ResUserDTO toResUserDTO(ResUserEntity resUserEntity){
            ResUserDTO resUserDTO=new ResUserDTO();
            resUserDTO.setRes_num(resUserEntity.getResNum());
            resUserDTO.setRes_reason(resUserEntity.getResReason());
            resUserDTO.setRes_unum(resUserEntity.getUserEntity().getUserNum());
            resUserDTO.setRes_uname(resUserEntity.getUserEntity().getUserName());
            resUserDTO.setRes_uphone(resUserEntity.getUserEntity().getUserPhone());
            resUserDTO.setRes_uid(resUserEntity.getUserEntity().getUserId());
            resUserDTO.setRes_account(resUserEntity.getResAccount());
            return resUserDTO;
    }


}
