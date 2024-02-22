package com.hwagae.market.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/user/join")
    public String joinForm(){
        System.out.println("회원가입 페이지");
        return "views/user/join";
    }

    @PostMapping("/user/join")
    public String join(@ModelAttribute UserDTO userDTO){
        System.out.println("UserController.save");
        System.out.println("userDTO = " + userDTO);
        userService.save(userDTO);
        System.out.println("회원가입 완료");
        return "redirect:/user/login?success=true"; // 회원가입 성공 시 login 페이지로 리다이렉트하고 success=true 파라미터를 전달
    }

    @PostMapping("/user/id-check")
    public @ResponseBody String idCheck(@RequestParam("user_id") String user_id) {
        System.out.println("userId = " + user_id);
        String checkResult = userService.idCheck(user_id);
        return checkResult;
/*        if(checkResult != null){
            return "ok";
        }else {
            return "no";
        }*/
    }

    @PostMapping("/user/nick-check")
    public @ResponseBody String nickCheck(@RequestParam("user_nick") String user_nick) {
        System.out.println("userNick = " + user_nick);
        String checkResult = userService.nickCheck(user_nick);
        return checkResult;
/*        if(checkResult != null){
            return "ok";
        }else {
            return "no";
        }*/
    }



    @GetMapping("/user/login")
    public String LoginForm() {
        System.out.println("로그인 페이지");
        return "views/user/login";
    }

    @PostMapping("/user/login")
    public String Login(@ModelAttribute UserDTO userDTO, HttpSession session){
        UserDTO result = userService.login(userDTO);
        if(result != null){
            session.setAttribute("user", result);
            System.out.println("로그인 성공");
            return "views/myPage/myPage";
        }else{
            return "views/user/login";
        }
    }

    @GetMapping("/user/logout")
    public String Logout(HttpSession session) {
        session.invalidate();
        System.out.println("로그아웃");
        return "views/user/login";
    }

    @GetMapping("/myPage")
    public String MyPage(){
        System.out.println("마이페이지");
        return "views/myPage/myPage";
    }


    @GetMapping("/user/find")
    public String Find(){
        System.out.println("아이디 비밀번호 찾기");
        return "views/user/find";
    }

    @PostMapping("/user/findID")
    public String findID(@ModelAttribute UserDTO userDTO, Model model) {
        UserDTO result = userService.findID(userDTO);
        System.out.println("ID 찾기 = " + result);
        System.out.println("ID 정보 = " + userDTO);
        model.addAttribute("findID", result.getUser_id()); // Thymeleaf에서 사용할 수 있도록 모델에 추가
        return "views/user/result";
    }

    @PostMapping("/user/findPW")
    public String findPW(@ModelAttribute UserDTO userDTO, Model model) {
        UserDTO result = userService.findPW(userDTO); // findPW 메서드 호출로 변경
        System.out.println("PW 찾기 = " + result);
        System.out.println("PW 정보 = " + userDTO);
        model.addAttribute("findPW", result.getUser_pw()); // Thymeleaf에서 사용할 수 있도록 모델에 추가
        return "views/user/result";
    }


    @GetMapping("/myPage/userUpdate")
    public String Update(HttpSession session, Model model){
        UserDTO userDTO = (UserDTO) session.getAttribute("user");
        UserDTO updateInfo = userService.updateForm(userDTO.getUser_id());
        model.addAttribute("updateInfo", updateInfo);
        System.out.println("updateInfo = " + updateInfo);
        System.out.println("session = " + session + ", model = " + model);
        System.out.println("회원정보 수정");
        return "views/myPage/userUpdate";
    }


    @PostMapping("/user/pwUpdate")
    public String UpdatePW(@ModelAttribute UserDTO userDTO) throws IOException {
        userService.update(userDTO);
        UserDTO updatedUser = userService.login(userDTO);

        return "redirect:/myPage/userUpdate";
    }

    @PostMapping("/user/nickUpdate")
    public String UpdateNick(@ModelAttribute UserDTO userDTO) throws IOException {
        userService.update(userDTO);
        UserDTO updatedUser = userService.login(userDTO);

        return "redirect:/myPage/userUpdate";
    }





    @GetMapping("/myPage/purchaseList/{userNick}")
    public String Purchase(@PathVariable("userNick") String userNick){
        System.out.println("구매내역");
        return "views/myPage/purchaseList";
    }


    @GetMapping("/myPage/saleList/{userNick}")
    public String Sale(@PathVariable("userNick") String userNick){
        System.out.println("판매내역");
        return "views/myPage/saleList";
    }


    @GetMapping("/myPage/like/{userNick}")
    public String Like(@PathVariable("userNick") String userNick){
        System.out.println("찜한 상품");
        return "views/myPage/like";
    }


    @GetMapping("/myPage/unRegister/{userNick}")
    public String UnRegister(@PathVariable("userNick") String userNick){
        System.out.println("회원탈퇴");
        return "views/myPage/unRegister";
    }


    @PostMapping("/myPage/unRegister")
    public String UnRegister(@RequestParam("user_id") String userId,
                             @RequestParam("user_pw") String userPw,
                             HttpSession session) {
        // 입력한 ID와 비밀번호로 로그인 시도
        UserDTO loginAttempt = new UserDTO();
        loginAttempt.setUser_id(userId);
        loginAttempt.setUser_pw(userPw);
        UserDTO user = userService.login(loginAttempt);

        // 사용자가 존재하고, 입력한 비밀번호가 일치하는 경우에만 삭제 진행
        if (user != null && user.getUser_pw().equals(userPw)) {
            userService.deleteUser(userId);
            session.invalidate(); // 세션 무효화
            return "views/user/login"; // 회원탈퇴 후 로그인 페이지로 리다이렉트
        } else {
            // ID나 비밀번호가 일치하지 않는 경우
            return "views/myPage/unRegister"; // 회원탈퇴 페이지로 다시 이동
        }
    }



}
