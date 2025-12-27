package com.focus.focused.service;

import com.focus.focused.entity.*;
import com.focus.focused.exception.*;
import com.focus.focused.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubjectService {

    private final SubjectRepository subjectRepository;
    private final UserService userService;

    public Subject create(Long userId, Subject subject) {
        User user = userService.getById(userId);
        subject.setUser(user);
        return subjectRepository.save(subject);
    }

    public List<Subject> getByUser(Long userId) {
        return subjectRepository.findByUserId(userId);
    }

    public void delete(Long id) {
        subjectRepository.deleteById(id);
    }
}

