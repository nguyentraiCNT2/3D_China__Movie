package org.project4.backend.service.user_service.impl;

import org.modelmapper.ModelMapper;
import org.project4.backend.dto.Comment_Episode_DTO;
import org.project4.backend.dto.Comment_Movie_DTO;
import org.project4.backend.dto.Episode_DTO;
import org.project4.backend.dto.User_DTO;
import org.project4.backend.entity.*;
import org.project4.backend.repository.admin_repository.Episode_Repository;
import org.project4.backend.repository.admin_repository.User_Repository;
import org.project4.backend.repository.user_repository.Comment_Episode_Repository;
import org.project4.backend.service.user_service.Comment_Episode_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class Comment_Episode_Service_IMPL implements Comment_Episode_Service {
    @Autowired
    private Comment_Episode_Repository comment_Episode_Repository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private User_Repository userRepository;
    @Autowired
    private Episode_Repository episodeRepository;
    @Override
    public List<Comment_Episode_DTO> getByEpisode(Long episodeid, Pageable pageable) {
        List<Comment_Episode_DTO> user_DTOS = new ArrayList<>();
        // tìm kiếm tất cả người dùng
        Episode_enitty episodeEnitty = episodeRepository.findById(episodeid).orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng"));
        episodeEnitty.setFileepisodes(null);
        episodeEnitty.setSubtitles(null);
        List<Comment_Episode_Entity> list = comment_Episode_Repository.findByEpisode(episodeEnitty,pageable);
        // chuyển đổi sang DTO
        for (Comment_Episode_Entity item : list) {
            Comment_Episode_DTO commentMovieDto = new Comment_Episode_DTO();
            commentMovieDto.setId(item.getId());
            commentMovieDto.setContent(item.getContent());
            commentMovieDto.setEpisode(modelMapper.map(item.getEpisode(), Episode_DTO.class)); // chuyển đổi sang DTO (modelmapper) item.getEpisode());
            commentMovieDto.setUseradd(modelMapper.map(item.getUser_add(), User_DTO.class)); // chuyển đổi sang DTO (modelmapper) item.getUser_add());
            commentMovieDto.setTimeadd(item.getTime_add());
            user_DTOS.add(commentMovieDto);
        }
        return user_DTOS;
    }

    @Override
    public Comment_Episode_DTO save(Comment_Episode_DTO comment) {
        try {
            if (comment == null)
                throw new RuntimeException("Bạn chưa nhập bình luận");
            User_Entity user = userRepository.findById(comment.getUseradd().getId()).orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng"));
            Episode_enitty episodeEnitty = episodeRepository.findById(comment.getEpisode().getId()).orElseThrow(() -> new RuntimeException("Không tìm thấy bộ phim"));
            Comment_Episode_Entity comment_Movie_Entity = modelMapper.map(comment, Comment_Episode_Entity.class);
            comment_Movie_Entity.setUser_add(user);
            comment_Movie_Entity.setEpisode(episodeEnitty);
            comment_Movie_Entity.setTime_add(Date.valueOf(LocalDate.now()));
            comment_Movie_Entity.setContent(comment.getContent());
            comment_Episode_Repository.save(comment_Movie_Entity);
            return comment;
        }catch (Exception e){
            throw new RuntimeException("Lỗi khi lưu");
        }
    }

    @Override
    public int totalItem() {
        return (int) comment_Episode_Repository.count();
    }
}
