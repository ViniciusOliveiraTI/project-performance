package com.viniciusdev.project_performance.app.config;

import com.viniciusdev.project_performance.features.auth.entities.Role;
import com.viniciusdev.project_performance.features.auth.RoleRepository;
import com.viniciusdev.project_performance.features.user.UserRepository;
import com.viniciusdev.project_performance.features.user.entities.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.Set;

@Configuration
public class AdminUserConfig implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void run(String... args) throws Exception {

        var adminRole = new Role(null, "ADMIN");
        var basicRole = new Role(null, "BASIC");
        var testRole = new Role(null, "TEST");

        roleRepository.saveAll(Arrays.asList(adminRole, basicRole, testRole));

        var userAdmin = userRepository.findByEmail("admin");

        userAdmin.ifPresentOrElse(
                (user) -> {
                    System.out.println("Admin já existe");
                },
                () -> {
                    var user = new User();
                    user.setName("Admin");
                    user.setEmail("admin");
                    user.setPassword(passwordEncoder.encode("123"));
                    user.setRoles(Set.of(adminRole));
                    userRepository.save(user);
                }
        );

    }

}
