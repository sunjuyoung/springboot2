package com.test.book.springboot.web;

import com.test.book.springboot.service.posts.PostsService;
import com.test.book.springboot.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService service;

    /**
     * 
     * @param model
     * @return
     */
    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("posts",service.findAllDesc());
        return "index";
    }

    /**
     * 저장
     * @return
     */
    @GetMapping("/posts/save")
    public String postsSave(){
        return "posts-save";
    }


    /**
     * 수정
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id,Model model){
        PostsResponseDto post = service.findById(id);
            model.addAttribute("posts",post);
        return "posts-update";
    }

}
