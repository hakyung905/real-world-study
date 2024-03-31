package com.realworld.study.web.users.dto;

import com.realworld.study.domain.users.Users;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UsersSaveRequestDto {

    private String username;

    private String email;

    private String password;

    public Users toEntity(){
        return Users.builder()
                .username(username)
                .email(email)
                .password(password)
                .build();
    }
}
