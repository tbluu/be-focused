package com.focus.focused.dto;

import lombok.Data;

@Data
public class RegisterRequest {
    private String username;
    private String email;
    private String password;
    private String rePassword;
    private String avatar;
}
