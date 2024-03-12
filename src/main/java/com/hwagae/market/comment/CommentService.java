package com.hwagae.market.comment;

import com.hwagae.market.inquiry.InquiryEntity;
import com.hwagae.market.inquiry.InquiryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final InquiryRepository inquiryRepository;
    public Integer save(CommentDTO commentDTO){
        System.out.println("qna_num값= " + commentDTO.getQna_num());
        Optional<InquiryEntity> optionalInquiryEntity=inquiryRepository.findById(commentDTO.getQna_num());
        if(optionalInquiryEntity.isPresent()){
            InquiryEntity inquiryEntity=optionalInquiryEntity.get();
            CommentEntity commentEntity=CommentEntity.toSaveEntity(commentDTO,inquiryEntity);

            Integer result= commentRepository.save(commentEntity).getCommentNum();
            if(result!=null){
                if("admin".equals(commentDTO.getComment_writer())){
                    inquiryEntity.setQnaStatus(1);
                    inquiryRepository.save(inquiryEntity);
                }
            }
            return result;
        }else{
            return null;
        }
    }

    public List<CommentDTO> findAll(Integer qna_num){
            InquiryEntity inquiryEntity=inquiryRepository.findById(qna_num).get();

            List<CommentEntity> commentEntityList=commentRepository.findAllByInquiryEntityOrderByCommentNumDesc(inquiryEntity);

            List<CommentDTO> commentDTOList=new ArrayList<>();
            for(CommentEntity commentEntity: commentEntityList){
                CommentDTO commentDTO=CommentDTO.toCommentDTO(commentEntity,qna_num);
                commentDTOList.add(commentDTO);
            }
            return commentDTOList;
    }

}
