package org.project4.backend.controller.api.login;

import org.project4.backend.config.TokenUtil;
import org.project4.backend.dto.Role_DTO;
import org.project4.backend.dto.User_DTO;
import org.project4.backend.service.login_service.Login_service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.Duration;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/login")
public class Login_Controller {
    @Autowired
    private Login_service login_Service;
    // Đăng nhập tai khoản
    @PostMapping("/login")
    public ResponseEntity<?> login(String username, String password) {
        try {
            //Đăng nhập
            User_DTO user = login_Service.login(username, password);
            user.setPassword(null);
            //Tạo token
            long expirationMillis = 3600000;
            String mainToken = TokenUtil.generateToken(user.getId()+"", expirationMillis);
            Map<String, Object> response = new HashMap<>();
            // Tạo cookie
            ResponseCookie cookie = ResponseCookie.from("token", mainToken)
                    .httpOnly(false)
                    .maxAge(Duration.ofSeconds(3600))
                    .sameSite("Strict")
                    .secure(true)
                    .path("/")
                    .build();
            // Tạo header
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.SET_COOKIE, cookie.toString());
            response.put("token", mainToken);
            response.put("user", user);
            // Trả về token
            return ResponseEntity.ok()
                    .headers(headers)
                    .body(response);
        }catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    // Đăng ký tài khoản
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User_DTO user) {
        try {
            // Đăng ký
            Date currentDate = Date.valueOf(LocalDate.now());
            Role_DTO role = new Role_DTO();
            role.setId(2L);
            user.setTime_add(currentDate);
            user.setPoint(0L);
            user.setRole(role);
            User_DTO user_dto = login_Service.registerUser(user);
            user_dto.setPassword(null);
            long expirationMillis = 3600000;
            String mainToken = TokenUtil.generateToken(user_dto.getId()+"", expirationMillis);
            Map<String, Object> response = new HashMap<>();
            ResponseCookie cookie = ResponseCookie.from("token", mainToken)
                    .httpOnly(false) // Thiết lập httponly
                    .maxAge(Duration.ofSeconds(3600000)) // Thiết lập thời gian sống của cookie
                    .sameSite("Strict") // Thiết lập SameSite
                    .secure(true) // Đánh dấu cookie chỉ được gửi qua kênh an toàn (HTTPS)
                    .path("/")
                    .domain("localhost")
                    .build();
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.SET_COOKIE, cookie.toString());
            response.put("token", mainToken);
            response.put("user", user);
            return ResponseEntity.ok()
                    .headers(headers)
                    .body(response);
        }catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/checktoken")
    public ResponseEntity<?> checkToken(@RequestParam("token") String token) {
        try {
            User_DTO user = login_Service.checkUserByToken(token);
            user.setPassword(null);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
