package com.focus.focused.repository;

import com.focus.focused.entity.StoreItem;
import com.focus.focused.entity.UserStoreItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserStoreItemRepository extends JpaRepository<UserStoreItem, Long> {
    List<UserStoreItem> findByUserId(Long userId);
}
