package org.project4.backend.service.login_service;

import org.project4.backend.dto.User_DTO;

public interface Login_service {
    User_DTO login(String username, String password);
    User_DTO registerUser(User_DTO user);
    User_DTO checkUser(String username, String email);
    User_DTO forgetPassword(User_DTO user);
    User_DTO checkUserByToken(String token);
}
