package com.kuti.server.main.service;

import com.kuti.server.main.model.PictureSaveDto;
import com.kuti.server.main.model.PictureUpdateDto;
import com.kuti.server.main.model.entity.Picture;
import org.springframework.stereotype.Service;

@Service
public interface PictureService {

    /**
     * The function uses the ownerId to specify
     * the owner of the post and appends it to their list.
     *
     * @param id
     * @param req
     */
    void create (int id, PictureSaveDto req) throws Exception;

    /**
     * @param req
     * @return
     */
    Picture read(Integer req) throws Exception;

    /**
     * Reads all available pictures.
     *
     * @return Iterrator with all pictures
     * @throws Exception No pictures available
     */
    Iterable<Picture> readAll() throws Exception;

    /**
     * Used for updating an existing entry.
     *
     * @param id
     * @param req
     * @throws Exception if no picture found with provided id
     */

    void update(int id, PictureUpdateDto req) throws Exception;

    /**
     * Used for deleting picture.
     * @param req a picture object
     * @throws Exception if picture not found
     */
    void delete(Picture req) throws Exception;

    /**
     * Used for deleting a picture by its id.
     * @param req id of picture
     * @throws Exception if id doesn't point to existing entry
     */
    void deleteById(Integer req) throws Exception;
}