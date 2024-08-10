package org.project4.backend.service.admin_service;

import org.project4.backend.dto.Category_Movie_DTO;
import org.project4.backend.dto.Movie_DTO;

import java.awt.print.Pageable;
import java.util.List;

public interface Category_Movie_Service {

    List<Category_Movie_DTO> getByMovie_id(Long movie_id);

    List<Category_Movie_DTO> getByCategory_id(Long category_id);
}
