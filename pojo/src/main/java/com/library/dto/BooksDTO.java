package com.library.dto;

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
public class BooksDTO {
    private Long bookId;
    private String bookName;
    private String author;
    private String isbn;
    private LocalDate publishDate;
    private Double price;
    private String description;
}
