package com.viniciusdev.project_performance.features.user;

import com.viniciusdev.project_performance.app.exception.EmailAlreadyExistsException;
import com.viniciusdev.project_performance.app.exception.NotFoundException;
import com.viniciusdev.project_performance.features.auth.repositories.RoleRepository;
import com.viniciusdev.project_performance.features.auth.entities.Role;
import com.viniciusdev.project_performance.features.user.dtos.UserRequest;
import com.viniciusdev.project_performance.features.user.dtos.UserResponse;
import com.viniciusdev.project_performance.features.user.entities.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserMapper userMapper;

    public List<UserResponse> findAll() {

        List<User> users = userRepository.findAll();

        return users.stream()
                .map(userMapper::entityToResponse)
                .toList();
    }

    public UserResponse findyId(UUID id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User with id '%s' not found".formatted(id)));

        return userMapper.entityToResponse(user);

    }

    public void deleteById(UUID id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User with id '%s' not found".formatted(id)));

        userRepository.deleteById(id);

    }

    public UserResponse update(UUID id, UserRequest request) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User with id '%s' not found".formatted(id)));

        userMapper.updateEntityFromRequest(user, request);

        return userMapper.entityToResponse(userRepository.save(user));

    }

    @Transactional
    public UserResponse create(UserRequest request) {

       if (userRepository.existsByEmail(request.email())) {
           throw new EmailAlreadyExistsException(request.email());
       }

        Role basicRole = roleRepository.findByName("ROLE_BASIC")
                .orElseThrow(() -> new NotFoundException("Basic role not found"));

        User newUser = userMapper.requestToEntity(request);

        newUser.setPassword(passwordEncoder.encode(request.password()));

        newUser.setRoles(Set.of(basicRole));

        return userMapper.entityToResponse(userRepository.save(newUser));
    }
}
