package com.hwagae.market.like;

import com.hwagae.market.post.PostService;
import com.hwagae.market.user.UserDTO;
import com.hwagae.market.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

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
}
