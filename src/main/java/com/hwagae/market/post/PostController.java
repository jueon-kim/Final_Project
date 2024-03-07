package com.hwagae.market.post;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class PostController {

    private  final PostService postService;

    @GetMapping("/post/save")
    public String saveForm(){
        return "/views/post/save";
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
        return "/views/post/list";
    }

}
