package org.project4.backend.service.admin_service;

import org.project4.backend.dto.Schedule_DTO;

import java.util.List;

public interface Schedule_Service {
    List<Schedule_DTO> getAll();
}
