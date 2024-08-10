package org.project4.backend.service.admin_service.impl;

import org.modelmapper.ModelMapper;
import org.project4.backend.dto.Category_Movie_DTO;
import org.project4.backend.dto.Movie_DTO;
import org.project4.backend.entity.Category_Entity;
import org.project4.backend.entity.Category_Movie_Entity;
import org.project4.backend.entity.Movie_Entity;
import org.project4.backend.repository.admin_repository.Category_Movie_Repository;
import org.project4.backend.repository.admin_repository.Category_Repository;
import org.project4.backend.repository.admin_repository.Movie_Repository;
import org.project4.backend.service.admin_service.Category_Movie_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Category_Movice_Service_IMPL implements Category_Movie_Service {
    @Autowired
    private Category_Movie_Repository categoryMovieRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private Movie_Repository movieRepository;
    @Autowired
    private Category_Repository categoryRepository;
    @Override
    public List<Category_Movie_DTO> getByMovie_id(Long movie_id) {
        List<Category_Movie_DTO> result = new ArrayList<>();
        Movie_Entity movie = movieRepository.findById(movie_id).orElseThrow(() -> new RuntimeException("Không có bộ phim này"));
        List<Category_Movie_Entity> categoryMovieEntities = categoryMovieRepository.findByMovie(movie);
        for (Category_Movie_Entity categoryMovieEntity : categoryMovieEntities) {
            Category_Movie_DTO dto = modelMapper.map(categoryMovieEntity, Category_Movie_DTO.class);
            result.add(dto);
        }
        return result;
    }

    @Override
    public List<Category_Movie_DTO> getByCategory_id(Long category_id) {
        List<Category_Movie_DTO> result = new ArrayList<>();
        Category_Entity category = categoryRepository.findById(category_id).orElseThrow(() -> new RuntimeException("Không có bộ the loại"));
        List<Category_Movie_Entity> categoryMovieEntities = categoryMovieRepository.findByCategory(category);
        for (Category_Movie_Entity categoryMovieEntity : categoryMovieEntities) {
            Category_Movie_DTO dto = modelMapper.map(categoryMovieEntity, Category_Movie_DTO.class);

            result.add(dto);
        }
        return result;
    }
}
