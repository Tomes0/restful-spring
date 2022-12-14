package com.kuti.server.main.model;

import com.kuti.server.main.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostReadDto {
    private int id;
    private String title;
    private String content;
    private LocalDateTime creationDate;
    private LocalDateTime lastModificationDate;
    private int ownerId;
    private User ownerObject;

}
