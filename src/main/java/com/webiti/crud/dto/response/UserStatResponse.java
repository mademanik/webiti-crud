package com.webiti.crud.dto.response;

import com.webiti.crud.model.User;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UserStatResponse {
    private Integer totalUser;
    private List<UserResponse> dataUsers;
}
