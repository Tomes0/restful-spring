package com.kuti.server.main.controller;

import com.kuti.server.main.model.*;
import com.kuti.server.main.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    final public UserService userService;

    @PostMapping("/save")
    @ApiOperation(value = "Adds new entry to database.")
    public void createUser(@RequestBody UserSaveDto req) {
        userService.create(req);
    }

    @GetMapping("/get/{userId}")
    @ApiOperation(value = "Gets an entry based on it's id.")
    public UserReadDto readUser(@PathVariable("userId") Integer userId) throws Exception {
        return userService.read(userId);
    }

    @GetMapping("/get-all")
    @ApiOperation(value = "Gets all entries from database.")
    public Iterator<UserReadAllDto> readAllUser()throws Exception{
        return userService.readAll();
    }

    @PutMapping("/update/{userId}")
    @ApiOperation(value = "Updates an existing User entry.")
    public void update(@PathVariable("userId") int userId, @RequestBody UserUpdateDto user) throws Exception {
        userService.update(user, userId);
    }
    @PostMapping("/profilePicture/{userId}")
    @ApiOperation(value = "Create a profile picture.")
    public void profilePicture(@PathVariable("userId") int userId, @RequestBody UserProfilePictureDto req){
        System.out.println(req.getBytea());
        userService.profilePicture(userId, req);
    }

    @PutMapping("/profilePicture/{userId}/update")
    @ApiOperation(value = "Updates a profile picture.")
    public void profilePictureUpdate(@PathVariable("userId") int userId, @RequestBody UserProfilePictureDto req){
        userService.profilePicture(userId, req);
    }


    @PutMapping("/updatePassword/{userId}")
    @ApiOperation(value = "Updates an existing User entry.")
    public void update(@PathVariable("userId") int userId, @RequestBody UserUpdatePasswordDto req) throws Exception {
        userService.updatePassword(req, userId);
    }

    @DeleteMapping("/delete/{userId}")
    @ApiOperation(value = "Deletes entry based on its id.")
    public void delete(@PathVariable("userId") int userId) throws Exception {
        userService.deleteById(userId);
    }
}
