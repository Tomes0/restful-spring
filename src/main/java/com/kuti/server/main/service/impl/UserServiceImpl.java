package com.kuti.server.main.service.impl;

import com.kuti.server.main.model.*;
import com.kuti.server.main.model.entity.Picture;
import com.kuti.server.main.model.entity.Post;
import com.kuti.server.main.model.entity.User;
import com.kuti.server.main.repository.PictureRepository;
import com.kuti.server.main.repository.PostRepository;
import com.kuti.server.main.repository.UserRepository;
import com.kuti.server.main.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
        for (User user: userRepository.findAll()) {
            if(user.getEmail().equals(req.getEmail())){
                throw new RuntimeException("Email already taken!");
            }
        }
        User user = User.builder()
                .creationDate(LocalDateTime.now())
                .userName(req.getUserName())
                .fullName(req.getFullName())
                .email(req.getEmail())
                .pass(req.getPass())
                .build();
        userRepository.save(user);
    }


    @Override
    public void update(UserUpdateDto req, int id) throws Exception {
        if (!userRepository.existsById(id)) {
            throw new Exception("User profile doesn't exist!");
        } else {
            for (User user: userRepository.findAll()) {
                if(user.getEmail().equals(req.getEmail())){
                    if(id == user.getUserId()) {
                        continue;
                    }
                    else {
                        throw new RuntimeException("Email already taken!");
                    }

                }
            }
            User origUser = userRepository.findById(id).get();

            origUser.setUserName(req.getUserName());
            origUser.setEmail(req.getEmail());
            origUser.setFullName(req.getFullName());

            userRepository.save(origUser);
        }
    }

    @Override
    public void delete(User req) throws Exception {
        if (userRepository.existsById(req.getUserId()))
            deleteById(req.getUserId());
        else throw new Exception("User profile doesn't exist!");
    }

    @Override
    public void deleteById(Integer req) throws Exception {
        if (userRepository.existsById(req)) {
            try {
                for (Picture picture : userRepository.findById(req).get().getPictureList()) {
                    pictureRepository.delete(picture);
                }
                for (Post post : userRepository.findById(req).get().getPostList()) {
                    postRepository.delete(post);
                }
                userRepository.deleteById(req);
            } catch (Exception e) {
                throw new RuntimeException("Error deleting pictures/posts.");
            }
        } else throw new Exception("User profile doesn't exist!");
    }

    @Override
    public Iterator<UserReadAllDto> readAll() throws Exception {
        if (userRepository.count() != 0) {
            List<UserReadAllDto> allUsers = new ArrayList<>();
            for (User user : userRepository.findAll()) {
                allUsers.add(
                        new UserReadAllDto(
                            user.getEmail(),
                            user.getFullName(),
                            user.getCreationDate(),
                            user.getUserName(),
                            user.getUserId())
                );
            }
            return allUsers.iterator();

        } else throw new Exception("No users.");
    }

    @Override
    public void updatePassword(UserUpdatePasswordDto req, int userId) {
        if(userRepository.existsById(userId)){
            User origUser = userRepository.findById(userId).get();
            origUser.setPass(req.getPassword());
            userRepository.save(origUser);
        }else throw new RuntimeException("User not found!");
    }

    @Override
    public void profilePicture(int userId, UserProfilePictureDto req) {
        if(userRepository.existsById(userId)){
            User origUser = userRepository.findById(userId).get();
            origUser.setProfilePicture(req.getBytea());
            userRepository.save((origUser));

        }else throw new RuntimeException("User not found");
    }

    @Override
    public UserReadDto read(Integer id) throws Exception {
        if(userRepository.existsById(id)){
            User user = userRepository.findById(id).get();
            UserReadDto userResponse = UserReadDto.builder()
                    .email(user.getEmail())
                    .fullName(user.getFullName())
                    .userName(user.getUserName())
                    .creationDate(user.getCreationDate())
                    .profilePicture(user.getProfilePicture())
                    .userId(user.getUserId())
                    .pictureList(user.getPictureList())
                    .postList(user.getPostList())
                    .password(user.getPass())
                    .build();
            return userResponse;
        }
        else throw new Exception("User not found!");
    }
}
