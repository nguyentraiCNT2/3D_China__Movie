package org.project4.backend.service.user_service;

import org.project4.backend.dto.User_Follow_DTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface User_Follow_Service {
    List<User_Follow_DTO> findByUser(Long userId, Pageable pageable);
    void save(User_Follow_DTO userFollowDTO);
    void delete(Long id);
    int totalItem();
}
