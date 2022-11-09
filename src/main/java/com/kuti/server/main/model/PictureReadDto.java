package com.kuti.server.main.model;

import com.kuti.server.main.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PictureReadDto {
    private String picture;
    private String extension;
    private int ownerId;
    private int pictureId;
    private User ownerObject;
}
