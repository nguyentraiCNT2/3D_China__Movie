package org.project4.backend.service.user_service;

import org.project4.backend.dto.User_Ratings_DTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface Ratings_Service {
    List<User_Ratings_DTO> getByUser(Long userid, Pageable pageable);
    List<User_Ratings_DTO> getByMovie(Long movieid, Pageable pageable);
    User_Ratings_DTO getByMovieAndUser(Long movieid, Long userid);
    void save(User_Ratings_DTO ratings);
}
