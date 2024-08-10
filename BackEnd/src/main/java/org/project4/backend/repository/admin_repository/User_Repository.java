package org.project4.backend.repository.admin_repository;

import org.project4.backend.entity.Role_Entity;
import org.project4.backend.entity.User_Entity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface User_Repository extends JpaRepository<User_Entity, Long> {
    // tim kiem theo username
    User_Entity findByUsername(String username);
    // tìm kiểm gần đúng theo username
    List<User_Entity> findByUsernameLike(String username, Pageable pageable);
    // tìm kiếm theo quyền hạn
    List<User_Entity> findByRole(Role_Entity role, Pageable pageable);

    // tìm kiếm theo email
    User_Entity findByEmail(String email);

    // tìm kiếm theo username và password
    User_Entity findByUsernameAndPassword(String username, String password);

    // tìm kiếm theo username và email
    User_Entity findByUsernameAndEmail(String username, String email);
}
