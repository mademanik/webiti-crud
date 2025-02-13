package com.webiti.crud.service;

import com.webiti.crud.dto.request.LoginUserDto;
import com.webiti.crud.dto.request.RegisterUserDto;
import com.webiti.crud.helper.ValidationErrors;
import com.webiti.crud.helper.Validations;
import com.webiti.crud.model.Role;
import com.webiti.crud.model.RoleEnum;
import com.webiti.crud.model.User;
import com.webiti.crud.repository.RoleRepository;
import com.webiti.crud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public User signup(RegisterUserDto input) {
        Optional<Role> optionalRole = roleRepository.findByName(RoleEnum.USER);

        if (optionalRole.isEmpty()) {
            return null;
        }

        Optional<User> optOfUser = userRepository.findByEmail(input.getEmail());
        Validations.checkArgument(
                optOfUser.isEmpty(), ValidationErrors.USER_ALREADY_EXISTS);

        User user = User.builder()
                .fullName(input.getFullName())
                .email(input.getEmail())
                .password(passwordEncoder.encode(input.getPassword()))
                .role(optionalRole.get())
                .build();

        return userRepository.save(user);
    }

    @Override
    public User authenticate(LoginUserDto input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );

        return userRepository.findByEmail(input.getEmail()).orElseThrow();
    }
}
