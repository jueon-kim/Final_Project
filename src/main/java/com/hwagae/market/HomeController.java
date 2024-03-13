package com.hwagae.market;

import com.hwagae.market.post.PostDTO;
import com.hwagae.market.post.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {


    private  final PostService postService;

    public HomeController(PostService postService) {
        this.postService = postService;
    }


    @GetMapping("/")
    public String index(Model model) {
        List<PostDTO> postDTOList = postService.findAll();
        model.addAttribute("postList", postDTOList);
        System.out.println("postDTOList = " + postDTOList);
        System.out.println("model = " + model);
        System.out.println("글 목록");

        System.out.println("홈페이지");

        return "/views/user/index";
    }

    @GetMapping("/index")
    public String index2(Model model) {
        List<PostDTO> postDTOList = postService.findAll();
        model.addAttribute("postList", postDTOList);
        System.out.println("postDTOList = " + postDTOList);
        System.out.println("model = " + model);
        System.out.println("글 목록");

        System.out.println("홈페이지");
        return "views/user/index";
    }



}