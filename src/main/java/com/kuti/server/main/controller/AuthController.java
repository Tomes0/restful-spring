package com.kuti.server.main.controller;

import com.kuti.server.main.model.AuthLoginDto;
import com.kuti.server.main.model.AuthRegisterDto;
import com.kuti.server.main.model.UserReadDto;
import com.kuti.server.main.service.AuthService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    final public AuthService authService;

    @PostMapping("/register")
    @ApiOperation(value = "Adds new entry to database.")
    public int registerUser(@RequestBody AuthRegisterDto authRegisterDto) {
        return authService.register(authRegisterDto);
    }

    @SneakyThrows
    @PostMapping("/login")
    @ApiOperation(value = "Logs in if exits.")
    public UserReadDto loginUser(@RequestBody AuthLoginDto authLoginDto) {
        return authService.login(authLoginDto);
    }
}
