package com.alert.app.backend.domain;

import com.alert.app.backend.status.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.time.LocalDateTime;

import static com.alert.app.backend.status.Status.NOT_SUBSCRIBING;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class User {

    @Id
    private long id;

    private String username;

    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private Status status;

    private LocalDateTime created;
}
