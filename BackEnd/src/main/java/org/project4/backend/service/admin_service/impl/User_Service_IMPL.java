package org.project4.backend.service.admin_service.impl;

import org.mindrot.jbcrypt.BCrypt;
import org.modelmapper.ModelMapper;
import org.project4.backend.dto.User_DTO;
import org.project4.backend.entity.Notification_Entity;
import org.project4.backend.entity.Role_Entity;
import org.project4.backend.entity.User_Entity;
import org.project4.backend.repository.admin_repository.Notification_Repository;
import org.project4.backend.repository.admin_repository.Role_Repository;
import org.project4.backend.repository.admin_repository.User_Repository;
import org.project4.backend.service.admin_service.User_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class User_Service_IMPL implements User_Service {
    @Autowired
    private User_Repository user_Repository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private Role_Repository role_Repository;
    @Autowired
    private Notification_Repository notificationRepository;
    // hiển thị tất cả người dùng
    @Override
    public List<User_DTO> getAll(Pageable pageable) {
        List<User_DTO> user_DTOS = new ArrayList<>();
        // tìm kiếm tất cả người dùng
        List<User_Entity> list = user_Repository.findAll(pageable).getContent();
        // chuyển đổi sang DTO
        for (User_Entity user_Entity : list) {
            User_DTO user_DTO = modelMapper.map(user_Entity, User_DTO.class);
            user_DTOS.add(user_DTO);
        }
        return user_DTOS;
    }

    @Override
    public List<User_DTO> getByName(String name, Pageable pageable) {
        List<User_DTO> user_DTOS = new ArrayList<>();
        // tìm kiếm tất cả người dùng
        List<User_Entity> list = user_Repository.findByUsernameLike("%" + name + "%",pageable);
        // chuyển đổi sang DTO
        for (User_Entity user_Entity : list) {
            User_DTO user_DTO = modelMapper.map(user_Entity, User_DTO.class);
            user_DTOS.add(user_DTO);
        }
        return user_DTOS;
    }

    @Override
    public List<User_DTO> getByRole(Long role, Pageable pageable) {
        List<User_DTO> user_DTOS = new ArrayList<>();
        // tìm kiếm theo quyền hạn id
        Role_Entity role_Entity = role_Repository.findById(role).orElseThrow(() -> new RuntimeException("Không có quyền này!"));
        // tìm kiếm tất cả người dùng
        List<User_Entity> list = user_Repository.findByRole(role_Entity,pageable);
        // chuyển đổi sang DTO
        for (User_Entity user_Entity : list) {
            User_DTO user_DTO = modelMapper.map(user_Entity, User_DTO.class);
            user_DTOS.add(user_DTO);
        }
        return user_DTOS;
    }

    @Override
    public User_DTO getById(Long id) {
        try {
            // tìm kiếm người dùng theo id
            User_Entity user_Entity = user_Repository.findById(id).orElseThrow(() -> new RuntimeException("Không có người dùng này!"));
           // chuyển đổi sang DTO
            User_DTO user_DTO = modelMapper.map(user_Entity, User_DTO.class);
            return user_DTO;
        }catch (Exception e) {
           throw new RuntimeException("Không tìm thấy người dùng");
        }
    }

    @Override
    public User_DTO create(User_DTO user) {
        try {
            if (user == null)
                throw new RuntimeException("Bạn chưa nhập dữ liệu!");
            User_Entity user_Entity = modelMapper.map(user, User_Entity.class);
            Role_Entity role_Entity = role_Repository.findById(user.getRole().getId()).orElseThrow(() -> new RuntimeException("Không có quyền này!"));
            user_Entity.setRole(role_Entity);
            user_Repository.save(user_Entity);
            User_DTO user_DTO = modelMapper.map(user_Entity, User_DTO.class);
            return user_DTO;
        }catch (Exception e) {
          throw new RuntimeException("Có lỗi xảy ra");
        }
    }

    @Override
    public User_DTO update(User_DTO user) {
        try {
            if (user ==null)
                throw new RuntimeException("Bạn chưa nhập dữ liệu!");
            User_Entity user_entity = user_Repository.findById(user.getId()).orElseThrow(() -> new RuntimeException("Không có người dùng này!"));
            Role_Entity role_Entity = role_Repository.findById(user.getRole().getId()).orElseThrow(() -> new RuntimeException("Không có quyền này!"));
            user_entity.setRole(role_Entity);
            user_entity.setUsername(user.getUsername());
            user_entity.setPassword(user.getPassword());

            user_Repository.save(user_entity);
            User_DTO user_DTO = modelMapper.map(user_entity, User_DTO.class);
            return user_DTO;
        }catch (Exception e) {
            throw new RuntimeException("Có lỗi xảy ra");
        }

    }

    @Override
    public User_DTO updateRole(Long id, Long roleid) {
        try {
            User_Entity user_entity = user_Repository.findById(id).orElseThrow(() -> new RuntimeException("Không có người dùng này!"));
            Role_Entity role_Entity = role_Repository.findById(roleid).orElseThrow(() -> new RuntimeException("Không có quyền này!"));
            user_entity.setRole(role_Entity);
            user_Repository.save(user_entity);
            User_DTO user_DTO = modelMapper.map(user_entity, User_DTO.class);
            return user_DTO;
        }catch (Exception e) {
            throw new RuntimeException("Có lỗi xảy ra");
        }
    }

    @Override
    public User_DTO recharge(Long id, Long point) {
        try {
            if (point ==null)
                throw new RuntimeException("Bạn chưa nhập dữ liệu!");
            User_Entity user_entity = user_Repository.findById(id).orElseThrow(() -> new RuntimeException("Không có người dùng này!"));
            user_entity.setPoint(user_entity.getPoint() + point);
            user_Repository.save(user_entity);
            Notification_Entity notificationEntity = new Notification_Entity();
            notificationEntity.setUser(user_entity);
            notificationEntity.setTimeadd(Date.valueOf(LocalDate.now()));
            notificationEntity.setTimeupdate(Date.valueOf(LocalDate.now()));
            notificationEntity.setContent("Đã nạp "+ point +" xu \n số dư hiện tại là: " + user_entity.getPoint()+" xu.");
            notificationEntity.setStatus(true);
            notificationRepository.save(notificationEntity);
            User_DTO user_DTO = modelMapper.map(user_entity, User_DTO.class);
            return user_DTO;
        }catch (Exception e) {
            throw new RuntimeException("Có lỗi xảy ra");
        }
    }

    @Override
    public void delete(Long id) {
        try {
            if (id == null)
                throw new RuntimeException("Bạn chưa nhập dữ liệu!");
            User_Entity user_entity = user_Repository.findById(id).orElseThrow(() -> new RuntimeException("Không có người dùng này!"));
            user_Repository.deleteById(user_entity.getId());
        }catch (Exception e) {
            throw new RuntimeException("Có lỗi xảy ra");
        }
    }

    @Override
    public int totalItem() {
        return (int) user_Repository.count();
    }
}
