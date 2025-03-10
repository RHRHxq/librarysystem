package com.library.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewsDTO implements Serializable {
    private Long reviewId;
    private Long userId;
    private Long bookId;
    private String content;
    private Long rating;
    private Long parentReviewId;
    private LocalDateTime createTime;
}
