package com.realworld.study.web.users;

import com.realworld.study.service.UsersService;
import com.realworld.study.web.users.dto.UsersResponseDto;
import com.realworld.study.web.users.dto.UsersSaveRequestDto;
import com.realworld.study.web.users.dto.UsersUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UsersController {

    @Autowired
    private final UsersService usersService;

    @PostMapping("")
    public UsersResponseDto saveUsers(@RequestBody UsersSaveRequestDto requestDto) {
        return usersService.saveUsers(requestDto);
    }

    @PutMapping("")
    public UsersResponseDto updateUsers(@RequestBody UsersUpdateRequestDto requestDto) {
        return usersService.updateUsers(requestDto);
    }
}
