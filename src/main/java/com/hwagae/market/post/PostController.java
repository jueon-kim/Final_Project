package com.hwagae.market.post;

import com.hwagae.market.category.CategoryEntity;
import com.hwagae.market.category.CategoryService;
import com.hwagae.market.file.FileEntity;
import com.hwagae.market.like.LikeDTO;
import com.hwagae.market.like.LikeService;
import com.hwagae.market.user.UserDTO;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final CategoryService categoryService;
    private final LikeService likeService;

    @GetMapping("/post/save")
    public String saveForm(HttpSession session){
        UserDTO userDTO = (UserDTO) session.getAttribute("user");
        System.out.println("userDTO = " + userDTO);

        if(userDTO != null){
            return "views/post/postSave";
        }else {
            return "views/user/login";
        }

    }

    @PostMapping("/post/save")
    public String save(@ModelAttribute PostDTO postDTO) throws IOException {
        postService.save(postDTO);
        System.out.println("postDTO = " + postDTO);
        //세이브 된 글 번호로 리다이렉트하기
        return "views/user/index";
    }

    //이건 나중에 없애도 됨.
    @GetMapping("/post/list")
    public String findAll(Model model){
        List<PostDTO> postDTOList = postService.findAll();
        model.addAttribute("postList", postDTOList);
        System.out.println("postDTOList = " + postDTOList);
        System.out.println("model = " + model);
        System.out.println("글 목록");
        return "postEdit";
    }

    @GetMapping("/post/search")
    public String search(String keyword, Model model, HttpSession session){
        List<PostDTO> searchList = postService.search(keyword);
        UserDTO userDTO = (UserDTO) session.getAttribute("user");
        // 좋아요 정보를 담을 리스트
        List<LikeDTO> likesList = new ArrayList<>();

        // List에 있는 각 게시물에 대해 좋아요 정보를 가져옴
        for (PostDTO post : searchList) {
            LikeDTO likeDTO = likeService.findByPostNum(post.getPost_num(), userDTO);
            likesList.add(likeDTO);
        }

        model.addAttribute("searchList",searchList);
        model.addAttribute("keyword",keyword);
        model.addAttribute("likesList",likesList);
        model.addAttribute("user",userDTO);

        return "views/post/searchList";
    }

    @GetMapping("/post/saleList/{userNick}")
    public String Sale(@PathVariable("userNick") String userNick, Model model, HttpSession session){

        //get요청을 한 nick네임값
        System.out.println("클릭한 닉네임"+userNick);

        //일단 모든 게시물을 불러온다
        List<PostDTO> postDTOList = postService.findAll();

        //세션값 불러오기
        UserDTO userDTO = (UserDTO) session.getAttribute("user");

        //db에 저장된 닉네임 불러오기
        String user_nick = String.valueOf(userDTO.getUser_num());
            System.out.println("user_num으로 변환 = " + user_nick);

        List<PostDTO> userPostList = new ArrayList<>();
        for (PostDTO postDTO : postDTOList) {
            //db에 저장된 일련번호 불러오기
            String postUserNum = String.valueOf(postDTO.getUser_num());
            System.out.println("포스트넘?? = " + postUserNum);

            if (postUserNum.equals(user_nick)) {
                userPostList.add(postDTO);
                System.out.println("게시물 = " + postDTO);
            }
        }
        model.addAttribute("postList", userPostList);
        System.out.println("model = " + model);

        // 페이지 반환
        if(userNick.equals("{userNick}"+userDTO.getUser_nick())){
            System.out.println("판매내역");
            return "views/myPage/saleList";
        }else {
            return "views/user/login";
        }
    }

    @GetMapping("/post/{postNum}")
    public String postDetail(@PathVariable("postNum") Integer postNum, Model model, HttpSession session) {
        System.out.println("넘어옵니까? = " + postNum);

        //게시물목록 조회
        List<PostDTO> postDTOList = postService.findAll();
        System.out.println("글 목록"+postDTOList);

        //해당 postNum에 해당하는 게시물 가져오는 리스트
        List<PostDTO> userPostList = new ArrayList<>();

        UserDTO userDTO = (UserDTO) session.getAttribute("user");
        //PostDTO postDTO = postService.findById(postNum);

        //각각의 게시물 확인
        for (PostDTO postDetail : postDTOList) {

            var postDetailNum = postDetail.getPost_num();
            System.out.println("글에 있는 포스트 넘버 = " + postDetailNum);

            // 현재 확인 중인 게시물이 postNum으로 요청된 게시물인 경우
            if (postDetailNum.equals(postNum)) {
                // 해당 게시물의 카테고리 번호 가져오기
                Integer postCategoryNum = postDetail.getCategory_num();
                System.out.println("글에 있는 카테고리 넘버 = " + postCategoryNum);

                // 카테고리 번호를 사용하여 카테고리 정보 가져오기
                CategoryEntity categoryEntity = categoryService.getCategoryByCategoryNum(postCategoryNum);
                System.out.println("categoryEntity = " + categoryEntity);

                if (categoryEntity != null) {
                    postDetail.setCategoryName(categoryEntity.getCategoryName());
                    System.out.println("categoryEntity.getCategoryName() = " + categoryEntity.getCategoryName());
                }

                userPostList.add(postDetail);
                System.out.println("게시물 = " + postDetail);
            }

        }

        LikeDTO likeDTO = likeService.findByPostNum(postNum, userDTO);
        model.addAttribute("like",likeDTO);

        model.addAttribute("postDetail", userPostList);
        System.out.println("model = " + model);


        System.out.println("디테일");
        return "views/post/postDetail";
    }


    @GetMapping("/post/edit/{postNumArray}")
    public String postEdit(@PathVariable("postNumArray") Integer postNumArray, Model model) {
        System.out.println("넘어옵니까? = " + postNumArray);

        //게시물목록 조회
        List<PostDTO> postDTOList = postService.findAll();
        System.out.println("글 목록"+postDTOList);

        //해당 postNum에 해당하는 게시물 가져오는 리스트
        List<PostDTO> userPostList = new ArrayList<>();
        System.out.println("userPostList = " + userPostList);

        for (PostDTO postDetail : postDTOList) {
            var postDetailNum = postDetail.getPost_num();
            System.out.println("글에 있는 포스트 넘버 = " + postDetailNum);

            // 현재 확인 중인 게시물이 postNum으로 요청된 게시물인 경우
            if (postDetailNum.equals(postNumArray)) {
                // 해당 게시물의 카테고리 번호 가져오기
                Integer postCategoryNum = postDetail.getCategory_num();
                System.out.println("글에 있는 카테고리 넘버 = " + postCategoryNum);

                // 카테고리 번호를 사용하여 카테고리 정보 가져오기
                CategoryEntity categoryEntity = categoryService.getCategoryByCategoryNum(postCategoryNum);
                System.out.println("categoryEntity = " + categoryEntity);

                if (categoryEntity != null) {
                    postDetail.setCategoryName(categoryEntity.getCategoryName());
                    System.out.println("categoryEntity.getCategoryName() = " + categoryEntity.getCategoryName());
                }
                userPostList.add(postDetail);
                System.out.println("게시물 = " + postDetail);
            }
        }
            model.addAttribute("postEdit", userPostList);
            System.out.println("model = " + model);
            return "views/post/postEdit";
    }


}
