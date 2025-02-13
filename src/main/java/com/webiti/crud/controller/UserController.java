package com.webiti.crud.controller;

import com.webiti.crud.dto.request.UserRequest;
import com.webiti.crud.dto.response.UserResponse;
import com.webiti.crud.helper.ApplicationMessages;
import com.webiti.crud.helper.ResponseHandler;
import com.webiti.crud.mapper.UserMapper;
import com.webiti.crud.model.RoleEnum;
import com.webiti.crud.model.User;
import com.webiti.crud.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("/users")
@RestController
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/me")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Object> authenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User getUser = (User) authentication.getPrincipal();
        UserResponse response = userMapper.mapToUserResponse(getUser);

        if (response != null) {
            return ResponseHandler.generateResponse(ApplicationMessages.USER_RETRIEVED.getMessage(), HttpStatus.OK, response);
        } else {
            return ResponseHandler.generateResponse(ApplicationMessages.USER_DATA_EMPTY.getMessage(), HttpStatus.NOT_FOUND, response);
        }
    }

    @PostMapping("")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Object> createUser(@RequestBody UserRequest userRequest) {
        UserResponse response = userService.createUser(userRequest);
        return ResponseHandler.generateResponse(ApplicationMessages.USER_CREATED.getMessage(), HttpStatus.CREATED, response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateUser(@PathVariable("id") Integer id, @RequestBody UserRequest userRequest) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User userPrincipal = (User) authentication.getPrincipal();
        RoleEnum userRole = userPrincipal.getRole().getName();

        if (!id.equals(userPrincipal.getId()) && !userRole.equals(RoleEnum.ADMIN)) {
            return ResponseHandler.generateResponse(ApplicationMessages.UNAUTHORIZED_UPDATED_USER.getMessage(),
                    HttpStatus.FORBIDDEN, null);
        }

        UserResponse response = userService.updateUserById(id, userRequest);
        return ResponseHandler.generateResponse(ApplicationMessages.USER_UPDATED.getMessage(), HttpStatus.OK, response);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Object> allUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Page<User> userPage = userService.allUsers(page, size);

        Map<String, Object> response = new HashMap<>();
        response.put("users", userPage.getContent());
        response.put("currentPage", userPage.getNumber());
        response.put("totalItems", userPage.getTotalElements());
        response.put("totalPages", userPage.getTotalPages());

        return ResponseHandler.generateResponse(
                !userPage.isEmpty() ? ApplicationMessages.USER_RETRIEVED.getMessage() : ApplicationMessages.USER_DATA_EMPTY.getMessage(),
                HttpStatus.OK,
                response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getUserById(@PathVariable("id") Integer id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User userPrincipal = (User) authentication.getPrincipal();
        RoleEnum userRole = userPrincipal.getRole().getName();

        if (!id.equals(userPrincipal.getId()) && !userRole.equals(RoleEnum.ADMIN)) {
            return ResponseHandler.generateResponse(ApplicationMessages.UNAUTHORIZED_GET_USER.getMessage(),
                    HttpStatus.FORBIDDEN, null);
        }

        UserResponse response = userService.getUserById(id);
        if (response != null) {
            return ResponseHandler.generateResponse(ApplicationMessages.USER_RETRIEVED.getMessage(), HttpStatus.OK, response);
        } else {
            return ResponseHandler.generateResponse(ApplicationMessages.USER_NOT_FOUND
                    .getValue(id.toString()), HttpStatus.NOT_FOUND, response);
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Object> deleteUserById(@PathVariable("id") Integer id) {
        userService.deleteUserById(id);
        return ResponseHandler.generateResponse(ApplicationMessages.USER_DELETED.getMessage(), HttpStatus.OK, null);
    }

}

