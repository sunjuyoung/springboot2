package com.test.book.springboot.web;

import com.test.book.springboot.service.posts.PostsService;
import com.test.book.springboot.web.dto.PostsResponseDto;
import com.test.book.springboot.web.dto.PostsSaveRequestDto;
import com.test.book.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class PostsApiController {

    private final PostsService service;

    /**
     *
     * @param dto
     * @return
     */
    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto dto){
        return service.save(dto);
    }


    @PutMapping("/api/v1/posts/{id}")
    public Long update(@RequestBody PostsUpdateRequestDto dto, @PathVariable Long id){
        return service.update(dto, id);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id){
        return service.findById(id);
    }
}
