package org.project4.backend.service.admin_service;

import org.project4.backend.dto.Movie_DTO;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

public interface Movie_Service {
    List<Movie_DTO> getAll(Pageable pageable);

    List<Movie_DTO> getByNew_movie(Boolean new_movie, Pageable pageable);

    List<Movie_DTO> getByHot_movie(Boolean hot_movie, Pageable pageable);

    List<Movie_DTO> getByVip_movie(Boolean vip_movie, Pageable pageable);

    List<Movie_DTO> getByAuthor(String author, Pageable pageable);

    List<Movie_DTO> getByVn_name(String vn_name, Pageable pageable);

    List<Movie_DTO> getByCn_name(String cn_name, Pageable pageable);

    List<Movie_DTO> getByUser_add(Long user_add, Pageable pageable);

    List<Movie_DTO> getByUser_update(Long user_update, Pageable pageable);

    List<Movie_DTO> getByTime_add(Date time_add, Pageable pageable);

    List<Movie_DTO> getByTime_update(Date time_update, Pageable pageable);
    Movie_DTO getById(Long id);
    Movie_DTO getByMovieId(Long id);

    Movie_DTO create(Movie_DTO movie_DTO, MultipartFile file, String categorylist, String scheduleList)throws IOException;

    Movie_DTO update(Movie_DTO movie_DTO, MultipartFile file, String categorylist, String scheduleList)throws IOException;

    void delete(Long id);

    int totalItem();
}
