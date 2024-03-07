package com.hwagae.market;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String index() {
        return "/views/user/index";
    }

    @GetMapping("/index")
    public String index2(){
        System.out.println("홈페이지");
        return "views/user/index";
    }

}