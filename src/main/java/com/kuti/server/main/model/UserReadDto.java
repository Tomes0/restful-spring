package com.kuti.server.main.model;

import com.kuti.server.main.model.entity.Picture;
import com.kuti.server.main.model.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserReadDto {
    private String email;
    private String fullName;
    private LocalDateTime creationDate;
    private String userName;
    private int userId;
    private List<Post> postList;
    private List<Picture> pictureList;
}
