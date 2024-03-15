package com.hwagae.market.restrictedUser;

import com.hwagae.market.user.UserEntity;
import com.hwagae.market.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class ResUserController {
    private final ResUserService resUserService;
    private final UserService userService;

    @PostMapping("/admin/resUser")
    public String insertForm(@ModelAttribute ResUserDTO resUserDTO, @RequestParam("res_account") String resAccount, Model model){
        System.out.println("관리자 -  제재회원 입력해보아요~^^");
        System.out.println("resUserDTO = " + resUserDTO);
        model.addAttribute("resUserDTO",resUserDTO);
        model.addAttribute("resAccount",resAccount);
        return "/views/admin/insertResUser";
    }

    @PostMapping("/admin/insertResUser")
    public String insertResUser(@ModelAttribute ResUserDTO resUserDTO,Model model) throws IOException {
        System.out.println("관리자 - 제재 회원 입력중");
        System.out.println("resUserDTO = " + resUserDTO );
        UserEntity userEntity=userService.findByNum(resUserDTO.getRes_unum());
        resUserService.save(resUserDTO,userEntity);
        model.addAttribute("resUserDTO",resUserDTO);
        return "/views/admin/reportUserList";
    }

}
