package com.library.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Books {
    private Long bookId;
    private String bookName;
    private String author;
    private String isbn;
    private LocalDate publishDate;
    private Double price;
    private String description;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
