package com.library.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserInformationDTO {
    private Long id;
    private String avatarPath;
    private String email;
    private String phone;
    private String name;
    private String gender;
    private LocalDate birthday;
    private String bio;
    private String interests;
}
