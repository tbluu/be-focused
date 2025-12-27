package com.focus.focused.repository;

import com.focus.focused.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserThemeRepository extends JpaRepository<UserTheme, Long> {
    List<UserTheme> findByUserId(Long userId);

    boolean existsByUserIdAndThemeId(Long userId, Long themeId);
}
