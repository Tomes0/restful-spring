package com.kuti.server.main.service.impl;

import com.kuti.server.main.model.entity.Picture;
import com.kuti.server.main.model.entity.User;
import com.kuti.server.main.repository.PictureRepository;
import com.kuti.server.main.repository.UserRepository;
import com.kuti.server.main.service.PictureService;
import com.kuti.server.main.util.ImageUtility;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class PictureServiceImpl implements PictureService {

    @Autowired
    private final PictureRepository pictureRepository;

    @Autowired
    private final UserRepository userRepository;

    @Override
    public void create (int id, MultipartFile multipartFile) throws Exception {
        if(userRepository.existsById(id)){
            User owner = userRepository.findById(id).get();
            String extension = multipartFile.getContentType().split("/")[multipartFile.getContentType().split("/").length-1];
            Picture picture = Picture.builder()
                    .extension(extension)
                    .bytea(ImageUtility.compressImage(multipartFile.getBytes()))
                    .ownerId(owner)
                    .build();
            pictureRepository.save(picture);
        }else throw new Exception("User doesn't exist!");

    }

    @Override
    public Picture read(Integer req) throws Exception {
        if(pictureRepository.existsById(req)){
            Picture picture = Picture.builder()
                    .bytea(ImageUtility.decompressImage (pictureRepository.findById(req).get().getBytea()))
                    .pictureId(pictureRepository.findById(req).get().getPictureId())
                    .ownerId(pictureRepository.findById(req).get().getOwnerId())
                    .extension(pictureRepository.findById(req).get().getExtension())
                    .build();
            return picture;
        }
        else throw new Exception("Picture not found!");
    }

    @Override
    public Iterable<Picture> readAll() throws Exception{
        if(pictureRepository.count()!=0){
            return pictureRepository.findAll();
        }else throw new Exception("No pictures!");
    }

    @Override
    public void update(int id, MultipartFile multipartFile) throws Exception {
        if(pictureRepository.existsById(id)){
            Picture picture = Picture.builder()
                    .bytea(ImageUtility.compressImage(multipartFile.getBytes()))
                    .pictureId(pictureRepository.findById(id).get().getPictureId())
                    .ownerId(pictureRepository.findById(id).get().getOwnerId())
                    .extension(pictureRepository.findById(id).get().getExtension())
                    .pictureId(pictureRepository.findById(id).get().getPictureId())
                    .build();
            pictureRepository.save(picture);
        }
        else throw new Exception("Picture not found!");
    }

    @Override
    public void delete(Picture req) throws Exception {
        if(pictureRepository.existsById(req.getPictureId())){
            pictureRepository.delete(req);
        }else throw new Exception("Picture not found!");
    }
    @Override
    public void deleteById(Integer req) throws Exception {
        if(pictureRepository.existsById(req)){
            pictureRepository.deleteById(req);
        }else throw new Exception("Picture not found");
    }

}
