package com.test.book.springboot.service.posts;

import com.test.book.springboot.domain.posts.Posts;
import com.test.book.springboot.domain.posts.PostsRepository;
import com.test.book.springboot.web.dto.PostsResponseDto;
import com.test.book.springboot.web.dto.PostsSaveRequestDto;
import com.test.book.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class PostsService {

    private final PostsRepository postsRepository;


    @Transactional
    public Long save(PostsSaveRequestDto dto){
        return postsRepository.save(dto.toEntity()).getId();
    }


    @Transactional
    public Long update(PostsUpdateRequestDto dto, Long id){
        Posts posts = postsRepository.findById(id).orElseThrow(()->
                new IllegalArgumentException("해당 사용자가 없습니다."));

        //Entity객체의 값만 변경하면 별도로 Update 쿼리를 날릴 필요가 없다(영속성 컨텍스트가 유지)
        //트랜젝션 안에서 데이터베이스에서 데이터를 가져오면 데이터는 영속성 컨텍스트가 유지된다
        //이상태에서 값을 변경하면 트랜잭션이 끝나는 시점에 해당 변경분을 반영, 더티 체킹 이라고 한다
        posts.update(dto.getTitle(),dto.getContent());
        return id;
    }


    public PostsResponseDto findById(Long id){
        Posts posts = postsRepository.findById(id).orElseThrow(()->
                new IllegalArgumentException("해당 사용자가 없습니다"));
        return new PostsResponseDto(posts);
    }
}
