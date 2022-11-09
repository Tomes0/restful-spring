package com.kuti.server.main.service;

import com.kuti.server.main.model.AuthLoginDto;
import com.kuti.server.main.model.AuthRegisterDto;
import com.kuti.server.main.model.UserReadDto;

public interface AuthService {

    int register(AuthRegisterDto auth);
    UserReadDto login(AuthLoginDto auth) throws Exception;

}
