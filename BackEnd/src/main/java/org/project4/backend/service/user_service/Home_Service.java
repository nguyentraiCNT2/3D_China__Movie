package org.project4.backend.service.user_service;

import org.project4.backend.dto.Movie_DTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface Home_Service {
    List<Movie_DTO> getNewMovies(Pageable pageable);
    List<Movie_DTO> getHotMovies(Pageable pageable);
    List<Movie_DTO> getByTopmovie();
    Movie_DTO getImageByid(Long id);
    int totalItem();
}
