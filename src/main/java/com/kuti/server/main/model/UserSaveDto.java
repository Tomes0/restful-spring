package com.kuti.server.main.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSaveDto {
    private String email;
    private String fullName;
    private String pass;
    private String userName;
}
