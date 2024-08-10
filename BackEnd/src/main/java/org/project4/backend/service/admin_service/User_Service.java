package org.project4.backend.service.admin_service;

import org.project4.backend.dto.User_DTO;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface User_Service {
    List<User_DTO> getAll(Pageable pageable);
    List<User_DTO>  getByName(String name, Pageable pageable);
    List<User_DTO> getByRole(Long role, Pageable pageable);
    User_DTO getById(Long id);
    User_DTO create(User_DTO user);
    User_DTO update(User_DTO user);
    User_DTO updateRole(Long id, Long roleid);
    User_DTO recharge(Long id, Long point);
    void delete(Long id);
    int totalItem();
}
