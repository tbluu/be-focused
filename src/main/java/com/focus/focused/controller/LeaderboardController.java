package com.focus.focused.controller;

import com.focus.focused.dto.*;
import com.focus.focused.entity.*;
import com.focus.focused.repository.*;
import com.focus.focused.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/leaderboard")
@RequiredArgsConstructor
@CrossOrigin
public class LeaderboardController {

    private final UserRepository userRepository;

    @GetMapping
    public ApiResponse<List<User>> top50() {
        return ApiResponse.success(userRepository.findTop50ByOrderByTotalPointDesc());
    }
}
