package com.kuti.server.main.repository;

import com.kuti.server.main.model.entity.Picture;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PictureRepository extends CrudRepository<Picture, Integer> {


}
