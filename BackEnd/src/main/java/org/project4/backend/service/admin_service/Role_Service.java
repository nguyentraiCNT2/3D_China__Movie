package org.project4.backend.service.admin_service;

import org.project4.backend.dto.Role_DTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface Role_Service {
    List<Role_DTO> getAll(Pageable pageable);

    List<Role_DTO> getByName(String name);

    Role_DTO getById(Long id);

    Role_DTO create(Role_DTO role);

    Role_DTO update(Role_DTO role);

    void delete(Long id);
}
