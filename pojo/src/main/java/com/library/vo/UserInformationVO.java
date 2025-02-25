package com.library.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserInformationVO {
    private Long id;
    private String avatarPath;
    private String name;
    private String gender;
    private LocalDate birthday;
    private String bio;
    private String interests;
}
