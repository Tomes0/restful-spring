package com.kuti.server.main.model;

import com.kuti.server.main.model.entity.Picture;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserProfilePictureDto {
    private String bytea;
}
