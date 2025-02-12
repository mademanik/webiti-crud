package com.webiti.crud.service;

import com.webiti.crud.dto.request.RegisterUserDto;
import com.webiti.crud.dto.request.UserRequest;
import com.webiti.crud.dto.response.UserResponse;
import com.webiti.crud.helper.ValidationErrors;
import com.webiti.crud.helper.Validations;
import com.webiti.crud.mapper.UserMapper;
import com.webiti.crud.model.Role;
import com.webiti.crud.model.RoleEnum;
import com.webiti.crud.model.User;
import com.webiti.crud.repository.RoleRepository;
import com.webiti.crud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserMapper userMapper;

    @Override
    public Page<User> allUsers(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return userRepository.findAll(pageable);
    }

    @Override
    public User createAdministrator(RegisterUserDto input) {
        Optional<Role> optionalRole = roleRepository.findByName(RoleEnum.ADMIN);

        if (optionalRole.isEmpty()) {
            return null;
        }

        User user = User.builder()
                .fullName(input.getFullName())
                .email(input.getEmail())
                .password(passwordEncoder.encode(input.getPassword()))
                .role(optionalRole.get())
                .build();

        return userRepository.save(user);
    }

    @Override
    public UserResponse createUser(UserRequest input) {
        Optional<Role> optionalRole = roleRepository.findByName(RoleEnum.USER);
        if (optionalRole.isEmpty()) {
            return null;
        }

        User user = User.builder()
                .fullName(input.getFullName())
                .email(input.getEmail())
                .password(passwordEncoder.encode(input.getPassword()))
                .role(optionalRole.get())
                .build();
        userRepository.save(user);

        return userMapper.mapToUserResponse(user);
    }

    @Override
    public UserResponse updateUserById(Integer id, UserRequest input) {
        Optional<User> optOfUser = userRepository.findById(id);
        Validations.checkArgument(
                optOfUser.isPresent(), ValidationErrors.USER_NOT_FOUND);
        User user = optOfUser.get();
        user.setEmail(input.getEmail());
        user.setPassword(passwordEncoder.encode(input.getPassword()));
        user.setFullName(input.getFullName());
        userRepository.save(user);
        return userMapper.mapToUserResponse(user);
    }

    @Override
    public UserResponse getUserById(Integer id) {
        User user = userRepository.findById(id).orElse(null);
        return userMapper.mapToUserResponse(user);
    }

    @Override
    public void deleteUserById(Integer id) {
        Optional<User> optOfUser = userRepository.findById(id);
        Validations.checkArgument(
                optOfUser.isPresent(), ValidationErrors.USER_NOT_FOUND);
        User user = optOfUser.get();
        userRepository.deleteById(user.getId());
    }
}
