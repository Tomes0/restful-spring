package com.kuti.server.main.service.impl;


import com.kuti.server.main.model.PostReadDto;
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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


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
        Post newPost = Post.builder()
                .title(req.getTitle())
                .content(req.getContent())
                .creationDate(LocalDateTime.now())
                .lastModificationDate(LocalDateTime.now())
                .ownerId(owner.getUserId())
                .ownerObject(owner)
                .build();
        postRepository.save(newPost);
    }

    @Override
    public PostReadDto read(Integer req) throws Exception {
        if(postRepository.existsById(req)){
            Post post = postRepository.findById(req).get();
            return new PostReadDto(
                    post.getPostId(),
                    post.getTitle(),
                    post.getContent(),
                    post.getCreationDate(),
                    post.getLastModificationDate(),
                    post.getOwnerId(),
                    post.getOwnerObject());
        }else throw new Exception("Post not found!");

    }

    @Override
    public void update(PostUpdateDto req, int id) throws Exception {
        if(postRepository.existsById(id) &&
                userRepository.existsById(postRepository.findById(id).get().getOwnerObject().getUserId())){

            User owner = postRepository.findById(id).get().getOwnerObject();
            Post post = Post.builder()
                    .lastModificationDate(LocalDateTime.now())
                    .creationDate(postRepository.findById(id).get().getCreationDate())
                    .content(req.getContent())
                    .postId(id)
                    .ownerObject(owner)
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
    public Iterator<PostReadDto> readAll() throws Exception {
        List<PostReadDto> posts = new ArrayList<>();
        if(postRepository.count()!=0){
            for (Post post: postRepository.findAll()) {
                posts.add(new PostReadDto(
                            post.getPostId(),
                            post.getTitle(),
                            post.getContent(),
                            post.getCreationDate(),
                            post.getLastModificationDate(),
                            post.getOwnerId(),
                            post.getOwnerObject()));
            }
            return posts.iterator();
        }else throw new Exception("No posts.");
    }
}
