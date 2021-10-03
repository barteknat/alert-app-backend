//package com.alert.app.backend.mapper;
//
//import com.alert.app.backend.domain.Login;
//import com.alert.app.backend.dto.LoginDto;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//public class LoginMapper {
//
//    public Login mapToLogin(LoginDto loginDto) {
//        return Login.builder()
//                .id(loginDto.getId())
//                .status(loginDto.isStatus())
//                .build();
//    }
//
//    public LoginDto mapToLoginDto(Login login) {
//        return LoginDto.builder()
//                .id(login.getId())
//                .status(login.isStatus())
//                .build();
//    }
//
//    public List<LoginDto> mapToLoginDtoList(List<Login> loginList) {
//        List<LoginDto> loginDtoList = new ArrayList<>();
//        for (Login login : loginList) {
//            loginDtoList.add(mapToLoginDto(login));
//        }
//        return loginDtoList;
//    }
//}
