package org.project4.backend.controller.api.login;

import org.project4.backend.config.TokenUtil;
import org.project4.backend.controller.output.Notification_OutPut;
import org.project4.backend.controller.output.User_OutPut;
import org.project4.backend.dto.Movie_DTO;
import org.project4.backend.dto.Role_DTO;
import org.project4.backend.dto.User_DTO;
import org.project4.backend.service.login_service.Login_service;
import org.project4.backend.service.user_service.Notification_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    @Autowired
    private Notification_Service notificationService;
    // Đăng nhập tai khoản
    @PostMapping("/login")
    public ResponseEntity<?> login(String username, String password) {
        try {
            //Đăng nhập
            User_DTO user = login_Service.login(username, password);
            user.setPassword(null);
            //Tạo token
            long expirationMillis = 3600000;
            String mainToken = TokenUtil.generateToken(user.getId() + "", expirationMillis);
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
        } catch (Exception e) {
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
            String mainToken = TokenUtil.generateToken(user_dto.getId() + "", expirationMillis);
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
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/checktoken")
    public ResponseEntity<?> checkToken(@RequestParam("token") String token) {
        try {
            User_DTO user = login_Service.checkUserByToken(token);
            user.setPassword(null);
            user.setAvatar(null);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/avatar/{id}")
    public ResponseEntity<?> avatar(@PathVariable Long id, @RequestParam("avatar") MultipartFile avatar) {
        try {
            login_Service.uploadAvatar(id, avatar);
            return ResponseEntity.ok("Thay đổi ảnh thành công");
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/changepassword/{id}")
    public ResponseEntity<?> avatar(@PathVariable Long id, @RequestParam("password") String password, @RequestParam("newpassword") String newpassword, @RequestParam("confirmpassword") String confirmpassword) {
        try {
            User_DTO user_dto = login_Service.changePassword(id, password, newpassword, confirmpassword);
            return ResponseEntity.ok("Thay đổi mk thành công");
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/updateinfo/{id}")
    public ResponseEntity<?> updateinfo(@PathVariable Long id, @RequestParam("fullname") String fullname, @RequestParam("email") String email) {
        try {
            login_Service.updateInfo(id, fullname, email);
            return ResponseEntity.ok("Thay đổi thông tin thành công");
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/userprofile")
    public ResponseEntity<?> userProfile(@RequestParam("id") Long id) {
        try {
            User_DTO user = login_Service.userProfile(id);
            user.setPassword(null);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/view/{id}")
    public ResponseEntity<byte[]> viewFile(@PathVariable Long id) {
        User_DTO fileDTO = login_Service.userByid(id);
        if (fileDTO != null && fileDTO.getAvatar() != null) {
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + fileDTO.getId() + "\"")
                    .contentType(MediaType.IMAGE_PNG)
                    .contentType(MediaType.IMAGE_JPEG)
                    .contentType(MediaType.IMAGE_GIF)
                    .body(fileDTO.getAvatar());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/notificaion/user/{id}")
    public Notification_OutPut getAll(@PathVariable Long id, @RequestParam("page") int page, @RequestParam("limit") int limit) {
        Notification_OutPut result = new Notification_OutPut();
        result.setPage(page);
        Pageable pageable = PageRequest.of(page - 1, limit);
        result.setListResult(notificationService.getByUser(id,pageable));
        result.setTotalPage((int) Math.ceil((double) (notificationService.totalItems()) / limit));
        return result;
    }
    @PostMapping("/changepassword/user")
    public ResponseEntity<?> changepassword(@RequestParam("id") Long id, @RequestParam("newPassword") String newPassword
            , @RequestParam("confirmPassword") String confirmPassword) {
      try {
          User_DTO user = login_Service.forgetPassword(id, newPassword, confirmPassword);
          return ResponseEntity.ok(user);
      }catch (Exception e) {
          return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
      }
    }
    @GetMapping("/find/user")
    public ResponseEntity<?> findUser( @RequestParam("username") String username, @RequestParam("email") String email) {
        try {
            User_DTO user = login_Service.checkUser(username, email);
            return ResponseEntity.ok(user);
        }catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
