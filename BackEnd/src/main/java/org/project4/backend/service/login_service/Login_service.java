package org.project4.backend.service.login_service;

import org.project4.backend.dto.User_DTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public interface Login_service {
    User_DTO login(String username, String password);
    User_DTO registerUser(User_DTO user);
    User_DTO changePassword(Long id,String oldPassword, String newPassword, String confirmPassword);
    User_DTO checkUser(String username, String email);
    User_DTO forgetPassword(Long id, String  newPassword, String confirmPassword);
    User_DTO checkUserByToken(String token);
    User_DTO userProfile(Long id);
    User_DTO userByid(Long id);
    void uploadAvatar(Long id, MultipartFile file);
    void updateInfo(Long id,String fullname, String  email);
}
