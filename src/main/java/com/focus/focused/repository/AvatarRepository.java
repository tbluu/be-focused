package com.focus.focused.repository;

import com.focus.focused.entity.Avatar;
import com.focus.focused.entity.Theme;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AvatarRepository extends JpaRepository<Avatar, Long> {
}
