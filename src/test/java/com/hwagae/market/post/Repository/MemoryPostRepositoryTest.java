package com.hwagae.market.post.Repository;


import com.hwagae.market.post.MemoryPostRepository;
import com.hwagae.market.post.Post;
import com.hwagae.market.post.PostRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;


public class MemoryPostRepositoryTest {

    MemoryPostRepository repository = new MemoryPostRepository();

    @AfterEach //테스트가 끝나고 clear를 해줌
    public void afterEach(){
        repository.clearStore();
    }


//    @Test
//    public void save(){
//        Post post = new Post();
//        post.setPostTitle("test");
//
//        repository.save(post);
//        Post result = repository.Post_title(post.getPostTitle()).get();
//        assertThat(post).isEqualTo(result);
//    }

    @Test
    public void post_title(){
        Post post = new Post();
        post.setPostTitle("글제목");

        repository.save(post);
        Post result = repository.post_title(post.getPostTitle()).get();
        assertThat(post).isEqualTo(result);
    }

    @Test
    public void post_num(){
        Post post1 = new Post();
        post1.setPostContent("1");
        repository.save(post1);

        Post post2 = new Post();
        post2.setPostContent("2");
        repository.save(post2);

        Post result = repository.post_num(Long.valueOf("2")).get();
        assertThat(result).isEqualTo(post2);
    }

    @Test
    public void findAllPost() {
        Post post1 = new Post();
        post1.setPostTitle("post1");
        repository.save(post1);

        Post post2 = new Post();
        post2.setPostTitle("post2");
        repository.save(post2);

        List<Post> result = repository.findAllPost();
        assertThat(result.size()).isEqualTo(2);
    }
}