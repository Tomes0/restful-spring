package com.kuti.server.main.model;

import com.kuti.server.main.model.entity.Picture;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

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
    private String ownerUser;

}
