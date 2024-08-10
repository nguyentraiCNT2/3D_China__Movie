package org.project4.backend.service.user_service.impl;

import org.modelmapper.ModelMapper;
import org.project4.backend.dto.Movie_DTO;
import org.project4.backend.entity.Movie_Entity;
import org.project4.backend.repository.user_repository.Home_Repository;
import org.project4.backend.service.user_service.Home_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
public class Home_Service_IMPL implements Home_Service {
    @Autowired
    private Home_Repository homeRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public List<Movie_DTO> getNewMovies(Pageable pageable) {
        List<Movie_DTO> result = new ArrayList<>();
        List<Movie_Entity> movie_entity = homeRepository.findByNewmovie(true, pageable);
        if (movie_entity == null)
            throw new RuntimeException("Không có bộ phim mới nào");
        for (Movie_Entity movie : movie_entity) {
            Movie_DTO dto = modelMapper.map(movie, Movie_DTO.class);
            dto.setImageurl(null);
            result.add(dto);
        }
        return result;
    }

    @Override
    public List<Movie_DTO> getHotMovies(Pageable pageable) {
        List<Movie_DTO> result = new ArrayList<>();
        List<Movie_Entity> movie_entity = homeRepository.findByHotmovie(true, pageable);
        if (movie_entity == null)
            throw new RuntimeException("Không có bộ phim mới nào");
        for (Movie_Entity movie : movie_entity) {
            Movie_DTO dto = modelMapper.map(movie, Movie_DTO.class);
            dto.setImageurl(null);
            result.add(dto);
        }
        return result;
    }

    @Override
    public List<Movie_DTO> getByTopmovie() {
        List<Movie_DTO> result = new ArrayList<>();
        List<Movie_Entity> movie_entity = homeRepository.findByTotalviews();
        if (movie_entity == null)
            throw new RuntimeException("Không có bộ phim nào");
        for (Movie_Entity movie : movie_entity) {
            Movie_DTO dto = modelMapper.map(movie, Movie_DTO.class);
            dto.setImageurl(null);
            result.add(dto);
        }
        return result;
    }

    @Override
    public Movie_DTO getImageByid(Long id) {
        try {
            Movie_Entity movie_entity = homeRepository.findById(id).get();
            if (movie_entity == null)
                throw new RuntimeException("Không có bộ phim nào");
            Movie_DTO dto = modelMapper.map(movie_entity, Movie_DTO.class);

            return dto;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public int totalItem(){
        return (int) homeRepository.count();
    }
}
