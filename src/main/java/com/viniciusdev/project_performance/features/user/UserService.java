package com.viniciusdev.project_performance.features.user;

import com.viniciusdev.project_performance.features.auth.RoleRepository;
import com.viniciusdev.project_performance.features.auth.entities.Role;
import com.viniciusdev.project_performance.features.user.dtos.UserRequest;
import com.viniciusdev.project_performance.features.user.dtos.UserResponse;
import com.viniciusdev.project_performance.features.user.entities.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public User save(UserRequest request) {

        if (userRepository.findByEmail(request.email()).isPresent()) {
            throw new BadCredentialsException("Email already exists");
        }

        Role basicRole = roleRepository.findByName("BASIC").get();
        Role testRole = roleRepository.findByName("TEST").get();

        User newUser = new User();

        newUser.setName(request.name());
        newUser.setEmail(request.email());
        newUser.setPassword(passwordEncoder.encode(request.password()));
        newUser.setRoles(Set.of(basicRole, testRole));

        return userRepository.save(newUser);

    }

    public List<UserResponse> findAll() {

        List<User> users = userRepository.findAll();

        return users.stream()
                .map(user -> new UserResponse(user.getName(), user.getEmail()))
                .toList();
    }
}
