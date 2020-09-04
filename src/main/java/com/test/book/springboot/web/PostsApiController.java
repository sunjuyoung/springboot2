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
     *저장
     * @param dto
     * @return
     */
    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto dto){
        return service.save(dto);
    }


    /**
     * 수정
     * @param dto
     * @param id
     * @return
     */
    @PutMapping("/api/v1/posts/{id}")
    public Long update(@RequestBody PostsUpdateRequestDto dto, @PathVariable Long id){
        return service.update(dto, id);
    }

    /**
     * 조회
     * @param id
     * @return
     */
    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id){
        return service.findById(id);
    }
}
