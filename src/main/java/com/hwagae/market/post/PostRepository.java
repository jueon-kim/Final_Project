package com.hwagae.market.post;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository <PostEntity, Integer>{
    // 제목 검색
    List<PostEntity> findByPostTitleContaining(String keyword);
}
