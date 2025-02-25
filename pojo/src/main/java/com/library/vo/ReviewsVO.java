package com.library.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewsVO {
    private Long reviewId;
    private Long userId;
    private Long bookId;
    private String content;
    private Long rating;
    private Long parentReviewId;
    private LocalDateTime createTime;
}
