package com.focus.focused.controller;

import com.focus.focused.entity.*;
import com.focus.focused.repository.*;
import com.focus.focused.service.*;
import com.focus.focused.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@CrossOrigin
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public ApiResponse<User> getUser(@PathVariable Long id) {
        return ApiResponse.success(userService.getById(id));
    }

    @PutMapping("/{id}")
    public ApiResponse<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        return ApiResponse.success(userService.update(id, user));
    }

    @PatchMapping("/{id}/avatar")
    public ApiResponse<User> updateAvatar(@PathVariable Long id, @RequestParam String avatar) {
        return ApiResponse.success(userService.updateAvatar(id, avatar));
    }

    @PatchMapping("/{id}/settings")
    public ApiResponse<User> updateSettings(
            @PathVariable Long id,
            @RequestBody ChangeSettingsRequest request) {
        return ApiResponse.success(userService.updateSettings(id, request));
    }

    @PatchMapping("/{id}/theme")
    public ApiResponse<User> updateTheme(@PathVariable Long id, @RequestParam String theme) {
        return ApiResponse.success(userService.updateTheme(id, theme));
    }

    @PostMapping("/{id}/add-points")
    public ApiResponse<User> addPoints(@PathVariable Long id, @RequestParam int amount) {
        return ApiResponse.success(userService.addFocusPoints(id, amount));
    }
}

