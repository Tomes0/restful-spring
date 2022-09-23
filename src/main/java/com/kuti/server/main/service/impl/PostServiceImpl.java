package com.kuti.server.main.service.impl;


import com.kuti.server.main.model.PostSaveDto;
import com.kuti.server.main.model.PostUpdateDto;
import com.kuti.server.main.model.entity.Post;
import com.kuti.server.main.model.entity.User;
import com.kuti.server.main.repository.PostRepository;
import com.kuti.server.main.repository.UserRepository;
import com.kuti.server.main.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    @Autowired
    private final PostRepository postRepository;
    @Autowired
    private final UserRepository userRepository;

    @Override
    public void create(PostSaveDto req, int id) {
        User owner = userRepository.findById(id).get();
        Post post = Post.builder()
                .ownerId(owner)
                .content(req.getContent())
                .creationDate(new java.sql.Date( new Date().getTime()))
                .lastModificationDate(new java.sql.Date( new Date().getTime()))
                .build();
        postRepository.save(post);
    }

    @Override
    public Post read(Integer req) throws Exception {
        if(postRepository.existsById(req)){
            return postRepository.findById(req).get();
        }else throw new Exception("Post not found!");

    }

    @Override
    public void update(PostUpdateDto req, int id) throws Exception {
        if(postRepository.existsById(id) &&
                userRepository.existsById(postRepository.findById(id).get().getOwnerId().getUserId())){


            User owner = postRepository.findById(id).get().getOwnerId();
            Date creationDate = postRepository.findById(id).get().getCreationDate();
            Post post = Post.builder()
                    .lastModificationDate(new java.sql.Date( new Date().getTime()))
                    .creationDate(postRepository.findById(id).get().getCreationDate())
                    .content(req.getContent())
                    .postId(id)
                    .ownerId(owner)
                    .build();
            postRepository.save(post);
        }else throw new Exception("Post/User not found!");
    }

    @Override
    public void deleteById(Integer req) throws Exception {
        if(postRepository.existsById(req))
            postRepository.deleteById(req);
        else throw new Exception("Post not found!");
    }

    @Override
    public void delete(Post req) throws Exception {
        if(postRepository.existsById(req.getPostId())){
            postRepository.delete(req);
        }else throw new Exception("Post not found!");
    }

    @Override
    public Iterable<Post> readAll() throws Exception {
        if(postRepository.count()!=0){
            return postRepository.findAll();
        }else throw new Exception("No posts.");
    }
}