package org.project4.backend.config;

import jakarta.annotation.PostConstruct;
import org.project4.backend.entity.Role_Entity;
import org.project4.backend.repository.admin_repository.Role_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {
    @Autowired
    private Role_Repository role_Repository;
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
    }
}
