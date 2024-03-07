package com.hwagae.market.user;

import com.hwagae.market.email.EmailController;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;

@Log4j2
@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final EmailController emailController;


    @GetMapping("/user/join")
    public String joinForm(){
        System.out.println("회원가입 페이지");
        return "views/user/join";
    }

    @PostMapping("/user/join")
    public String join(@ModelAttribute UserDTO userDTO){
        if ("ok".equals(emailController.getEmailAuthResult())) {
            System.out.println("후에엥"+emailController.getEmailAuthResult());
            System.out.println("UserController.save");
            System.out.println("userDTO = " + userDTO);
            userService.save(userDTO);
            System.out.println("회원가입 완료");
            return "redirect:/user/login?success=true"; // 회원가입 성공 시 login 페이지로 리다이렉트하고 success=true 파라미터를 전달
        }else {
            return "redirect:/user/join?success=false";
        }
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
            return "redirect:/user/login?loginFailed=true";
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
        String result = userService.findID(userDTO); // 반환 타입을 String으로 변경
        System.out.println("ID 찾기 = " + result);
        System.out.println("ID 정보 = " + userDTO);
        model.addAttribute("findID", result); // Thymeleaf에서 사용할 수 있도록 모델에 추가
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


/*    @PostMapping("/user/pwUpdate")
    public String UpdatePW(@ModelAttribute UserDTO userDTO) throws IOException {
        userService.updatePw(userDTO);
        UserDTO updatedUser = userService.login(userDTO);

        return "redirect:/myPage/userUpdate";
    }*/

    @PostMapping("/user/pwUpdate")
    public String UpdatePW(@ModelAttribute UserDTO userDTO, HttpSession session) throws IOException {
        // 세션에 저장된 사용자 정보 가져오기
        UserDTO sessionUser = (UserDTO) session.getAttribute("user");

        // 세션의 user_num과 폼에서 전송된 user_num이 일치하는 경우에만 수행
        if (sessionUser.getUser_num().equals(userDTO.getUser_num())) {
            // 비밀번호 업데이트 수행
            userService.updatePw(userDTO);
            // 세션에서 기존 사용자 정보 제거
            session.removeAttribute("user");
            // 업데이트된 사용자 정보를 세션에 설정
            session.setAttribute("user", sessionUser);
        }

        return "redirect:/myPage/userUpdate";
    }


    @PostMapping("/user/nickUpdate")
    public String UpdateNick(@ModelAttribute UserDTO userDTO, HttpSession session) throws IOException {
        userService.updateNick(userDTO);
        UserDTO updatedUser = userService.login(userDTO);

        UserDTO sessionUser = (UserDTO) session.getAttribute("user");
        // 사용자 정보 업데이트
        sessionUser.setUser_nick(userDTO.getUser_nick()); // 닉네임으로 변경 예시
        // 세션에서 기존 사용자 정보 제거
        session.removeAttribute("user");
        // 업데이트된 사용자 정보를 세션에 설정
        session.setAttribute("user", sessionUser);

        return "redirect:/myPage/userUpdate";
    }


    @PostMapping("/user/locationUpdate")
    public String UpdateLocation(@ModelAttribute UserDTO userDTO, HttpSession session) throws IOException {
        userService.updateLocation(userDTO);
        UserDTO updatedUser = userService.login(userDTO);

        UserDTO sessionUser = (UserDTO) session.getAttribute("user");
        // 사용자 정보 업데이트
        sessionUser.setUser_location2(userDTO.getUser_location2()); // 닉네임으로 변경 예시
        // 세션에서 기존 사용자 정보 제거
        session.removeAttribute("user");
        // 업데이트된 사용자 정보를 세션에 설정
        session.setAttribute("user", sessionUser);

        return "redirect:/myPage/userUpdate";
    }



/*    @PostMapping("/user/photoUpdate")
    public String UpdatePhoto(@ModelAttribute UserDTO userDTO) throws IOException {
        if(userDTO.getUser_id()!=null){
            String fileName = null;
            MultipartFile upLoadFile = userDTO.getUpLoadFile();
            System.out.println("upLoadFile = " + upLoadFile);
            if(!upLoadFile.isEmpty()){
                fileName = upLoadFile.getOriginalFilename();
                System.out.println(fileName);
                upLoadFile.transferTo(new File("C:\\image\\"+fileName));
                userDTO.setUser_photo(fileName);
            }
        }
        userService.updatePhoto(userDTO);
        UserDTO updatedUser = userService.login(userDTO);

        return "redirect:/myPage/userUpdate";
    }*/


    @PostMapping("/user/photoUpdate")
    public String UpdatePhoto(@ModelAttribute UserDTO userDTO, HttpSession session) throws IOException {
        MultipartFile upLoadFile = userDTO.getUpLoadFile();
        if (upLoadFile != null && !upLoadFile.isEmpty()) {
            String fileName = System.currentTimeMillis()+ "_" + upLoadFile.getOriginalFilename();
            System.out.println("Uploaded file name: " + fileName);

            // 파일을 서버에 저장
            upLoadFile.transferTo(new File("C:/image/" + fileName));

            // 사용자 정보 업데이트
            userDTO.setUser_photo(fileName);
            userService.updatePhoto(userDTO);

            UserDTO sessionUser = (UserDTO) session.getAttribute("user");
            // 사용자 정보 업데이트
            sessionUser.setUser_photo(fileName);
            // 세션에서 기존 사용자 정보 제거
            session.removeAttribute("user");
            // 업데이트된 사용자 정보를 세션에 설정
            session.setAttribute("user", sessionUser);

        }
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
