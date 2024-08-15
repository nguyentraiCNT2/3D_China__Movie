package org.project4.backend.config;

import jakarta.annotation.PostConstruct;
import org.project4.backend.entity.Role_Entity;
import org.project4.backend.entity.Schedule_Entity;
import org.project4.backend.entity.User_Entity;
import org.project4.backend.repository.admin_repository.Role_Repository;
import org.project4.backend.repository.admin_repository.Schedule_Repository;
import org.project4.backend.repository.admin_repository.User_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.sql.Date;
import java.time.LocalDate;

@Configuration
public class DataInitializer {
    @Autowired
    private Role_Repository role_Repository;
    @Autowired
    private Schedule_Repository schedule_Repository;
    @Autowired
    private User_Repository user_Repository;
    @PostConstruct
    public void init() {
        if (role_Repository.count() == 0) {
            Role_Entity adminRole = new Role_Entity();
            adminRole.setName("ROLE_ADMIN");
            role_Repository.save(adminRole);

            Role_Entity userRole = new Role_Entity();
            userRole.setName("ROLE_USER");
            role_Repository.save(userRole);
        }
        if (schedule_Repository.count() == 0) {
            int date = 2;
            for (int i = 0; i < 7; i++){
                User_Entity user = user_Repository.findById(1L).orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng này!"));
                Schedule_Entity schedule = new Schedule_Entity();
                if(i == 6){
                    schedule.setName("Chủ nhật");
                    schedule.setDescription("Tạo mặc định!");
                    schedule.setTime_add(Date.valueOf(LocalDate.now()));
                    schedule.setTimeupdate(Date.valueOf(LocalDate.now()));
                    schedule.setUseradd(user);
                    schedule.setUserupdate(user);
                    schedule_Repository.save(schedule);
                }else {
                    schedule.setName("Thứ "+date);
                    schedule.setDescription("Tạo mặc định!");
                    schedule.setTime_add(Date.valueOf(LocalDate.now()));
                    schedule.setTimeupdate(Date.valueOf(LocalDate.now()));
                    schedule.setUseradd(user);
                    schedule.setUserupdate(user);
                    schedule_Repository.save(schedule);
                }
                date++;
            }
        }
    }
}
