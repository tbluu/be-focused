package com.focus.focused.dto;

import lombok.Data;

@Data
public class ChangeSettingsRequest {
    private String newUsername;
    private String newPassword;
}
