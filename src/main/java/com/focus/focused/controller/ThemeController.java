package com.focus.focused.controller;

import com.focus.focused.dto.*;
import com.focus.focused.entity.*;
import com.focus.focused.exception.BadRequestException;
import com.focus.focused.repository.*;
import com.focus.focused.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/themes")
@RequiredArgsConstructor
@CrossOrigin
public class ThemeController {

    private final ThemeRepository themeRepository;
    private final UserThemeRepository userThemeRepository;
    private final UserService userService;

    @GetMapping
    public ApiResponse<List<Theme>> getAll() {
        return ApiResponse.success(themeRepository.findAll());
    }

    @GetMapping("/user/{userId}")
    public ApiResponse<List<UserTheme>> getUserThemes(@PathVariable Long userId) {
        return ApiResponse.success(userThemeRepository.findByUserId(userId));
    }

    @PostMapping("/buy")
    public ApiResponse<String> buy(
            @RequestParam Long userId,
            @RequestParam Long themeId
    ) {
        if (userThemeRepository.existsByUserIdAndThemeId(userId, themeId)) {
            throw new BadRequestException("Theme already owned");
        }

        User user = userService.getById(userId);
        Theme theme = themeRepository.findById(themeId)
                .orElseThrow(() -> new BadRequestException("Theme not found"));

        userThemeRepository.save(
                UserTheme.builder()
                        .user(user)
                        .theme(theme)
                        .build()
        );

        return ApiResponse.success("Theme purchased");
    }

}

