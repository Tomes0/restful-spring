package com.kuti.server.main.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthRegisterDto {
    private String email;
    private String fullName;
    private String password;
    private String userName;
}
