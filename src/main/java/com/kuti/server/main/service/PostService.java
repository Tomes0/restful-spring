package com.kuti.server.main.service;

import com.kuti.server.main.model.PostReadDto;
import com.kuti.server.main.model.PostSaveDto;
import com.kuti.server.main.model.PostUpdateDto;
import com.kuti.server.main.model.entity.Post;
import org.springframework.stereotype.Service;

import java.util.Iterator;

@Service
public interface PostService {

    /**
     * Used for creating posts. The goes through
     * as PostSaveDto.
     * <p>
     * The function uses the ownerId to specify
     * the owner of the post and appends it to their list.
     * The content is a simple String.
     *
     * @param req post to be created and appended to user
     * @param id
     */
    void create(PostSaveDto req, int id);

    /**
     * Used for getting the details of a specific post.
     * <p>
     * Uses an Integer to find the post.
     *
     * @param req postId
     * @return Post object with the details
     * @throws Exception if the post is not found
     */
    PostReadDto read(Integer req) throws Exception;

    /**
     * Used for updating content of post. It
     * automatically changes the lastModifiedDate to system time.
     * <p>
     * The Dto contains the postId and the content.
     *
     * @param req post that you want to change
     * @param id
     * @throws Exception if the post is not found
     */
    void update(PostUpdateDto req, int id) throws Exception;

    /**
     * Used for deleting a specific post. It also
     * removes it from the User associated with the post.
     *
     *
     * @param req postId of post to be deleted
     * @throws Exception if the post is not found
     */
    void deleteById(Integer req) throws Exception;

    /**
     * Used for deleting a specific post.
     * @param req a Post object
     * @throws Exception if post not found
     */
    void delete(Post req) throws Exception;

    /**
     * Used for getting all the posts
     *
     * @return Iterator in which you have all the posts
     * @throws Exception if there are no posts
     */
    Iterator<PostReadDto> readAll()throws Exception;
}
