package com.realworld.study.web.users.dto;

import com.realworld.study.domain.users.Users;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UsersUpdateRequestDto {
    private String email;
    private  String bio;
    private String image;

    public Users toEntity(){
        return Users.builder()
                .email(email)
                .bio(bio)
                .image(image)
                .build();
    }
}
