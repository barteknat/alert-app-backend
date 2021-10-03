//package com.alert.app.backend.domain;
//
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import javax.persistence.*;
//import java.time.LocalDateTime;
//
//@Builder
//@AllArgsConstructor
//@NoArgsConstructor
//@Data
//@Entity
//public class Login {
//
//    @Id
//    @GeneratedValue
//    private long id;
//
//    @ManyToOne
//    @JoinColumn(name = "USER_ID")
//    private User user;
//
//    private boolean status;
//
//    private LocalDateTime time = LocalDateTime.now();
//}
