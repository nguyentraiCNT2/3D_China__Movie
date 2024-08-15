package org.project4.backend.service.login_service.impl;

import org.mindrot.jbcrypt.BCrypt;
import org.modelmapper.ModelMapper;
import org.project4.backend.config.TokenUtil;
import org.project4.backend.dto.User_DTO;
import org.project4.backend.entity.Role_Entity;
import org.project4.backend.entity.User_Entity;
import org.project4.backend.repository.admin_repository.Role_Repository;
import org.project4.backend.repository.admin_repository.User_Repository;
import org.project4.backend.service.login_service.Login_service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.sql.Date;
import java.time.LocalDate;

@Service
public class Login_Service_IMPL implements Login_service {
    @Autowired
    private User_Repository user_Repository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private Role_Repository role_Repository;
    // chức năng đăng nhập
    @Override
    public User_DTO login(String username, String password) {
        try {
            //tìm kiếm tài khoản theo username
            User_Entity user = user_Repository.findByUsername(username);
            // kiểm tra nếu không có tai khoản nào
            if (user == null)
                throw new RuntimeException("Tài khoản không tồn tại!");
            //kiểm tra nếu có tài khoản nhưng mật khẩu không chính xác
            Boolean isMatch = BCrypt.checkpw(password, user.getPassword());
            if (!isMatch)
                throw new RuntimeException("Tài khoản hoặc mật không chính xác!");
            // kiểm tra tài khoản có phải là admin hay không
            if (user.getRole().getName().equals("ROLE_ADMIN"))
                    return modelMapper.map(user, User_DTO.class);
            // kiểm tra tài khoản có phải là user hay không
            if (user.getRole().getName().equals("ROLE_USER"))
                    return modelMapper.map(user, User_DTO.class);
            // nếu không phải admin hoặc user thì lỗi
            throw new RuntimeException("Tài khoản không tồn tại!");
        } catch (Exception e) {
            throw new RuntimeException("Có lỗi không xác định khi đăng nhập");

        }
    }
    // Đăng ký tài khoản
    @Override
    public User_DTO registerUser(User_DTO user) {
        try{
            User_Entity user_check = user_Repository.findByUsername(user.getUsername());
            // kiểm tra nếu username đã tồn tại
            if (user_check != null)
                throw new RuntimeException("Tài khoản đã tồn tại!");
            // kiểm tra nếu có dữ liệu
            if (user == null)
                throw new RuntimeException("Không có dữ liệu!");
            // kiểm tra nếu username không được nhập
            if (user.getUsername() == null || user.getUsername().equals(""))
                throw new RuntimeException("Chưa nhập tên đăng nhập!");
            // kiểm tra nếu password không được nhập
            if (user.getPassword() == null || user.getPassword().equals(""))
                throw new RuntimeException("Chưa nhập mật khẩu!");
            // kiểm tra nếu email không được nhập
            if (user.getEmail() == null || user.getEmail().equals(""))
                throw new RuntimeException("Chưa nhập email!");
            // kiểm tra nếu mật khẩu ít hơn 6 kí tự
            if (user.getPassword().length() < 6)
                throw new RuntimeException("Mật khẩu phải có độ dài ít nhất 6 kí tự!");
            // kiểm tra nếu mật khẩu có độ dài lớn hơn 25 kí tự
            if (user.getPassword().length() > 25)
                throw new RuntimeException("Mật khẩu phải có độ dài tối đa 25 kí tự!");
            // kiểm tra nếu mật khẩu có khoảng trắng
            if (user.getPassword().contains(" "))
                throw new RuntimeException("Mật khẩu không được có khoảng trắng!");
            // mã hóa mật khẩu
            String hashPassword =  BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
            user.setPassword(hashPassword);
            user.setFullname(user.getUsername());
            // kiểm tra quyền theo id
            Role_Entity role = role_Repository.findById(user.getRole().getId()).orElseThrow(() -> new RuntimeException("Không có quyền này!"));
            User_Entity user_entity = modelMapper.map(user, User_Entity.class);
            user_entity.setRole(role);
            user_entity.setTime_add(Date.valueOf(LocalDate.now()));
            // lưu tài khoản
            User_Entity user_EP = user_Repository.save(user_entity);
            User_DTO user_DTO = modelMapper.map(user_EP, User_DTO.class);
            return user_DTO;

        }catch (Exception e) {
            throw new RuntimeException("Có lỗi không xác định khi đăng ký");
        }
    }

    @Override
    public User_DTO changePassword(Long id,String oldPassword, String newPassword, String confirmPassword) {
        try {
            if (oldPassword == null || oldPassword.equals(""))
                throw new RuntimeException("Bạn chưa nhập mật khẩu hiện tại!");
            if (newPassword == null || newPassword.equals(""))
                throw new RuntimeException("Bạn chưa nhập mật khẩu mới!");
            if (confirmPassword == null || confirmPassword.equals(""))
                throw new RuntimeException("Bạn chưa nhập lại mật khẩu!");
            if (newPassword.length() < 6)
                throw new RuntimeException("Mật khẩu có độ dài ít nhất 6 kí tự!");
            if (newPassword.length() > 25)
                throw new RuntimeException("Mật khẩu có độ dài tối đa 25 kí tự!");
            if (newPassword.contains(" "))
                throw new RuntimeException("Mật khẩu không thế có khoảng trắng!");
            if (!newPassword.equals(confirmPassword))
                throw new RuntimeException("Mật khẩu không trùng khớp!");
            User_Entity user = user_Repository.findById(id).orElseThrow(() -> new RuntimeException("Không có người dùng không!"));
            if (!BCrypt.checkpw(oldPassword, user.getPassword()))
                throw new RuntimeException("Mật khẩu hiện tại không chính xác!");
            String hashPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt());
            user.setPassword(hashPassword);
            User_Entity user_EP = user_Repository.save(user);
            User_DTO user_DTO = modelMapper.map(user_EP, User_DTO.class);
            return user_DTO;
        }catch (Exception e) {
            throw new RuntimeException("Có lỗi không xác định khi đổi mật khẩu");
        }
    }

    // tìm tài khoản với username và email
    @Override
    public User_DTO checkUser(String username, String email) {
        try {
            //tìm kiếm tài khoản theo username và email
            User_Entity user = user_Repository.findByUsernameAndEmail(username,email);
            // kiểm tra nếu không có tài khoản nào
            if (user == null)
                throw new RuntimeException("Tài khoản không tồn tại!");
            User_DTO user_DTO = modelMapper.map(user, User_DTO.class);
            return user_DTO;
        }catch (Exception e) {
            throw new RuntimeException("Có lỗi không xác định khi tìm kiếm tài khoản");
        }
    }
    // đặt lại mật khẩu
    @Override
    public User_DTO forgetPassword(Long id, String  newPassword, String confirmPassword) {
        try {
        User_Entity user = user_Repository.findById(id).orElseThrow(() -> new RuntimeException("Tài khoản này không tồn tại!"));
        // kiểm tra nếu có dữ liệu
        if (user == null)
            throw new RuntimeException("Không có dữ liệu!");
        // kiểm tra nếu password không được nhập
        if (newPassword == null || newPassword.equals(""))
            throw new RuntimeException("Chưa nhập mật khẩu!");
        // kiểm tra nếu mật khẩu ít hơn 6 kí tự
        if (newPassword.length() < 6)
            throw new RuntimeException("Mật khẩu phải có độ dài ít nhất 6 kí tự!");
        // kiểm tra nếu mật khẩu có độ dài lớn hơn 25 kí tự
        if (newPassword.length() > 25)
            throw new RuntimeException("Mật khẩu phải có độ dài tối đa 25 kí tự!");
        // kiểm tra nếu mật khẩu có khoảng trắng
        if (newPassword.contains(" "))
            throw new RuntimeException("Mật khẩu không được có khoảng trắng!");
        if (!newPassword.equals(confirmPassword)){
            throw new RuntimeException("Mật không trùng khởp!");
        }
        // mã hóa mật khẩu
        String hashPassword =  BCrypt.hashpw(newPassword, BCrypt.gensalt());
        user.setPassword(hashPassword);
        User_Entity user_entity = modelMapper.map(user, User_Entity.class);
        User_Entity user_EP = user_Repository.save(user_entity);
        User_DTO user_DTO = modelMapper.map(user_EP, User_DTO.class);
        return user_DTO;

    }catch (Exception e) {
        throw new RuntimeException("Có lỗi không xác định khi đăng ký");
    }

    }

    @Override
    public User_DTO checkUserByToken(String token) {
        if (token != null) {
            // kiểm tra xác định token
            boolean isTokenValid = TokenUtil.validateToken(token);
            if (!isTokenValid)
                throw  new RuntimeException("Bạn đã hết phiên đăng nhập!");
            Long id = Long.valueOf(TokenUtil.getUserIdFromToken(token)) ;
            User_Entity user = user_Repository.findById(id).orElseThrow(() -> new RuntimeException("Không có người dùng này!"));
            if (user != null) {
                User_DTO user_DTO = modelMapper.map(user, User_DTO.class);
                return user_DTO;
            }
        }
        throw  new RuntimeException("Bạn đã hết phiên đăng nhập!");
    }

    @Override
    public User_DTO userProfile(Long id) {
       try {
           User_Entity  user = user_Repository.findById(id).orElseThrow(() -> new RuntimeException("Không có người dùng này!"));
           User_DTO user_DTO = new User_DTO();
           user_DTO.setId(user.getId());
           user_DTO.setUsername(user.getUsername());
           user_DTO.setEmail(user.getEmail());
           user_DTO.setPoint(user.getPoint());
           user_DTO.setFullname(user.getFullname());
           user_DTO.setAvatar(null);
           user_DTO.setTime_add(user.getTime_add());
           user_DTO.setActive(user.getActive());
           user_DTO.setStatus(user.getStatus());
           return user_DTO;
       }catch (Exception e) {
           throw new RuntimeException("Có lỗi không xác định");
       }
    }

    @Override
    public User_DTO userByid(Long id) {
        try {
            User_Entity  user = user_Repository.findById(id).orElseThrow(() -> new RuntimeException("Không có người dùng này!"));
            User_DTO user_DTO = new User_DTO();
            user_DTO.setId(user.getId());
            user_DTO.setUsername(user.getUsername());
            user_DTO.setFullname(user.getFullname());
            user_DTO.setEmail(user.getEmail());
            user_DTO.setPoint(user.getPoint());
            user_DTO.setAvatar(user.getAvatar());
            user_DTO.setTime_add(user.getTime_add());
            user_DTO.setActive(user.getActive());
            user_DTO.setStatus(user.getStatus());
            return user_DTO;
        }catch (Exception e) {
            throw new RuntimeException("Có lỗi không xác định");
        }
    }

    @Override
    public void uploadAvatar(Long id, MultipartFile file) {
        try {
            User_Entity user = user_Repository.findById(id).orElseThrow(() -> new RuntimeException("Không có người dùng này!"));
            if (user == null)
                throw new RuntimeException("Không có người dùng này!");
            if (file == null)
                user.setAvatar(user.getAvatar());
            else
                user.setAvatar(file.getBytes());
            user_Repository.save(user);
        }catch (Exception e) {
            throw new RuntimeException("Có lỗi không xác định");
        }

    }

    @Override
    public void updateInfo(Long id,String fullname, String email) {
        try {
            if (fullname == null || fullname.equals(""))
                throw new RuntimeException("Bạn chưa nhập họ tên!");
            if (email == null || email.equals(""))
                throw new RuntimeException("Bạn chưa nhập email!");
            User_Entity user = user_Repository.findById(id).orElseThrow(() -> new RuntimeException("Không có người dùng này!"));
            if (user == null)
                throw new RuntimeException("Không có người dùng này!");
            user.setFullname(fullname);
            user.setEmail(email);
            user_Repository.save(user);
        }catch (Exception e) {
            throw new RuntimeException("Có lỗi không xác định");
        }
    }
}
