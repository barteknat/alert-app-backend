package com.alert.app.backend.dto;

import com.alert.app.backend.status.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto {

    private long id;
    private String username;
    private String email;
    private String password;
    private Status status;
    private LocalDateTime created;
}
