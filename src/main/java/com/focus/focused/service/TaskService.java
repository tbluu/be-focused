package com.focus.focused.service;

import com.focus.focused.entity.*;
import com.focus.focused.exception.*;
import com.focus.focused.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final UserService userService;

    public Task createDailyTask(Long userId, String title) {
        LocalDateTime start = LocalDate.now().atStartOfDay();
        LocalDateTime end = LocalDate.now().atTime(LocalTime.MAX);

        long count = taskRepository.countByUserIdAndCreatedAtBetween(userId, start, end);
        if (count >= 5) {
            throw new BadRequestException("Bạn đã đạt giới hạn 5 task cho hôm nay!");
        }

        User user = userService.getById(userId);
        Task task = Task.builder()
                .title(title)
                .user(user)
                .completed(false)
                .build();
        return taskRepository.save(task);
    }

    public List<Task> getTodayTasks(Long userId) {
        LocalDateTime start = LocalDate.now().atStartOfDay();
        LocalDateTime end = LocalDate.now().atTime(LocalTime.MAX);
        return taskRepository.findByUserIdAndCreatedAtBetween(userId, start, end);
    }

    @Transactional
    public User completeTask(Long taskId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new BadRequestException("Không tìm thấy task"));

        if (Boolean.TRUE.equals(task.getCompleted())) {
            throw new BadRequestException("Task này đã hoàn thành trước đó");
        }

        task.setCompleted(true);
        taskRepository.save(task);

        return userService.addFocusPoints(task.getUser().getId(), 5);
    }
}

