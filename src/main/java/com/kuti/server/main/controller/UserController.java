package com.kuti.server.main.controller;

import com.kuti.server.main.model.UserReadAllDto;
import com.kuti.server.main.model.UserReadDto;
import com.kuti.server.main.model.UserSaveDto;
import com.kuti.server.main.model.UserUpdateDto;
import com.kuti.server.main.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;

@RestController
@RequiredArgsConstructor
@CrossOrigin()
@RequestMapping("/user")
public class UserController {
    @Autowired
    final public UserService userService;

    @PostMapping("/save")
    @ApiOperation(value = "Adds new entry to database.")
    public void createUser(@RequestBody UserSaveDto userSaveDto) {
        userService.create(userSaveDto);
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
    public void updateUser(@PathVariable("userId") int userId, @RequestBody UserUpdateDto user) throws Exception {
        userService.update(user, userId);
    }

    @DeleteMapping("/delete-by-id/{userId}")
    @ApiOperation(value = "Deletes entry based on its id.")
    public void deleteById(@PathVariable("userId") int userId) throws Exception {
        userService.deleteById(userId);
    }
}
