package com.hwagae.market;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String home() {
        System.out.println("main controller start");
        return "/views/index";
    }

    @GetMapping("board/cheatSearch")
    public String cheatSearch() {
        System.out.println("사기조회하러 가자~");
        return "views/board/cheatSearch";
    }


    @GetMapping("myPage/faq")
    public String faq() {
        System.out.println("자주묻는 질문 보자~");
        return "views/myPage/faq";
    }


    @GetMapping("myPage/report")
    public String report(){
        System.out.println("신고하러 가겟어요");
        return "views/myPage/report";
    }




}
