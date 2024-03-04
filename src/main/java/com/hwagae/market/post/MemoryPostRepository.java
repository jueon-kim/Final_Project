package com.hwagae.market.post;

import lombok.val;

import java.sql.Timestamp;
import java.util.*;

public class MemoryPostRepository implements PostRepository{

    private static Map<Long, Post> store = new HashMap<> ();
    private static long sequence = 0L;

    @Override //저장하는 기능
    public Long save(Post post) {
        post.setPostNum(++sequence);
        store.put(post.getPostNum(), post);
        return null;
    }

    @Override //전체 조회
    public List<Post> findAllPost() {
        return new ArrayList<>(store.values());
    }

    @Override // 일년번호
    public Optional<Post> post_num(Long post_num) {
        return Optional.ofNullable(store.get(post_num));
    }

    @Override
    public Optional<Post> post_title(String post_title) {
        return store.values().stream()
                .filter(post -> post.getPostTitle().equals(post_title))
                .findAny();
    }

    @Override
    public Optional<Post> post_content(String post_content) {
        return store.values().stream()
                .filter(post -> post.getPostContent().equals(post_content))
                .findAny();
    }

    @Override
    public Optional<Post> post_price(int targetPrice) {
        return store.values().stream()
                .filter(post -> post.getPostPrice() >= targetPrice)
                .findAny();
    }

    @Override
    public Optional<Post> post_state(Enum post_state) {
        return store.values().stream()
                .filter(post -> post.getPostState().equals(post_state))
                .findAny();
    }

    @Override
    public Optional<Post> post_saleState(Enum post_saleState) {
        return store.values().stream()
                .filter(post -> post.getPostProductState().equals(post_saleState))
                .findAny();
    }


    @Override
    public Optional<Post> post_regedate(Timestamp post_regedate) {
        return store.values().stream()
                .filter(post -> post.getPostregedate().equals(post_regedate))
                .findAny();
    }


    @Override
    public Optional<Post> post_update(String post_update) {
        return store.values().stream()
                .filter(post ->  post.getPostUpdate().equals(post_update))
                .findAny();
    }

    @Override
    public Optional<Post> post_location(String post_location) {
        return store.values().stream()
                .filter(post -> post.getPostLocation().equals(post_location))
                .findAny();
    }

    @Override //택배거래 여부
    public Optional<Post> post_ship(String post_ship) {
        return store.values().stream()
                .filter(post -> post.getPostShip().equals(post_ship))
                .findAny();
    }

    @Override //택배 배송주소
    public Optional<Post> post_shipAddress(String post_shipAddress) {
        return store.values().stream()
                .filter(post -> post.getPostShipAddress().equals(post_shipAddress))
                .findAny();
    }

    @Override // 직거래
    public Optional<Post> post_direct(Boolean post_direct) {
        return store.values().stream()
                .filter(post -> post.getPostDirect().equals(post_direct))
                .findAny();
    }

    @Override
    public Optional<Post> post_hits(int post_hits) {
        return store.values().stream()
                .filter(post -> post.getPostHits() == post_hits)
                .findAny();
    }

    @Override
    public Optional<Post> post_like(int post_like) {
        return store.values().stream()
                .filter(post -> post.getPostLike() == post_like)
                .findAny();
    }

    @Override
    public Optional<Post> post_deliveryFree(Boolean post_deliveryFree) {
        return store.values().stream()
                .filter(post -> post.getPostDeliveFree().equals(post_deliveryFree))
                .findAny();
    }

    @Override // 상품 상태
    public Optional<Post> post_productState(Enum post_productState) {
        return store.values().stream()
                .filter(post -> post.getPostProductState().equals(post_productState))
                .findAny();
    }

    //테스트가 끝나고 clear를 해줌
    public void clearStore(){
        store.clear();
    }
}
