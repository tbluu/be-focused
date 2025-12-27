package com.focus.focused.controller;

import com.focus.focused.entity.*;
import com.focus.focused.service.*;
import com.focus.focused.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
@CrossOrigin
public class TaskController {
    private final TaskService taskService;

    @GetMapping("/today/{userId}")
    public ApiResponse<List<Task>> getTodayTasks(@PathVariable Long userId) {
        return ApiResponse.success(taskService.getTodayTasks(userId));
    }

    @PostMapping("/today/{userId}")
    public ApiResponse<Task> createDailyTask(@PathVariable Long userId, @RequestParam String title) {
        return ApiResponse.success(taskService.createDailyTask(userId, title));
    }

    @PatchMapping("/complete/{taskId}")
    public ApiResponse<User> completeTask(@PathVariable Long taskId) {
        return ApiResponse.success(taskService.completeTask(taskId));
    }
}

