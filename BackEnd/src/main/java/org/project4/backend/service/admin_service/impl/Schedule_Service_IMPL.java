package org.project4.backend.service.admin_service.impl;

import org.modelmapper.ModelMapper;
import org.project4.backend.dto.Schedule_DTO;
import org.project4.backend.entity.Schedule_Entity;
import org.project4.backend.repository.admin_repository.Schedule_Repository;
import org.project4.backend.service.admin_service.Schedule_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Schedule_Service_IMPL implements Schedule_Service {
    @Autowired
    private Schedule_Repository scheduleRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<Schedule_DTO> getAll() {
        List<Schedule_DTO> result = new ArrayList<>();
        List<Schedule_Entity> schedule_entity = scheduleRepository.findAll();
        if (schedule_entity == null)
            throw new RuntimeException("Không có bộ liên quan");
        for (Schedule_Entity schedule : schedule_entity) {
            Schedule_DTO dto = modelMapper.map(schedule, Schedule_DTO.class);
            dto.setUseradd(null);
            dto.setUserupdate(null);
            result.add(dto);
        }
        return result;
    }
}
