package com.openphilosophy.api.services;

import java.util.List;
import java.util.UUID;

import com.openphilosophy.api.models.user.UserMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.openphilosophy.api.models.user.User;
import com.openphilosophy.api.models.user.dto.UserResponseDTO;
import com.openphilosophy.api.models.user.dto.UserRequestDTO;
import com.openphilosophy.api.repositories.UserRepository;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;

    public UserService(UserMapper userMapper, UserRepository userRepository) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User getById(String id) {
        UUID userId = UUID.fromString(id);
        return userRepository.findById(userId).orElse(null);
    }

    public User update(String id, UserRequestDTO data) {
        try {
            UUID uuidUser = UUID.fromString(id);
            if (userRepository.findById(uuidUser).isPresent()) {
                User user = userMapper.userRequestDtoToUser(data);
                user.setId(uuidUser);
                userRepository.save(user);
                return user;
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "BAD REQUEST");
            }
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "BAD REQUEST");
        }
    }

    public void delete(UUID id) {
        try {
            userRepository.deleteById(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Algo deu errado. Tente novamente.");
        }
    }
}
