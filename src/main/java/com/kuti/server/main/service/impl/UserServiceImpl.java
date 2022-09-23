package com.kuti.server.main.service.impl;

import com.kuti.server.main.model.UserUpdateDto;
import com.kuti.server.main.model.entity.Picture;
import com.kuti.server.main.model.entity.Post;
import com.kuti.server.main.model.entity.User;
import com.kuti.server.main.model.UserSaveDto;
import com.kuti.server.main.repository.PictureRepository;
import com.kuti.server.main.repository.PostRepository;
import com.kuti.server.main.repository.UserRepository;
import com.kuti.server.main.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Iterator;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final PictureRepository pictureRepository;
    @Autowired
    private final PostRepository postRepository;

    @Override
    public void create(UserSaveDto req) {
        User user = User.builder()
                .creationDate(new java.sql.Date( new Date().getTime()))
                .userName(req.getUserName())
                .fullName(req.getFullName())
                .email(req.getEmail())
                .pass(req.getPass())
                .build();
        userRepository.save(user);
    }

    @Override
    public User read(Integer id) throws Exception {
        if(userRepository.existsById(id)){
            return userRepository.findById(id).get();
        }
        else throw new Exception("User not found!");
    }

    @Override
    public void update(UserUpdateDto req, int id) throws Exception {
        if(userRepository.existsById(id)){
            User user = User.builder()
                    .userId(id)
                    .userName(req.getUserName())
                    .fullName(req.getFullName())
                    .pass(req.getPass())
                    .email(req.getEmail())
                    .build();
            userRepository.save(user);
        }else throw new Exception("User profile doesn't exist!");
    }

    @Override
    public void delete(User req) throws Exception {
        if(userRepository.existsById(req.getUserId()))
            deleteById(req.getUserId());
        else throw new Exception("User profile doesn't exist!");
    }
    @Override
    public void deleteById(Integer req) throws Exception {
        if(userRepository.existsById(req)){
            try{
                for (Picture picture:userRepository.findById(req).get().getPictureList()) {
                    pictureRepository.delete(picture);
                }
                for (Post post:userRepository.findById(req).get().getPostList()) {
                    postRepository.delete(post);
                }
                userRepository.deleteById(req);
            }catch(Exception e){
                throw new RuntimeException("Error deleting pictures/posts.");
            }
        }
        else throw new Exception("User profile doesn't exist!");
    }

    @Override
    public Iterator<User> readAll() throws Exception {
        if(userRepository.count()!=0){
            return userRepository.findAll().iterator();
        }else throw new Exception("No users.");
    }
}
