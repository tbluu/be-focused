package com.focus.focused.controller;

import com.focus.focused.entity.*;
import com.focus.focused.service.*;
import com.focus.focused.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subjects")
@RequiredArgsConstructor
@CrossOrigin
public class SubjectController {

    private final SubjectService subjectService;

    @PostMapping("/{userId}")
    public ApiResponse<Subject> create(
            @PathVariable Long userId,
            @RequestBody Subject subject
    ) {
        return ApiResponse.success(subjectService.create(userId, subject));
    }

    @GetMapping("/{userId}")
    public ApiResponse<List<Subject>> getAll(@PathVariable Long userId) {
        return ApiResponse.success(subjectService.getByUser(userId));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> delete(@PathVariable Long id) {
        subjectService.delete(id);
        return ApiResponse.success("Deleted");
    }
}

