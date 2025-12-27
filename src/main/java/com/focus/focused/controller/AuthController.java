package com.focus.focused.controller;

import com.focus.focused.dto.*;
import com.focus.focused.entity.*;
import com.focus.focused.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin
public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    public ApiResponse<User> register(@RequestBody RegisterRequest request) {
        User savedUser = userService.register(request);
        return ApiResponse.success(savedUser);
    }

    @PostMapping("/login")
    public ApiResponse<User> login(@RequestBody User user) {
        User loggedIn = userService.login(user.getEmail(), user.getPassword());
        return ApiResponse.success(loggedIn);
    }
}

