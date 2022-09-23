package com.kuti.server.main.controller;

import com.kuti.server.main.model.entity.Picture;
import com.kuti.server.main.service.PictureService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Iterator;

@RestController
@RequiredArgsConstructor
@RequestMapping("/picture")
public class PictureController {

    @Autowired
    final public PictureService picService;

    @PostMapping(value = "/save/{userId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ApiOperation(value = "Saves an entry based on it's id.")
    public void createPicture(@PathVariable("userId") int userId, @RequestPart MultipartFile multipartFile) throws Exception {
        picService.create(userId, multipartFile);
    }

    @GetMapping("/get/{pictureId}")
    @ApiOperation(value = "Outputs entry by its id.")
    public Picture readPicture(@PathVariable("pictureId") int pictureId) throws Exception {
        return picService.read(pictureId);
    }

    @GetMapping("/get-all")
    @ApiOperation(value = "Gets all entries from database.")
    public Iterator<Picture> readAllPictures() throws Exception{
        return picService.readAll().iterator();
    }

    @PutMapping("/update/{pictureId}")
    @ApiOperation(value = "Updates an existing entry.")
    public void updatePicture(@PathVariable("pictureId") int pictureId, @RequestPart MultipartFile multipartFile) throws Exception {
        picService.update(pictureId, multipartFile);
    }


    @DeleteMapping("/delete-by-id/{pictureId}")
    @ApiOperation(value = "Deletes entry based on its id.")
    public void deletePictureById(@PathVariable("pictureId") int pictureId) throws Exception{
        picService.deleteById(pictureId);
    }

}
