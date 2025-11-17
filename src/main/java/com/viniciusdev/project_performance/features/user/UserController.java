package com.viniciusdev.project_performance.features.user;

import com.viniciusdev.project_performance.features.user.dtos.UserRequest;
import com.viniciusdev.project_performance.features.user.dtos.UserResponse;
import com.viniciusdev.project_performance.features.user.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<UserResponse> save(@RequestBody UserRequest request) {

        try {

            User user = userService.save(request);

            return ResponseEntity.status(HttpStatus.CREATED).body(new UserResponse(user.getName(), user.getEmail()));

        }catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @GetMapping
    @PreAuthorize("hasAuthority('SCOPE_ADMIN') or hasAuthority('SCOPE_TEST')")
    public ResponseEntity<List<UserResponse>> findAll() {
        return ResponseEntity.ok().body(userService.findAll());
    }

}
