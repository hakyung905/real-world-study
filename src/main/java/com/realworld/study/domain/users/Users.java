package com.realworld.study.domain.users;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String email;

    private String bio;

    private String image;

    private String password;

    @Builder
    public Users(String username, String email, String bio, String image, String password){
        this.username = username;
        this.email = email;
        this.bio = bio;
        this.image = image;
        this.password = password;
    }

    public void updateUsers(String bio, String image){
        this.bio = bio;
        this.image = image;
    }
}
