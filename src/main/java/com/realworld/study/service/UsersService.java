package com.realworld.study.service;

import com.realworld.study.domain.users.Users;
import com.realworld.study.domain.users.UsersRepository;
import com.realworld.study.web.users.dto.UsersResponseDto;
import com.realworld.study.web.users.dto.UsersSaveRequestDto;
import com.realworld.study.web.users.dto.UsersUpdateRequestDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UsersService {

    private final UsersRepository usersRepository;

    public UsersResponseDto saveUsers(UsersSaveRequestDto saveUser) {
        Optional<Users> user = usersRepository.findOneByEmail(saveUser.getEmail());
        Users saved;
        if (user.isEmpty()) {
            saved = usersRepository.save(saveUser.toEntity());
        } else {
            throw new DuplicateKeyException("해당 이메일이 존재합니다. email => " + saveUser.getEmail());
        }
        return new UsersResponseDto(saved);
    }

    @Transactional
    public UsersResponseDto updateUsers(UsersUpdateRequestDto updateUser) {
        Users users = usersRepository.findOneByEmail(updateUser.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자 이메일입니다. email => " + updateUser.getEmail()));
        String updateBio = Optional.ofNullable(updateUser.getBio()).orElse(users.getBio());
        String updateImage = Optional.ofNullable(updateUser.getImage()).orElse(users.getImage());
        users.updateUsers(updateBio, updateImage);
        return new UsersResponseDto(users);
    }

}
