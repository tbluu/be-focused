package com.focus.focused.repository;

import com.focus.focused.entity.StoreItem;
import com.focus.focused.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface StoreItemRepository extends JpaRepository<StoreItem, Long> {
}
