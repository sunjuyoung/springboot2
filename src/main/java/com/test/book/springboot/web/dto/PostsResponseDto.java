package com.test.book.springboot.web.dto;

import com.test.book.springboot.domain.posts.Posts;
import lombok.Getter;

@Getter
public class PostsResponseDto {

    private String content;
    private String author;
    private Long id;
    private String title;


    public PostsResponseDto(Posts entity) {
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
        this.id = entity.getId();
    }


}
