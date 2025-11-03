package com.inovabook.web.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record UserDto(
        UUID id,
        String email,
        LocalDateTime createdOn,
        List<String> roles
) {}