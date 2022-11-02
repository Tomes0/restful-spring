package com.kuti.server.main.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserReadAllDto {
    private String email;
    private String fullName;
    private Date creationDate;
    private String userName;
    private int userId;
}
