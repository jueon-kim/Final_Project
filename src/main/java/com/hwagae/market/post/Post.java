package com.hwagae.market.post;

import lombok.Getter;
import lombok.Setter;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
public class Post {


    private Long postNum;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String postState;
    private Boolean PostDeliveryFree;

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public int getPostPrice() {
        return postPrice;
    }

    public void setPostPrice(int postPrice) {
        this.postPrice = postPrice;
    }

    public Timestamp getPostregedate() {
        return postregedate;
    }

    public void setPostregedate(Timestamp postregedate) {
        this.postregedate = postregedate;
    }

    public Timestamp getPostUpdate() {
        return postUpdate;
    }

    public void setPostUpdate(Timestamp postUpdate) {
        this.postUpdate = postUpdate;
    }

    public String getPostLocation() {
        return postLocation;
    }

    public void setPostLocation(String postLocation) {
        this.postLocation = postLocation;
    }

    public Boolean getPostShip() {
        return postShip;
    }

    public void setPostShip(Boolean postShip) {
        this.postShip = postShip;
    }

    public String getPostShipAddress() {
        return postShipAddress;
    }

    public void setPostShipAddress(String postShipAddress) {
        this.postShipAddress = postShipAddress;
    }

    public Boolean getPostDirect() {
        return postDirect;
    }

    public void setPostDirect(Boolean postDirect) {
        this.postDirect = postDirect;
    }

    public int getPostHits() {
        return postHits;
    }

    public void setPostHits(int postHits) {
        this.postHits = postHits;
    }

    public int getPostLike() {
        return postLike;
    }

    public void setPostLike(int postLike) {
        this.postLike = postLike;
    }

    public Boolean getPostDeliveryFree() {
        return PostDeliveryFree;
    }

    public void setPostDeliveryFree(Boolean postDeliveryFree) {
       this.PostDeliveryFree = postDeliveryFree;
    }

    public Enum getPostProductState() {
        return postProductState;
    }

    public void setPostProductState(Enum postProductState) {
        this.postProductState = postProductState;
    }

    public Long getPostNum() {
        return postNum;
    }

    public void setPostNum(Long postNum) {
        this.postNum = postNum;
    }

    public String getPostState() {
        return postState;
    }

    public void setPostState(String postState) {
        this.postState = postState;
    }
    public Object getPostDeliveFree() {
        return postDeliveFree;
    }

    public void setPostDeliveFree(Object postDeliveFree) {
        this.postDeliveFree = (Boolean) postDeliveFree;
    }

    private String postTitle;           // 글 제목
    private String postContent;         // 글 내용
    private int postPrice;              // 가격
    private Enum postSaleState;
    private Timestamp postregedate;     // 등록일자
    private Timestamp postUpdate;       // 수정일자
    private String postLocation;        // 희망 직거래 장소
    private Boolean postShip;           // 택배거래 여부
    private String postShipAddress;     // 택배 배송주소
    private Boolean postDirect;         // 직거래
    private int postHits;               // 조회수
    private int postLike;               //좋아요
    private Boolean postDeliveFree;   // 배송비 포함여부
    private Enum postProductState;      // 상품 상태

 }
