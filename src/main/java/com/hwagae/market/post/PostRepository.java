package com.hwagae.market.post;

import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;


@Repository
public interface PostRepository{

    //저장하는 기능
    Long save(Post post);

    List<Post> findAllPost();

    Optional<Post> post_num(Long postNum);

    Optional<Post> post_title(String post_title);

    Optional<Post> post_content(String post_content);

    Optional<Post> post_price(int post_pirce);

    Optional<Post> post_state(Enum post_state);

    Optional<Post> post_regedate(Timestamp post_regedate);

    Optional<Post> post_update(String post_update);

    Optional<Post> post_location(String post_location);

    //택배거래 여부
    Optional<Post> post_ship(String post_ship);

    //택배 배송주소
    Optional<Post> post_shipAddress(String post_shipAddress);

    // 직거래
    Optional<Post> post_direct(Boolean post_direct);

    // 조회수
    Optional<Post> post_hits(int post_hits);

    Optional<Post> post_like(int post_like);

    Optional<Post> post_deliveryFree(Boolean post_deliveryFree);

    // 상품 상태
    Optional<Post> post_productState(Enum post_productState);

    Optional<Post> post_saleState(Enum post_saleState);
}
