package com.test.book.springboot.domain.posts;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @After
    public void cleanup(){
        postsRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기(){
        String title= "테스트 타이틀";
        String content = "테스트내용";

        postsRepository.save(Posts.builder().author("t01")
        .content(content).title(title).build());

        List<Posts> list = postsRepository.findAll();

        Posts post = list.get(0);
        list.forEach(e->System.out.println(e.getContent()));

        assertThat(post.getContent()).isEqualTo(content);
        assertThat(post.getTitle()).isEqualTo(title);


    }
}
