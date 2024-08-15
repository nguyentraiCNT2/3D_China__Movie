package org.project4.backend.controller.api.admin;

import org.project4.backend.dto.Schedule_DTO;
import org.project4.backend.service.admin_service.Schedule_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/admin/schedule")
public class Schedule_Controller {
    @Autowired
    private Schedule_Service scheduleService;
    @RequestMapping("/getAll")
    public List<Schedule_DTO> getAll(){
        List<Schedule_DTO> result = scheduleService.getAll();
        if(result == null){
            return null;
        }
        return result;
    }
}
