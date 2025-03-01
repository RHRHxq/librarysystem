package com.library.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BooksSearchDTO implements Serializable {
    private String bookName;
    private String author;
    private String isbn;
    private LocalDate publishDateStart;
    private LocalDate publishDateEnd;
    private Double priceStart;
    private Double priceEnd;
    private Long categoryId;
}
