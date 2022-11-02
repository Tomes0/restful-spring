package com.kuti.server.main.service;

import com.kuti.server.main.model.UserReadAllDto;
import com.kuti.server.main.model.UserReadDto;
import com.kuti.server.main.model.UserSaveDto;
import com.kuti.server.main.model.UserUpdateDto;
import com.kuti.server.main.model.entity.User;
import org.springframework.stereotype.Service;

import java.util.Iterator;


@Service
public interface UserService {

    /**
     * Used for adding a new user to the database.
     * <p>
     * The UserSaveDto expects all it's fields to be
     * filled while the userId will be auto assigned.
     *
     * @param req UserSaveDto with all of its fields filled
     */
    void create(UserSaveDto req);

    /**
     * Used for getting user details of a specific userId.
     * <p>
     * If the entry specified by the userID
     * is empty it throws an Exception to signal that.
     *
     * @param id userId to read
     * @return User object with the details
     * @throws Exception if user not found
     */
    UserReadDto read(Integer id) throws Exception;

    /**
     * Used for updating an existing User entry.
     * This is done through the UserUpdateDto class.
     * <p>
     * If the entry specified by the userID
     * is empty it throws an Exception to signal that.
     *
     * @param req UserUpdateDto with the desired changes
     * @param id
     * @throws Exception if user not found
     */
    void update(UserUpdateDto req, int id) throws Exception;

    /**
     *  Used for deleting a user,
     *  this is getting passed as User.
     *  <p>
     *  If the entry specified User doesn't exist
     *  it throws an exception for that.
     * @param req the User to be deleted
     * @throws Exception if user not found
     */
    void delete(User req) throws Exception;

    /**
     *  Used for deleting a user by their userId,
     *  this is getting passed as Integer.
     *  <p>
     *  If the entry specified userId doesn't exist
     *  it throws an exception for that.
     * @param req the userId of the User to be deleted
     * @throws Exception if user not found
     */
    void deleteById(Integer req) throws Exception;

    /**
     * Used for getting all the users and their details.
     * @return Iterator in which you have all the users
     * @throws Exception if no users present
     */
    Iterator<UserReadAllDto> readAll() throws Exception;
}
