package com.hwagae.market.comment;

import com.hwagae.market.inquiry.InquiryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<CommentEntity,Integer> {

    List<CommentEntity> findAllByInquiryEntityOrderByCommentNumDesc(InquiryEntity inquiryEntity);
}
