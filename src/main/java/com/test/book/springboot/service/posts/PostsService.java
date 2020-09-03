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

    /**
     * 등록
     * @param dto
     * @return
     */
    @Transactional
    public Long save(PostsSaveRequestDto dto){
        return postsRepository.save(dto.toEntity()).getId();
    }

    /**
     * 수정
     * @param dto
     * @param id
     * @return
     */
    @Transactional
    public Long update(PostsUpdateRequestDto dto, Long id){
        Posts posts = postsRepository.findById(id).orElseThrow(()->
                new IllegalArgumentException("해당 사용자가 없습니다."));

        //Entity객체의 값만 변경하면 별도로 Update 쿼리를 날릴 필요가 없다(영속성 컨텍스트가 유지)
        //트랜젝션 안에서 데이터베이스에서 데이터를 가져오면 데이터는 영속성 컨텍스트가 유지된다
        posts.update(dto.getTitle(),dto.getContent());
        return id;
    }

    /**
     * 조회
     * @param id
     * @return
     */
    public PostsResponseDto findById(Long id){
        Posts posts = postsRepository.findById(id).orElseThrow(()->
                new IllegalArgumentException("해당 사용자가 없습니다"));
        return new PostsResponseDto(posts);
    }
}
