package com.openphilosophy.api.services;

import com.openphilosophy.api.models.user.dto.LoginRequestDTO;
import com.openphilosophy.api.models.user.User;
import com.openphilosophy.api.models.user.UserMapper;
import com.openphilosophy.api.models.user.dto.UserRequestDTO;
import com.openphilosophy.api.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AuthService {

    private final String BAD_REQ_MESSAGE = "Verifique seus dados e tente novamente.";
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public AuthService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public User login(LoginRequestDTO body) {
        try {
            User user = userRepository.findByEmail(body.email()).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, BAD_REQ_MESSAGE));
            if (user.getPassword().equals(body.password())) {
                return user;
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, BAD_REQ_MESSAGE);
            }
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, BAD_REQ_MESSAGE);
        }
    }

    public User createUser(UserRequestDTO data) {
        User user = userMapper.userRequestDtoToUser(data);
        return userRepository.save(user);
    }
}
