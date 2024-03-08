package com.hwagae.market.post;

import lombok.*;

import javax.persistence.Column;
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
    private String post_way;
    private String post_shipAddress;
    private String post_Address;
    private Integer post_hits;
    private Integer post_like;
    private Integer post_deliveryFee;
    private String post_productState;
    private Integer user_num;
    private Integer category_num;
    private Integer file_num;


    public static PostDTO toPostDTO(PostEntity postEntity){
        PostDTO postDTO = new PostDTO();
        postDTO.setPost_num(postEntity.getPost_num());
        postDTO.setPost_title(postEntity.getPost_title());
        postDTO.setPost_content(postEntity.getPost_content());
        postDTO.setPost_price(postEntity.getPost_price());
        postDTO.setPost_saleState(postEntity.getPost_saleState());
        postDTO.setPost_regdate(postEntity.getPost_regdate());
        postDTO.setPost_update(postEntity.getPost_update());
        postDTO.setPost_location(postEntity.getPost_location());
        postDTO.setPost_way(postEntity.getPost_way());
        postDTO.setPost_shipAddress(postEntity.getPost_shipAddress());
        postDTO.setPost_Address(postEntity.getPost_Address());
        postDTO.setPost_hits(postEntity.getPost_hits());
        postDTO.setPost_like(postEntity.getPost_like());
        postDTO.setPost_deliveryFee(postEntity.getPost_deliveryFee());
        postDTO.setPost_productState(postEntity.getPost_productState());
        postDTO.setUser_num(postEntity.getUser_num());
        postDTO.setCategory_num(postEntity.getCategory_num());
        postDTO.setFile_num(postEntity.getFile_num());

        return postDTO;
    }
}
