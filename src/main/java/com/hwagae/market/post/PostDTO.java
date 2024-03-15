package com.hwagae.market.post;

import com.hwagae.market.file.FileEntity;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Column;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
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
    private Integer post_hits;
    private Integer post_like;
    private Integer post_deliveryFee;
    private String post_productState;
    private Integer user_num;
    private Integer category_num;
    private Integer file_num;
    private Integer file_attached;


    // PostDTO 클래스에 등록 날짜 필드 추가
    private LocalDate postRegdate;

    // PostDTO 클래스에 게시물 등록일로부터 지난 시간을 표시하는 필드 추가
    private String timeAgo;

    /* 사진 첨부 */
    private List<MultipartFile> post_upLoadFile; //컨트롤러로 넘어올때 담는 용도
    private List<String> originalFileName; //진짜 파일이름
    private List<String> file_url; //서버에 전송할 파일 이름


    public void calculateTimeAgo() {
        LocalDate currentDate = LocalDate.now();
        LocalDate regDate = LocalDate.parse(this.post_regdate, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        long daysBetween = ChronoUnit.DAYS.between(regDate, currentDate);
        if (daysBetween == 0) {
            this.timeAgo = "오늘";
        } else if (daysBetween == 1) {
            this.timeAgo = "어제";
        } else {
            this.timeAgo = daysBetween + "일 전";
        }
    }

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
        postDTO.setPost_hits(postEntity.getPost_hits());
        postDTO.setPost_like(postEntity.getPost_like());
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

        // 게시물 등록일로부터 지난 시간 계산
        postDTO.calculateTimeAgo();

        return postDTO;
    }
}





//    public static PostDTO toPostDTO(PostEntity postEntity) {
//        PostDTO postDTO = new PostDTO();
//        postDTO.setPost_num(postEntity.getPost_num());
//        postDTO.setPost_title(postEntity.getPost_title());
//        postDTO.setPost_content(postEntity.getPost_content());
//        postDTO.setPost_price(postEntity.getPost_price());
//        postDTO.setPost_saleState(postEntity.getPost_saleState());
//        postDTO.setPost_regdate(postEntity.getPost_regdate());
//        /*        postDTO.setPost_update(postEntity.getPost_update());*/
//        postDTO.setPost_location(postEntity.getPost_location());
//        postDTO.setPost_location2(postEntity.getPost_location2());
//        postDTO.setPost_way(postEntity.getPost_way());
//        postDTO.setPost_shipAddress(postEntity.getPost_shipAddress());
//        postDTO.setPost_address(postEntity.getPost_address());
//        postDTO.setPost_hits(postEntity.getPost_hits());
//        postDTO.setPost_like(postEntity.getPost_like());
//        postDTO.setPost_deliveryFee(postEntity.getPost_deliveryFee());
//        postDTO.setPost_productState(postEntity.getPost_productState());
//        postDTO.setUser_num(postEntity.getUser_num());
//        postDTO.setCategory_num(postEntity.getCategory_num());
//
//        if (postEntity.getFileAttached() == null || postEntity.getFileAttached() == 0) {
//            postDTO.setFile_attached(0);
//        } else {
//            List<String> fileList = new ArrayList<>();
//            postDTO.setFile_attached(postEntity.getFileAttached());
//            for (FileEntity fileEntity : postEntity.getFileEntityList()) {
//                fileList.add(fileEntity.getFileUrl());
//            }
//            postDTO.setFile_url(fileList);
//        }
//        return postDTO;
//    }
//


//}
