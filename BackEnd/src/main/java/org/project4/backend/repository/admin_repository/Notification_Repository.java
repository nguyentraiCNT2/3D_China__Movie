package org.project4.backend.repository.admin_repository;

import org.project4.backend.entity.Notification_Entity;
import org.project4.backend.entity.User_Entity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Notification_Repository extends JpaRepository<Notification_Entity, Long> {
    List<Notification_Entity> findByUser(User_Entity user, Pageable pageable);

    List<Notification_Entity> findByUser(User_Entity user);
}
