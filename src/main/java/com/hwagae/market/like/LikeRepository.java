package com.hwagae.market.like;

import com.hwagae.market.post.PostEntity;
import com.hwagae.market.post.PostRepository;
import com.hwagae.market.user.UserDTO;
import com.hwagae.market.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LikeRepository extends JpaRepository<LikeEntity, Integer> {
    LikeEntity findByPostEntityAndUserEntity(PostEntity postEntity, UserEntity userEntity);
/*

    @Query("select l.postEntity.postNum from LikeEntity l where l.userEntity.userNum = :userNum")
    List<Long> findPostNumsByUserNum(@Param("userNum") Long userNum);
*/

}
