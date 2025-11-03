package com.inovabook.web.mapper;

import com.inovabook.web.dto.UserDto;
import com.inovabook.web.model.Role;
import com.inovabook.web.model.User;
import java.util.List;


public class UserMapper {
    public static UserDto mapToUserDto(User user) {
    String displayName = user.getEmail().split("@")[0]; // or firstName
    List<String> roleNames = user.getRoles().stream()
            .map(Role::getName)
            .toList();

    return new UserDto(
            user.getId(),
            user.getEmail(),
            user.getCreatedOn(),
            roleNames
    );
}
}