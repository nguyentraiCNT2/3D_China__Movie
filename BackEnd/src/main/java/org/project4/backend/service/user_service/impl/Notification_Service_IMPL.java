package org.project4.backend.service.user_service.impl;

import org.modelmapper.ModelMapper;
import org.project4.backend.dto.Notification_DTO;
import org.project4.backend.dto.User_DTO;
import org.project4.backend.entity.Notification_Entity;
import org.project4.backend.entity.User_Entity;
import org.project4.backend.repository.admin_repository.Notification_Repository;
import org.project4.backend.repository.admin_repository.User_Repository;
import org.project4.backend.service.user_service.Notification_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class Notification_Service_IMPL implements Notification_Service {
    @Autowired
    private Notification_Repository notificationRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private User_Repository userRepository;
    @Override
    public List<Notification_DTO> getByUser(Long id, Pageable pageable) {
        List<Notification_DTO> user_DTOS = new ArrayList<>();
        // tìm kiếm tất cả người dùng
        User_Entity user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng"));
        List<Notification_Entity> list = notificationRepository.findByUser(user,pageable);
        // chuyển đổi sang DTO
        for (Notification_Entity item : list) {
            Notification_DTO user_DTO = modelMapper.map(item, Notification_DTO.class);
            user_DTOS.add(user_DTO);
        }
        return user_DTOS;
    }

    @Override
    public List<Notification_DTO> getByUser(Long id) {
        List<Notification_DTO> user_DTOS = new ArrayList<>();
        // tìm kiếm tất cả người dùng
        User_Entity user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng"));
        List<Notification_Entity> list = notificationRepository.findByUser(user);
        if (list.size() == 0)
            throw new RuntimeException("Không có thông báo nào");
        // chuyển đổi sang DTO
        for (Notification_Entity item : list) {
            Notification_DTO user_DTO = modelMapper.map(item, Notification_DTO.class);
            user_DTOS.add(user_DTO);
        }
        return user_DTOS;
    }

    @Override
    public int totalItems() {
        return (int) notificationRepository.count();
    }
}
