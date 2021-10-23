package com.alert.app.backend.domain;

import com.alert.app.backend.status.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "USERS")
public class User {

    @Id
    @GeneratedValue
    private long id;
    private String username;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private UserStatus logStatus;
    @Enumerated(EnumType.STRING)
    private UserStatus subStatus;
    private LocalDateTime created;
}
