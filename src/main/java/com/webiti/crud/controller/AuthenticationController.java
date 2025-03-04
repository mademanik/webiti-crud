package com.webiti.crud.controller;

import com.webiti.crud.dto.response.LoginResponse;
import com.webiti.crud.dto.request.LoginUserDto;
import com.webiti.crud.dto.request.RegisterUserDto;
import com.webiti.crud.helper.ApplicationMessages;
import com.webiti.crud.helper.ResponseHandler;
import com.webiti.crud.model.RefreshToken;
import com.webiti.crud.model.User;
import com.webiti.crud.service.AuthenticationService;
import com.webiti.crud.service.JwtService;
import com.webiti.crud.service.RefreshTokenService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping("/auth")
@RestController
@Slf4j
@CrossOrigin
public class AuthenticationController {

    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private RefreshTokenService refreshTokenService;

    @PostMapping("/signup")
    public ResponseEntity<Object> register(@RequestBody RegisterUserDto registerUserDto) {
        User response = authenticationService.signup(registerUserDto);
        return ResponseHandler.generateResponse(ApplicationMessages.REGISTER_SUCCESS.getMessage(), HttpStatus.OK, response);
    }

    @PostMapping("/login")
    public ResponseEntity<Object> authenticate(@RequestBody LoginUserDto loginUserDto) {
        User authenticatedUser = authenticationService.authenticate(loginUserDto);
        String jwtToken = jwtService.generateToken(authenticatedUser);
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(loginUserDto.getEmail());

        LoginResponse response = LoginResponse.builder()
                .accessToken(jwtToken)
                .expiresIn(jwtService.getExpirationTime())
                .refreshToken(refreshToken.getToken())
                .refreshTokenExpiresIn(refreshToken.getExpiryDate())
                .build();

        return ResponseHandler.generateResponse(ApplicationMessages.LOGIN_SUCCESS.getMessage(), HttpStatus.OK, response);
    }

    @PostMapping("/refreshToken")
    public ResponseEntity<Object> refreshToken(@RequestBody Map<String, String> request) {
        String refreshToken = request.get("refresh_token");

        RefreshToken refreshTokenObj = refreshTokenService.findByToken(refreshToken)
                .map(refreshTokenService::verifyExpiration)
                .orElseThrow(() -> new RuntimeException("Refresh token doesn't exists!"));

        String accessToken = jwtService.generateToken(refreshTokenObj.getUser());
        LoginResponse loginResponse = LoginResponse.builder()
                .accessToken(accessToken)
                .expiresIn(jwtService.getExpirationTime())
                .refreshToken(refreshTokenObj.getToken())
                .refreshTokenExpiresIn(refreshTokenObj.getExpiryDate())
                .build();

        return ResponseHandler.generateResponse(
                ApplicationMessages.REFRESH_TOKEN_SUCCESS.getMessage(),
                HttpStatus.OK,
                loginResponse
        );
    }

    @PostMapping("/logout")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Object> logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
        return ResponseHandler.generateResponse("User logged out successfully", HttpStatus.OK, null);
    }

}
