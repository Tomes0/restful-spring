package com.kuti.server.main.service.impl;

import com.kuti.server.main.model.AuthLoginDto;
import com.kuti.server.main.model.AuthRegisterDto;
import com.kuti.server.main.model.UserReadDto;
import com.kuti.server.main.model.UserSaveDto;
import com.kuti.server.main.model.entity.User;
import com.kuti.server.main.repository.UserRepository;
import com.kuti.server.main.service.AuthService;
import com.kuti.server.main.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicBoolean;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    @Autowired
    private final UserRepository userRepository;

    private final UserService userService;

    @SneakyThrows
    @Override
    public int register(AuthRegisterDto auth) {
        UserSaveDto newUser = new UserSaveDto().builder()
                .username(auth.getUsername())
                .email(auth.getEmail())
                .password(auth.getPassword())
                .fullname(auth.getFullName())
                .build();
        userService.create(newUser);
        return 0;
    }

    @Override
    public UserReadDto login(AuthLoginDto auth) throws Exception {
        Iterable<User> users = userRepository.findAll();
        AtomicBoolean correct = new AtomicBoolean(false);
        String password = auth.getPassword();

        for (User user : users) {
            if (auth.getEmail().equals(user.getEmail())) {
                if (password.equals(user.getPass())) {
                    return userService.read(user.getUserId());
                }
            }
        }

        return null;
    }
}
