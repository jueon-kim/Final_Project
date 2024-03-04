package com.hwagae.market.post;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Transactional
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Long savePost(Post post) {
        return postRepository.save(post);
    }

    public List<Post> getAllPosts() {
        return postRepository.findAllPost();
    }

    public Optional<Post> getPostByNum(Long postNum) {
        return postRepository.post_num(postNum);
    }

    public Optional<Post> getPostByTitle(String postTitle) {
        return postRepository.post_title(postTitle);
    }

    public Optional<Post> getPostByContent(String postContent) {
        return postRepository.post_content(postContent);
    }

    public Optional<Post> getPostByPrice(int postPrice) {
        return postRepository.post_price(postPrice);
    }

    public Optional<Post> getPostBySaleState(Enum postSaleState) {
        return postRepository.post_state(postSaleState);
    }

    public Optional<Post> getPostByRegDate(Timestamp postRegDate) {
        return postRepository.post_regedate(postRegDate);
    }

    public Optional<Post> getPostByUpdate(String postUpdate) {
        return postRepository.post_update(postUpdate);
    }

    public Optional<Post> getPostByLocation(String postLocation) {
        return postRepository.post_location(postLocation);
    }

    public Optional<Post> getPostByShip(String postShip) {
        return postRepository.post_ship(postShip);
    }

    public Optional<Post> getPostByShipAddress(String postShipAddress) {
        return postRepository.post_shipAddress(postShipAddress);
    }

    public Optional<Post> getPostByDirect(Boolean postDirect) {
        return postRepository.post_direct(postDirect);
    }

    public Optional<Post> getPostByHits(int postHits) {
        return postRepository.post_hits(postHits);
    }

    public Optional<Post> getPostByLike(int postLike) {
        return postRepository.post_like(postLike);
    }

    public Optional<Post> getPostByDeliveryFree(Boolean postDeliveryFree) {
        return postRepository.post_deliveryFree(postDeliveryFree);
    }

    public Optional<Post> getPostByProductState(Enum postProductState) {
        return postRepository.post_productState(postProductState);
    }
}
























