package com.focus.focused.controller;

import com.focus.focused.entity.*;
import com.focus.focused.exception.BadRequestException;
import com.focus.focused.service.*;
import com.focus.focused.dto.*;
import com.focus.focused.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/store")
@RequiredArgsConstructor
@CrossOrigin
public class StoreController {
    private final StoreItemRepository storeItemRepository;
    private final UserStoreItemRepository userStoreItemRepository;
    private final UserService userService;

    @GetMapping("/items")
    public ApiResponse<List<StoreItem>> getItems() {
        return ApiResponse.success(storeItemRepository.findAll());
    }

    @GetMapping("/owned/{userId}")
    public ApiResponse<List<Long>> getOwnedItemIds(@PathVariable Long userId) {
        return ApiResponse.success(userStoreItemRepository.findByUserId(userId)
                .stream()
                .map(u -> u.getStoreItem().getId())
                .collect(Collectors.toList()));
    }

    @PostMapping("/buy")
    @Transactional
    public ApiResponse<User> buyItem(@RequestParam Long userId, @RequestParam Long itemId) {
        User user = userService.getById(userId);
        StoreItem item = storeItemRepository.findById(itemId)
                .orElseThrow(() -> new BadRequestException("Vật phẩm không tồn tại"));

        if (user.getPoint() == null) user.setPoint(0);

        if (user.getPoint() < item.getPrice()) {
            throw new BadRequestException("Bạn không đủ điểm để đổi vật phẩm này!");
        }
        user.setPoint(user.getPoint() - item.getPrice());
        userService.save(user);
        userStoreItemRepository.save(UserStoreItem.builder()
                .user(user)
                .storeItem(item)
                .purchasedAt(LocalDateTime.now())
                .build());
        return ApiResponse.success(user);
    }
}