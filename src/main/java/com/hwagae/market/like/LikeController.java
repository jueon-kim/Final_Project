package com.hwagae.market.like;

import com.hwagae.market.post.PostDTO;
import com.hwagae.market.post.PostEntity;
import com.hwagae.market.post.PostService;
import com.hwagae.market.user.UserDTO;
import com.hwagae.market.user.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class LikeController {

    private final PostService postService;
    private final UserService userService;
    private final LikeService likeService;

    @PostMapping("/like")
    public ResponseEntity likeProc(@ModelAttribute LikeDTO likeDTO, HttpSession session, Model model){
        UserDTO userDTO = (UserDTO) session.getAttribute("user");
        likeDTO.setUserNum(userDTO.getUser_num());
        System.out.println("LikeDTO = "+likeDTO);
        Boolean likeResult = likeService.like(likeDTO);
        if(likeResult){
            LikeDTO like = likeService.findByPostNum(likeDTO.getPostNum(),userDTO);

            return new ResponseEntity<>(like, HttpStatus.OK);
        }else{
            return new ResponseEntity<>("게시글 존재안함",HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/myPage/likeList")
    public String likedPostList(HttpSession session, Model model){

        UserDTO userDTO = (UserDTO) session.getAttribute("user");
        List<PostDTO> likedPosts = likeService.findLikedPostsByUserNum(userDTO.getUser_num());

        // 좋아요 정보를 담을 리스트
        List<LikeDTO> likesList = new ArrayList<>();

        // List에 있는 각 게시물에 대해 좋아요 정보를 가져옴
        for (PostDTO post : likedPosts) {
            LikeDTO likeDTO = likeService.findByPostNum(post.getPost_num(), userDTO);
            likesList.add(likeDTO);
        }

        model.addAttribute("likedPosts",likedPosts);
        model.addAttribute("likesList",likesList);

        return "views/myPage/like";
    }

/*
    @GetMapping("/myPage/likeList")
    public String getLikedPosts(HttpSession session, Model model){

        UserDTO userDTO = (UserDTO) session.getAttribute("user");
        List<Integer> likedPostNums = likeService.getLikedPostsByUserNum(userDTO.getUser_num());

        List<PostDTO> likedPosts = new ArrayList<>();
        // 좋아요 정보가 있는지 조건 추가
        for (Integer postNum : likedPostNums){
            PostDTO postDTO = postService.getPostByPostNum(postNum);
            if(postDTO != null){
                likedPosts.add(postDTO);
            }
        }

        model.addAttribute("likedPosts",likedPosts);
        model.addAttribute("user",userDTO);

        return "views/myPage/like";
    }
    */
}
