package com.kuti.server.main.controller;

import com.kuti.server.main.model.PostReadDto;
import com.kuti.server.main.model.PostSaveDto;
import com.kuti.server.main.model.PostUpdateDto;
import com.kuti.server.main.model.entity.Post;
import com.kuti.server.main.service.PostService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post")
public class PostController {
    @Autowired
    final public PostService postService;

    @PostMapping("/save/{userId}")
    @ApiOperation(value = "Adds a new entry to database.")
    public void createPost(@PathVariable("userId") int userId,@RequestBody PostSaveDto post) {
        postService.create(post,userId);
    }

    @GetMapping("/get/{postId}")
    @ApiOperation(value = "Gets an entry based on it's id.")
    public PostReadDto readPost(@PathVariable("postId") int postId) throws Exception {
        return postService.read(postId);
    }

    @GetMapping("/get-all")
    @ApiOperation(value = "Gets all entries from database.")
    public Iterator<PostReadDto> readAllPost()throws Exception{
        return postService.readAll();
    }

    @PutMapping("/update/{postId}")
    @ApiOperation(value = "Updates an existing entry.")
    public void updatePost(@PathVariable("postId") int postId, @RequestBody PostUpdateDto post) throws Exception {
        postService.update(post,postId);
    }

    @DeleteMapping("/delete-by-id/{postId}")
    @ApiOperation(value = "Deletes entry based on its id.")
    public void deletePost(@PathVariable("postId") int postId) throws Exception {
        postService.deleteById(postId);
    }
}
