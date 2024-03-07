package com.hwagae.market.post;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "post")
public class PostEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer post_num;

    @Column
    private String post_title;

    @Column
    private String post_content;

    @Column
    private Integer post_price;

    @Column
    private String post_saleState;

    @Column
    private String post_regdate;

    @Column
    private String post_update;

    @Column
    private String post_location;

    @Column
    private Integer post_ship;

    @Column
    private String post_shipAddress;

    @Column
    private Integer post_direct;

    @Column
    private String post_Address;

    @Column
    private Integer post_hits;

    @Column
    private Integer post_like;

    @Column
    private Integer post_deliveryFee;

    @Column
    private String post_productState;

    @Column
    private Integer user_num;

    @Column
    private Integer category_num;

    @Column
    private Integer file_num;


    public static PostEntity toSaveEntity(PostDTO postDTO){
        PostEntity postEntity = new PostEntity();
        postEntity.setPost_num(postDTO.getPost_num());
        postEntity.setPost_title(postDTO.getPost_title());
        postEntity.setPost_content(postDTO.getPost_content());
        postEntity.setPost_price(postDTO.getPost_price());
        postEntity.setPost_saleState(postDTO.getPost_saleState());
        postEntity.setPost_regdate(postDTO.getPost_regdate());
        postEntity.setPost_update(postDTO.getPost_update());
        postEntity.setPost_location(postDTO.getPost_location());
        postEntity.setPost_ship(postDTO.getPost_ship());
        postEntity.setPost_shipAddress(postDTO.getPost_shipAddress());
        postEntity.setPost_direct(postDTO.getPost_direct());
        postEntity.setPost_Address(postDTO.getPost_Address());
        postEntity.setPost_hits(postDTO.getPost_hits());
        postEntity.setPost_like(postDTO.getPost_like());
        postEntity.setPost_deliveryFee(postDTO.getPost_deliveryFee());
        postEntity.setPost_productState(postDTO.getPost_productState());
        postEntity.setUser_num(postDTO.getUser_num());
        postEntity.setCategory_num(postDTO.getCategory_num());
        postEntity.setFile_num(postDTO.getFile_num());

        return postEntity;
    }

}
