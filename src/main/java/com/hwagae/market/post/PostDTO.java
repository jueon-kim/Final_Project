package com.hwagae.market.post;

import com.hwagae.market.file.FileEntity;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Column;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO {

    private Integer post_num;
    private String post_title;
    private String post_content;
    private Integer post_price;
    private String post_saleState;
    private String post_regdate;
    private String post_update;
    private String post_location;
    private String post_location2;
    private String post_way;
    private String post_shipAddress;
    private String post_address;

/*    private Integer post_hits;
    private Integer post_like;*/

    private Integer post_deliveryFee;
    private String post_productState;
    private Integer user_num;
    private Integer category_num;
    private Integer file_num;
    private Integer file_attached;

    /* 사진 첨부 */
    private List<MultipartFile> post_upLoadFile; //컨트롤러로 넘어올때 담는 용도
    private List<String> originalFileName; //진짜 파일이름
    private List<String> file_url; //서버에 전송할 파일 이름


    public static PostDTO toPostDTO(PostEntity postEntity) {
        PostDTO postDTO = new PostDTO();
        postDTO.setPost_num(postEntity.getPost_num());
        postDTO.setPost_title(postEntity.getPost_title());
        postDTO.setPost_content(postEntity.getPost_content());
        postDTO.setPost_price(postEntity.getPost_price());
        postDTO.setPost_saleState(postEntity.getPost_saleState());
        postDTO.setPost_regdate(postEntity.getPost_regdate());
        /*        postDTO.setPost_update(postEntity.getPost_update());*/
        postDTO.setPost_location(postEntity.getPost_location());
        postDTO.setPost_location2(postEntity.getPost_location2());
        postDTO.setPost_way(postEntity.getPost_way());
        postDTO.setPost_shipAddress(postEntity.getPost_shipAddress());
        postDTO.setPost_address(postEntity.getPost_address());
/*        postDTO.setPost_hits(postEntity.getPost_hits());
        postDTO.setPost_like(postEntity.getPost_like());*/
        postDTO.setPost_deliveryFee(postEntity.getPost_deliveryFee());
        postDTO.setPost_productState(postEntity.getPost_productState());
        postDTO.setUser_num(postEntity.getUser_num());
        postDTO.setCategory_num(postEntity.getCategory_num());

        if (postEntity.getFileAttached() == null || postEntity.getFileAttached() == 0) {
            postDTO.setFile_attached(0);
        } else {
            List<String> fileList = new ArrayList<>();
            postDTO.setFile_attached(postEntity.getFileAttached());
            for (FileEntity fileEntity : postEntity.getFileEntityList()) {
                fileList.add(fileEntity.getFileUrl());
            }
            postDTO.setFile_url(fileList);
        }
        return postDTO;
    }
}
//        if(postEntity.getFileAttached()==0){
//            postDTO.setFile_attached(postEntity.getFileAttached());
//        }else {
//            List<String> fileList = new ArrayList<>();
//            postDTO.setFile_attached(postEntity.getFileAttached());
//            for (FileEntity fileEntity : postEntity.getFileEntityList()) {
//                fileList.add(fileEntity.getFileUrl());
//            }
//            postDTO.setFile_url(fileList);
//        }
//        return postDTO;
//    }
