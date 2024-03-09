package com.hwagae.market.post;

import com.hwagae.market.like.LikeDTO;
import com.hwagae.market.like.LikeService;
import com.hwagae.market.user.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class PostController {

    private  final PostService postService;
    private final LikeService likeService;

    @GetMapping("/post/save")
    public String saveForm(){
        return "/views/post/postSave";
    }

    @PostMapping("/post/save")
    public String save(@ModelAttribute PostDTO postDTO){
        System.out.println("postDTO = " + postDTO);
        postService.save(postDTO);
        return "/views/user/index";
    }

    @GetMapping("/post/list")
    public String findAll(Model model){
        List<PostDTO> postDTOList = postService.findAll();
        model.addAttribute("postList", postDTOList);
        System.out.println("postDTOList = " + postDTOList);
        System.out.println("model = " + model);
        System.out.println("글 목록");
        return "/views/post/postList";
    }

    @GetMapping("/post/saleList/{userNick}")
    public String Sale(@PathVariable("userNick") String userNick, Model model, HttpSession session){
        List<PostDTO> postDTOList = postService.findAll();
        UserDTO userDTO = (UserDTO) session.getAttribute("user");
            System.out.println("클릭한 닉네임"+userNick);
        int user_nick = userDTO.getUser_num();
            System.out.println("user_num으로 변환 = " + user_nick);
        List<PostDTO> userPostList = new ArrayList<>();
        for (PostDTO postDTO : postDTOList) {
            int postUserNum = postDTO.getUser_num();
            System.out.println("포스트넘?? = " + postUserNum);
            if (postUserNum==user_nick) {
                userPostList.add(postDTO);
                System.out.println("postId"+postDTO.getPost_num());
                System.out.println("게시물 = " + postDTO);
            }
        }
        model.addAttribute("postList", userPostList);
        model.addAttribute("user",userDTO);
        System.out.println("model = " + model);

        System.out.println("판매내역");

        return "views/myPage/saleList";
    }

    @GetMapping("/post/{postNum}")
    public String postDetail(@PathVariable("postNum") Integer postNum,HttpSession session, Model model) {
        System.out.println("넘어옵니까? = " + postNum);

        List<PostDTO> postDTOList = postService.findAll();
        System.out.println("글 목록"+postDTOList);


        List<PostDTO> userPostList = new ArrayList<>();
        for (PostDTO postDetail : postDTOList) {
                var postDetailNum = postDetail.getPost_num();
            System.out.println("글에 있는 포스트 넘버 = " + postDetailNum);
            if (postDetailNum.equals(postNum)) {

                userPostList.add(postDetail);
                System.out.println("게시물 = " + postDetail);
            }
        }


        UserDTO userDTO = (UserDTO)session.getAttribute("user");
        LikeDTO likeDTO = likeService.findByPostNum(postNum,userDTO);
        model.addAttribute("postDetail", userPostList);
        model.addAttribute("userNum",userDTO);
        model.addAttribute("like",likeDTO);
        System.out.println("model = " + model);

        System.out.println("디테일");

        return "/views/post/postDetail";
    }




}
