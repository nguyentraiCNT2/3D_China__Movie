package org.project4.backend.service.user_service.impl;

import org.modelmapper.ModelMapper;
import org.project4.backend.dto.Movie_DTO;
import org.project4.backend.entity.Category_Entity;
import org.project4.backend.entity.Category_Movie_Entity;
import org.project4.backend.entity.Movie_Entity;
import org.project4.backend.repository.admin_repository.Category_Movie_Repository;
import org.project4.backend.repository.admin_repository.Category_Repository;
import org.project4.backend.repository.user_repository.Movie_User_repository;
import org.project4.backend.service.user_service.Movie_user_service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Movie_user_Service_IMPL implements Movie_user_service {
    @Autowired
    private Movie_User_repository movie_user_Repository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private Category_Movie_Repository categoryMovieRepository;
    @Autowired
    private Category_Repository  categoryRepository;
    @Override
    public List<Movie_DTO> getListMovie(Long category, Long year, Boolean vip, String sortBy, Pageable pageable) {
        List<Movie_DTO> result = new ArrayList<>();
        Page<Movie_Entity> movie_entity = movie_user_Repository.findMovies(category, year, vip, sortBy, pageable);
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
    public List<Movie_DTO> getByCategoryid(Long categoryid, Pageable pageable) {
        List<Movie_DTO> result = new ArrayList<>();
        Category_Entity category_entity = categoryRepository.findById(categoryid).orElseThrow(() -> new RuntimeException("Không có thể tìm thấy danh mục này"));
        List<Category_Movie_Entity> categoryMovieEntities = categoryMovieRepository.findByCategory(category_entity, pageable);
        if (categoryMovieEntities == null)
            throw new RuntimeException("Không có bộ phim nào");
        for (Category_Movie_Entity categoryMovie : categoryMovieEntities) {
            Movie_DTO dto = modelMapper.map(categoryMovie.getMovie(), Movie_DTO.class);
            dto.setImageurl(null);
            result.add(dto);
        }
        return result;
    }

    @Override
    public int totalItem() {
        return (int) movie_user_Repository.count();
    }
}
