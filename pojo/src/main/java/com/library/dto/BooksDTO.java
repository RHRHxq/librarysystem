package com.library.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BooksDTO implements Serializable {
    private Long bookId;

    @NotNull(message = "书名不能为空")
    private String bookName;

    @NotNull(message = "作者不能为空")
    private String author;
    private String isbn;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate publishDate;

    @Pattern(regexp = "^[0-9]+[.0-9]", message = "价格只能是数字")
    private Double price;
    private String description;
}
