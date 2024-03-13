package com.hwagae.market.like;

import com.hwagae.market.post.PostDTO;
import com.hwagae.market.post.PostEntity;
import com.hwagae.market.post.PostRepository;
import com.hwagae.market.post.PostService;
import com.hwagae.market.user.UserDTO;
import com.hwagae.market.user.UserEntity;
import com.hwagae.market.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikeService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final LikeRepository likeRepository;
    private final PostService postService;


    public LikeDTO findByPostNum(Integer postNum, UserDTO userDTO){
        PostEntity postEntity = postRepository.findById(postNum).get();

        UserEntity userEntity = UserEntity.toUserEntity(userDTO);

        LikeEntity likeEntity = likeRepository.findByPostEntityAndUserEntity(postEntity, userEntity);


        LikeDTO likeDTO = new LikeDTO();
        if(likeEntity!=null){
            likeDTO = LikeDTO.toLikeDTO(likeEntity);
        }


        return likeDTO;
    }

    @Transactional
    public Boolean like(LikeDTO likeDTO){

        System.out.println("post_num : "+likeDTO.getPostNum());
        System.out.println("user_num : "+likeDTO.getUserNum());

        Optional<PostEntity> optionalPostEntity = postRepository.findById(likeDTO.getPostNum());
        Optional<UserEntity> optionalUserEntity = userRepository.findById(likeDTO.getUserNum());

        if(optionalPostEntity.isPresent()&&optionalUserEntity.isPresent()){
            PostEntity postEntity = optionalPostEntity.get();
            UserEntity userEntity = optionalUserEntity.get();

            LikeEntity likes = LikeEntity.toLikeEntity(postEntity, userEntity);
            LikeEntity likeEntity = likeRepository.findByPostEntityAndUserEntity(postEntity, userEntity);

            if(likeEntity!=null){
                likeRepository.deleteById(likeEntity.getLike_num());
            }else{
                likeRepository.save(likes);
            }

            return true;
        }
        else{
            return false;
        }
    }

    @Transactional
    public List<PostDTO> findLikedPostsByUserNum(Integer userNum){
        List<Integer> postNums = likeRepository.findPostNumsByUserNum(userNum);
        return postService.findPostsByPostNums(postNums);
    }


/*

    // Integer 객체만 List에 담는다.
    public List<Integer> getLikedPostsByUserNum(Integer userNum){
        List<LikeEntity> likes = likeRepository.findByUserEntity_UserNum(userNum);
        System.out.println("LikeService의 likes : "+likes);
        if(likes!=null){ // 출력되는거 보고 null로 할지 0으로 할지 정하기

            // 위에서 가져온 LikeEntity의 postNum만 List로 객체 저장을 위해 새 List 선언
            List<Integer> likedPosts = new ArrayList<>();
            // likedPosts에 postNum만 저장
            for(LikeEntity like : likes){
                likedPosts.add(like.getPostEntity().getPostNum());
            }
            System.out.println("LikeService의 likedPosts : "+likedPosts);
            return likedPosts;

        }
        else{
            // 해당 user가 좋아요를 누른 post가 없다면
            return null;
        }
    }
*/

}
