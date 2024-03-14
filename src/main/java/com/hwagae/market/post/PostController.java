package com.hwagae.market.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping("/user")
public class PostController {

    @GetMapping("/postForm")
    public String postForm() {
        System.out.println("post 등록 페이지");
        return "views/user/postForm";
    }


}
