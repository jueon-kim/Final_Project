package com.hwagae.market.inquiry;

import com.hwagae.market.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InquiryRepository extends JpaRepository<InquiryEntity,Integer> {
    //SELECT * FROM hg.qna where user_num = '11';
        List<InquiryEntity> findAllByUserEntity(UserEntity userEntity);
}
