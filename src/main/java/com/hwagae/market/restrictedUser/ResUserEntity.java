package com.hwagae.market.restrictedUser;

import com.hwagae.market.report.ReportEntity;
import com.hwagae.market.user.UserEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "restricted_user")
public class ResUserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer resNum;

    @Column
    private String resReason;

    @Column(name = "user_num", insertable = false, updatable = false)
    private Integer resUnum;

    @Column(name="user_name")
    private String resUname;

    @Column(name="user_id")
    private String resUid;

    @Column(name="user_phone")
    private String resUphone;

    @Column
    private String resAccount;

    @Column
    private Integer reportCount;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_num")
    private UserEntity userEntity;

    public static ResUserEntity toSaveEntity(ResUserDTO resUserDTO,UserEntity userEntity){
        ResUserEntity resUserEntity=new ResUserEntity();
        resUserEntity.setResReason(resUserDTO.getRes_reason());
        resUserEntity.setResAccount(resUserDTO.getRes_account());
        resUserEntity.setUserEntity(userEntity); // 기존의 UserEntity 객체 설정

        return resUserEntity;
    }

}