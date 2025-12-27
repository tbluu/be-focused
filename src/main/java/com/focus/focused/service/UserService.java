package com.focus.focused.service;

import com.focus.focused.entity.*;
import com.focus.focused.exception.*;
import com.focus.focused.repository.*;
import com.focus.focused.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final SubjectRepository subjectRepository;

    @Transactional
    public User register(RegisterRequest request) {
        if (!request.getPassword().equals(request.getRePassword())) {
            throw new BadRequestException("Mật khẩu xác nhận không khớp.");
        }
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new BadRequestException("Email đã được sử dụng.");
        }
        User user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(request.getPassword())
                .avatar("dog")
                .currentTheme("ocean")
                .totalPoint(0)
                .point(0)
                .streak(0)
                .build();
        User savedUser = userRepository.save(user);

        Subject defaultSub = Subject.builder()
                .name("Tiếng Anh")
                .user(savedUser)
                .build();
        subjectRepository.save(defaultSub);
        return savedUser;
    }

    public User login(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new BadRequestException("Không tìm thấy email."));
        if (!user.getPassword().equals(password)) {
            throw new BadRequestException("Sai mật khẩu.");
        }
        return user;
    }

    public User updateAvatar(Long id, String avatarKey) {
        User user = getById(id);
        user.setAvatar(avatarKey);
        return userRepository.save(user);
    }

    public User updateTheme(Long userId, String theme) {
        User user = getById(userId);
        user.setCurrentTheme(theme);
        return userRepository.save(user);
    }

    public User getById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Không tìm thấy user."));
    }

    public User update(Long id, User request) {
        User user = getById(id);
        user.setUsername(request.getUsername());
        return userRepository.save(user);
    }

    public User updateSettings(Long id, ChangeSettingsRequest request) {
        User user = getById(id);
        if (request.getNewUsername() != null && !request.getNewUsername().isBlank()) {
            user.setUsername(request.getNewUsername());
        }
        if (request.getNewPassword() != null && !request.getNewPassword().isBlank()) {
            user.setPassword(request.getNewPassword());
        }
        return userRepository.save(user);
    }

    @Transactional
    public User addFocusPoints(Long userId, int points) {
        User user = getById(userId);
        user.setPoint((user.getPoint() == null ? 0 : user.getPoint()) + points);
        user.setTotalPoint((user.getTotalPoint() == null ? 0 : user.getTotalPoint()) + points);
        return userRepository.save(user);
    }

    public User save(User user) {
        return userRepository.save(user);
    }
}
