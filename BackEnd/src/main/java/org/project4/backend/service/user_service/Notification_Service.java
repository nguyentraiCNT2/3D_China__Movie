package org.project4.backend.service.user_service;

import org.project4.backend.dto.Notification_DTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface Notification_Service {
    List<Notification_DTO> getByUser(Long id, Pageable pageable);
    List<Notification_DTO> getByUser(Long id);
    int totalItems();
}
