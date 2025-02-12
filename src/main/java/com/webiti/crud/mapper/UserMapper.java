package com.webiti.crud.mapper;

import com.webiti.crud.dto.request.UserRequest;
import com.webiti.crud.dto.response.UserResponse;
import com.webiti.crud.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface UserMapper {
    User mapToUser(UserRequest userRequest);
    UserResponse mapToUserResponse(User extracurricular);
    void mapUpdateUser(UserRequest userRequest, @MappingTarget User user);
}
