package com.test.book.springboot.web.dto;

import com.test.book.springboot.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Entity클래스와 거의 유사한 형태
 * 하지만 절대로 Entity 클래스를 Request,Response클래스로 사용하지 않는다
 * Entity클래스는 DB와 맞닿은 클래스 핵심 클래스다, 화면 변경은 사소한 기능 변경인데 이를위해
 * 테이블과 연결된 Entity클래스를 변경하는 것은 너무 큰 변경이다.
 */

@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {

    private String title;
    private String content;
    private String author;

    @Builder
    public PostsSaveRequestDto(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public Posts toEntity(){
        return Posts.builder()
                .author(author)
                .content(content)
                .title(title)
                .build();
    }




}
