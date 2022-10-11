package org.zerock.mreview.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zerock.mreview.dto.ReviewDTO;
import org.zerock.mreview.service.ReviewService;

import java.util.List;

@RestController //Ajax로 동작하기 때문에
@RequestMapping("/reviews")
@Log4j2
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/{mno}/all") // 결과데이터 : ReviewDTO 리스트, 해당영화의 모든 리뷰 반환
    public ResponseEntity<List<ReviewDTO>> getList(@PathVariable("mno") Long mno){

        List<ReviewDTO> reviewDTOList = reviewService.getListOfMovie(mno);

        return new ResponseEntity<>(reviewDTOList, HttpStatus.OK);
    }

    @PostMapping("/{mno}") // 결과데이터 : 생성된 리뷰 번호 , 새로운 리뷰등록
    public ResponseEntity<Long> addReview(@RequestBody ReviewDTO movieReviewDTO){

        Long reviewnum = reviewService.register(movieReviewDTO);

        return new ResponseEntity<>(reviewnum,HttpStatus.OK);
    }

    @PutMapping("/{mno}/{reviewnum}") // 결과데이터 : 리뷰의 수정 성공 여부, 리뷰수정
    public ResponseEntity<Long> modifyReview(@PathVariable Long reviewnum, @RequestBody ReviewDTO movieReviewDTO){

        reviewService.modify(movieReviewDTO);

        return new ResponseEntity<>(reviewnum,HttpStatus.OK);
    }


    @DeleteMapping("/{mno}/{reviewnum}") // 리뷰 삭제
    public ResponseEntity<Long> removieReview(@PathVariable Long reviewnum){

        reviewService.remove(reviewnum);

        return new ResponseEntity<>(reviewnum,HttpStatus.OK);
    }
}
