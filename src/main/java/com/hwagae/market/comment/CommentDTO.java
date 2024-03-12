package com.hwagae.market.comment;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@ToString
public class CommentDTO {
    private Integer comment_num;
    private String comment_writer;
    private String comment_content;
    private Timestamp comment_date;
    private Integer qna_num;

    public static CommentDTO toCommentDTO(CommentEntity commentEntity,Integer qna_num){
        CommentDTO commentDTO=new CommentDTO();
        commentDTO.setComment_num(commentEntity.getCommentNum());
        commentDTO.setComment_writer(commentEntity.getCommentWriter());
        commentDTO.setComment_content(commentEntity.getCommentContent());
        commentDTO.setComment_date(commentEntity.getCommentDate());
        commentDTO.setQna_num(qna_num);
        return commentDTO;
    }
}
