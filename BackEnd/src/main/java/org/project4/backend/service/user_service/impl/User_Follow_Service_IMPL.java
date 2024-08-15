package org.project4.backend.service.user_service.impl;

import org.modelmapper.ModelMapper;
import org.project4.backend.dto.Comment_Movie_DTO;
import org.project4.backend.dto.User_Follow_DTO;
import org.project4.backend.entity.Comment_Movie_Entity;
import org.project4.backend.entity.Movie_Entity;
import org.project4.backend.entity.User_Entity;
import org.project4.backend.entity.User_Follow_Entity;
import org.project4.backend.repository.admin_repository.Movie_Repository;
import org.project4.backend.repository.admin_repository.User_Repository;
import org.project4.backend.repository.user_repository.User_Follow_Repository;
import org.project4.backend.service.user_service.User_Follow_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class User_Follow_Service_IMPL implements User_Follow_Service {
    @Autowired
    private User_Follow_Repository userFollowRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private User_Repository userRepository;
    @Autowired
    private Movie_Repository movieRepository;
    @Override
    public List<User_Follow_DTO> findByUser(Long userId, Pageable pageable) {
        List<User_Follow_DTO> user_DTOS = new ArrayList<>();
        // tìm kiếm tất cả người dùng
        User_Entity user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng"));
        List<User_Follow_Entity> list = userFollowRepository.findByUser(user,pageable);
        // chuyển đổi sang DTO
        for (User_Follow_Entity item : list) {
            User_Follow_DTO commentMovieDto = modelMapper.map(item, User_Follow_DTO.class);
            user_DTOS.add(commentMovieDto);
        }
        return user_DTOS;
    }

    @Override
    public void save(User_Follow_DTO userFollowDTO) {
        if (userFollowDTO == null)
            throw new RuntimeException("Không tìm thấy người dùng");
        User_Entity user = userRepository.findById(userFollowDTO.getUser().getId()).orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng"));
        Movie_Entity movie = movieRepository.findById(userFollowDTO.getMovie().getId()).orElseThrow(() -> new RuntimeException("Không tìm thấy ngollectors"));
        List<User_Follow_Entity> check = userFollowRepository.findByUserAndMovie(user,movie);
        if (check.size() > 0)
            throw new RuntimeException("Bạn Đã theo dõi phim này");
        User_Follow_Entity userFollowEntity = modelMapper.map(userFollowDTO, User_Follow_Entity.class);
        userFollowEntity.setUser(user);
        userFollowEntity.setMovie(movie);
        userFollowRepository.save(userFollowEntity);
    }

    @Override
    public void delete(Long id) {
        try {
            User_Follow_Entity userFollowEntity = userFollowRepository.findById(id).orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng"));
            userFollowRepository.delete(userFollowEntity);
        }catch (Exception e) {
            throw new RuntimeException("Không tìm thấy người dùng");
        }
    }

    @Override
    public int totalItem() {
        return (int) userFollowRepository.count();
    }
}
