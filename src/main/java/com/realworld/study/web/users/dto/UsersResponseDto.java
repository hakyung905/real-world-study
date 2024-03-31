package com.realworld.study.web.users.dto;

import com.realworld.study.domain.users.Users;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UsersResponseDto {

    private String email;

    private String token;

    private String username;

    private String bio;

    private String image;

    public UsersResponseDto(Users users) {
        this.email = users.getEmail();
        this.username = users.getUsername();
        this.bio = users.getBio();
        this.image = users.getImage();
    }
}
