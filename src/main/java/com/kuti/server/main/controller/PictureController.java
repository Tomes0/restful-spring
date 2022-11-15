package com.kuti.server.main.controller;

import com.kuti.server.main.model.PictureSaveDto;
import com.kuti.server.main.model.PictureUpdateDto;
import com.kuti.server.main.model.entity.Picture;
import com.kuti.server.main.service.PictureService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


import java.util.Iterator;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/picture")
public class PictureController {

    @Autowired
    final public PictureService picService;

    @PostMapping(value = "/save/{userId}")
    @ApiOperation(value = "Saves an entry based on it's id.")
    public void createPicture(@PathVariable("userId") int userId, @RequestBody PictureSaveDto req) throws Exception {
        picService.create(userId, req);
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
    public void updatePicture(@PathVariable("pictureId") int pictureId, @RequestPart PictureUpdateDto req) throws Exception {
        picService.update(pictureId, req);
    }


    @DeleteMapping("/delete/{pictureId}")
    @ApiOperation(value = "Deletes entry based on its id.")
    public void deletePictureById(@PathVariable("pictureId") int pictureId) throws Exception{
        picService.deleteById(pictureId);
    }

}
