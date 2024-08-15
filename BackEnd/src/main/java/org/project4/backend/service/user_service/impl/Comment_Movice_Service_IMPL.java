package org.project4.backend.service.user_service.impl;

import org.modelmapper.ModelMapper;
import org.project4.backend.dto.Comment_Movie_DTO;
import org.project4.backend.dto.Notification_DTO;
import org.project4.backend.entity.Comment_Movie_Entity;
import org.project4.backend.entity.Movie_Entity;
import org.project4.backend.entity.Notification_Entity;
import org.project4.backend.entity.User_Entity;
import org.project4.backend.repository.admin_repository.Movie_Repository;
import org.project4.backend.repository.admin_repository.User_Repository;
import org.project4.backend.repository.user_repository.Comment_Movie_Repository;
import org.project4.backend.service.user_service.Comment_Movice_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Service
public class Comment_Movice_Service_IMPL implements Comment_Movice_Service {
    @Autowired
    private Comment_Movie_Repository comment_Movie_Repository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private User_Repository userRepository;
    @Autowired
    private Movie_Repository movieRepository;
    @Override
    public List<Comment_Movie_DTO> getByMovie(Long movieid, Pageable pageable) {
        List<Comment_Movie_DTO> user_DTOS = new ArrayList<>();
        // tìm kiếm tất cả người dùng
        Movie_Entity movie = movieRepository.findById(movieid).orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng"));
        List<Comment_Movie_Entity> list = comment_Movie_Repository.findByMovie(movie,pageable);
        // chuyển đổi sang DTO
        for (Comment_Movie_Entity item : list) {
            Comment_Movie_DTO commentMovieDto = modelMapper.map(item, Comment_Movie_DTO.class);
            user_DTOS.add(commentMovieDto);
        }
        return user_DTOS;
    }

    @Override
    public Comment_Movie_DTO save(Comment_Movie_DTO comment) {
        try {
            if (comment == null)
                throw new RuntimeException("Không tìm thấy người dùng");
            User_Entity user = userRepository.findById(comment.getUseradd().getId()).orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng"));
            Movie_Entity movie = movieRepository.findById(comment.getMovie().getId()).orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng"));
            Comment_Movie_Entity comment_Movie_Entity = modelMapper.map(comment, Comment_Movie_Entity.class);
            comment_Movie_Entity.setUseradd(user);
            comment_Movie_Entity.setMovie(movie);
            comment_Movie_Entity.setTimeadd(Date.valueOf(LocalDate.now()));
            comment_Movie_Entity.setContent(comment.getContent());
            comment_Movie_Repository.save(comment_Movie_Entity);
            return comment;
        }catch (Exception e) {
            throw new RuntimeException("Lỗi khi lưu");
        }
    }

    @Override
    public int totalItem() {
        return (int) comment_Movie_Repository.count();
    }
}
