package org.project4.backend.controller.api.admin;

import org.project4.backend.dto.Category_Movie_DTO;
import org.project4.backend.dto.Movie_DTO;
import org.project4.backend.service.admin_service.Category_Movie_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/category_movie")
public class Category_movie_Controller {
    @Autowired
    private Category_Movie_Service categoryMovieService;
    @GetMapping("/getBymovie/{id}")
    public List<Category_Movie_DTO> getByMovie_id(@PathVariable("id") Long movie_id) {
        List<Category_Movie_DTO> list_entity = new ArrayList<>();
            List<Category_Movie_DTO> list = categoryMovieService.getByMovie_id(movie_id);

            for (Category_Movie_DTO item : list) {
                Movie_DTO movie_dto =item.getMovie();
                movie_dto.setImageurl(null);
                item.setMovie(movie_dto);
                list_entity.add(item);
            }
            return list_entity;
    }

    @GetMapping("/getBycategory/{id}")
    public List<Category_Movie_DTO> getByCategory_id(@PathVariable("id") Long category_id) {
        return categoryMovieService.getByCategory_id(category_id);
    }
}
