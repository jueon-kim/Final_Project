package com.hwagae.market.post;


import com.hwagae.market.file.FileEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "post")
public class PostEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postNum;

    @Column
    private String postTitle;

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
    private String post_location2;

    @Column
    private String post_way;

    @Column
    private Integer post_hits;

    @Column
    private Integer post_like;

    @Column
    private String post_productState;

    @Column
    private Integer user_num;

    @Column
    private Integer category_num;

    @Column
    private Integer fileAttached = 0;

    public Integer getFileAttached() {
        return fileAttached != null ? fileAttached : 0;
    }

    @OneToMany(mappedBy = "postEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    //어떤것과 매칭이 되느냐 ? 부모엔티티~ / cascade, onphanRemoval  : cascade속성부여v
    private List<FileEntity> fileEntityList = new ArrayList<>();

    public static PostEntity toSaveEntity(PostDTO postDTO) {
        PostEntity postEntity = new PostEntity();
        postEntity.setPostNum(postDTO.getPost_num());
        postEntity.setPostTitle(postDTO.getPost_title());
        postEntity.setPost_content(postDTO.getPost_content());
        postEntity.setPost_price(postDTO.getPost_price());
        postEntity.setPost_saleState(postDTO.getPost_saleState());
        postEntity.setPost_regdate(postDTO.getPost_regdate());
        postEntity.setPost_update(postDTO.getPost_update());
        postEntity.setPost_location(postDTO.getPost_location());
        postEntity.setPost_location2(postDTO.getPost_location2());
        postEntity.setPost_way(postDTO.getPost_way());
        postEntity.setPost_hits(postDTO.getPost_hits());
        postEntity.setPost_like(postDTO.getPost_like());
        postEntity.setPost_productState(postDTO.getPost_productState());
        postEntity.setUser_num(postDTO.getUser_num());
        postEntity.setCategory_num(postDTO.getCategory_num());
        postEntity.setFileAttached(0); //파일 없음
        return postEntity;
    }

    public static PostEntity toSaveFileEntity(PostDTO postDTO) {
        PostEntity postEntity = new PostEntity();
        postEntity.setPostNum(postDTO.getPost_num());
        postEntity.setPostTitle(postDTO.getPost_title());
        postEntity.setPost_content(postDTO.getPost_content());
        postEntity.setPost_price(postDTO.getPost_price());
        postEntity.setPost_saleState(postDTO.getPost_saleState());
        postEntity.setPost_regdate(postDTO.getPost_regdate());
        postEntity.setPost_update(postDTO.getPost_update());
        postEntity.setPost_location(postDTO.getPost_location());
        postEntity.setPost_location2(postDTO.getPost_location2());
        postEntity.setPost_way(postDTO.getPost_way());
        postEntity.setPost_hits(postDTO.getPost_hits());
        postEntity.setPost_like(postDTO.getPost_like());
        postEntity.setPost_productState(postDTO.getPost_productState());
        postEntity.setUser_num(postDTO.getUser_num());
        postEntity.setCategory_num(postDTO.getCategory_num());
        postEntity.setFileAttached(1); //파일 없음
        return postEntity;

    }


}