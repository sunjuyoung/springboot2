package com.test.book.springboot.web.dto;

import com.test.book.springboot.domain.posts.Posts;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostsListResponseDto {

    private String author;
    private Long id;
    private String title;
    private LocalDateTime modifiedDate;

    public PostsListResponseDto(Posts entity) {
        this.author = entity.getAuthor();
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.modifiedDate = entity.getModifiedDate();
    }



}
