package com.hwagae.market.like;

import com.hwagae.market.post.PostEntity;
import com.hwagae.market.post.PostRepository;
import com.hwagae.market.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<LikeEntity, Integer> {
    LikeEntity findByPostEntityAndUserEntity(PostEntity postEntity, UserEntity userEntity);
}
