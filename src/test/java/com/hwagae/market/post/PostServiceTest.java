package com.hwagae.market.post;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PostServiceTest {

    @Mock
    private PostRepository postRepository;

    @InjectMocks
    private PostService postService;

    @Test
    void getAllPosts() {
        // GIVEN
        List<Post> posts = List.of(new Post(), new Post());
        when(postRepository.findAllPost()).thenReturn(posts);

        // WHEN
        List<Post> result = postService.getAllPosts();

        // THEN
        assertEquals(posts, result);
    }

    @Test
    void getPostByNumId() {
        // GIVEN
        long postNum = 1L;
        Post post = new Post();
        when(postRepository.post_num(postNum)).thenReturn(Optional.of(post));

        // WHEN
        Optional<Post> result = postService.getPostByNum(postNum);

        // THEN
        assertEquals(Optional.of(post), result);
    }


    @Test
    void 모든게시물조회() {

        //GIVEN

        //WHEN

        //THEN
    }

    @Test
    void 게시물일련번호() {

        //GIVEN

        //WHEN

        //THEN
    }

    @Test
    void 게시물제목() {

        //GIVEN

        //WHEN

        //THEN
    }

    @Test
    void 게시물글내용() {

        //GIVEN

        //WHEN

        //THEN
    }

    @Test
    void 가격() {

        //GIVEN

        //WHEN

        //THEN
    }

    @Test
    void 상태() {

        //GIVEN

        //WHEN

        //THEN
    }

    @Test
    void 수정일자() {

        //GIVEN

        //WHEN

        //THEN
    }

    @Test
    void 거래장소() {

        //GIVEN

        //WHEN

        //THEN
    }

    @Test
    void 택배거래여부() {

        //GIVEN

        //WHEN

        //THEN
    }

    @Test
    void 택배배송주소() {

        //GIVEN

        //WHEN

        //THEN
    }

    @Test
    void 직거래() {

        //GIVEN

        //WHEN

        //THEN
    }

    @Test
    void 조회수() {

        //GIVEN

        //WHEN

        //THEN
    }

    @Test
    void 좋아요() {

        //GIVEN

        //WHEN

        //THEN
    }

    @Test
    void 배송비포함() {

        //GIVEN

        //WHEN

        //THEN
    }

    @Test
    void 상품상태() {

        //GIVEN

        //WHEN

        //THEN
    }
}