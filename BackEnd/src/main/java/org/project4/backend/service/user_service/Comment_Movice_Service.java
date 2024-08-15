package org.project4.backend.service.user_service;

import org.project4.backend.dto.Comment_Movie_DTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface Comment_Movice_Service {
    List<Comment_Movie_DTO> getByMovie(Long movieid, Pageable pageable);

    Comment_Movie_DTO save(Comment_Movie_DTO comment);
    int totalItem();
}
