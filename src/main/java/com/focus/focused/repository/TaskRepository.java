package com.focus.focused.repository;

import com.focus.focused.entity.Task;import com.focus.focused.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByUserIdAndCreatedAtBetween(Long userId, LocalDateTime start, LocalDateTime end);

    long countByUserIdAndCreatedAtBetween(Long userId, LocalDateTime start, LocalDateTime end);
}
