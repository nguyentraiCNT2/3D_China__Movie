package org.project4.backend.service.admin_service.impl;

import org.project4.backend.repository.admin_repository.Category_Repository;
import org.project4.backend.repository.admin_repository.Movie_Repository;
import org.project4.backend.repository.admin_repository.User_Repository;
import org.project4.backend.service.admin_service.Static_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Static_Service_IMPL implements Static_Service {
    @Autowired
    private User_Repository userRepository;
    @Autowired
    private Category_Repository categoryRepository;
    @Autowired
    private Movie_Repository movieRepository;
    @Override
    public int tongSoUser() {
        return (int) userRepository.count();
    }

    @Override
    public int tongSoCategory() {
        return (int) categoryRepository.count();
    }

    @Override
    public int tongSoMovie() {
        return (int) movieRepository.count();
    }
}
