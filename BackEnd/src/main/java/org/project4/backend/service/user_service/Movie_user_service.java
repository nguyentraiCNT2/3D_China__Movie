package org.project4.backend.service.user_service;

import org.project4.backend.dto.Movie_DTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface Movie_user_service {
    List<Movie_DTO> getListMovie(Long category, Long year,Boolean vip, String sortBy,Pageable pageable);
    List<Movie_DTO> getByCategoryid(Long categoryid, Pageable pageable);
    int totalItem();
}
