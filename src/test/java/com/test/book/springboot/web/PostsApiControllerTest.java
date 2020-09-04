package com.test.book.springboot.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.book.springboot.domain.posts.Posts;
import com.test.book.springboot.domain.posts.PostsRepository;
import com.test.book.springboot.web.dto.PostsSaveRequestDto;
import com.test.book.springboot.web.dto.PostsUpdateRequestDto;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//webMvcTest는 JPA기능이 작동하지 않기때문에 한번에 테스트 할때는 SpringBootTest 와 TestRestTemplate을 사용
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostsApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private PostsRepository postsRepository;

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }

    @After
    public void clean() throws Exception{
        postsRepository.deleteAll();
    }

    @Test
    public void Posts_register() throws Exception{
        //JPA Auditing
        LocalDateTime now = LocalDateTime.of(2019,6,4,0,0,0);

        PostsSaveRequestDto dto = PostsSaveRequestDto.builder()
                .author("t01").content("test01").title("title").build();

        String url = "http://localhost:"+port+"/api/v1/posts";

        ResponseEntity<Long> responseEntity = testRestTemplate.postForEntity(url,dto,Long.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Posts> all = postsRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo("title");
        assertThat(all.get(0).getCreateDate()).isAfter(now);

    }

    @Test
    public void Posts_update()throws Exception{
        //저장
        Posts savePosts = postsRepository.save(Posts.builder().title("test").content("test").author("hi").build());

        //업데이트 내용
        String expectedTitle = "title";
        PostsUpdateRequestDto requestDto =
                PostsUpdateRequestDto.builder().content("hi").title(expectedTitle).build();

        //주소
        Long updateId = savePosts.getId();
        String url = "http://localhost:"+port+"/api/v1/posts/"+updateId;

        //when
        mvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().isOk());

        List<Posts> list = postsRepository.findAll();
        list.forEach((e)->System.out.println(e.getTitle()));
        assertThat(list.get(0).getTitle()).isEqualTo(expectedTitle);

    }


}
