package org.project4.backend.controller.api.user;

import org.project4.backend.controller.output.Movie_output;
import org.project4.backend.dto.Movie_DTO;
import org.project4.backend.service.user_service.Home_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/home")
public class HomeController {
    @Autowired
    private Home_Service home_service;
    @GetMapping("/new")
    public Movie_output getNewMovies(@RequestParam("page") int page, @RequestParam("limit") int limit) {
        Movie_output result = new Movie_output();
        result.setPage(page);
        Pageable pageable = PageRequest.of(page - 1, limit);
        result.setListResult(home_service.getNewMovies(pageable));
        result.setTotalPage((int) Math.ceil((double) (home_service.totalItem()) / limit));
        return result;
    }
    @GetMapping("/hot")
    public Movie_output getHotMovies(@RequestParam("page") int page, @RequestParam("limit") int limit) {
        Movie_output result = new Movie_output();
        result.setPage(page);
        Pageable pageable = PageRequest.of(page - 1, limit);
        result.setListResult(home_service.getHotMovies(pageable));
        result.setTotalPage((int) Math.ceil((double) (home_service.totalItem()) / limit));
        return result;
    }
    @GetMapping("/top")
    public List<Movie_DTO> getHotMovies() {
            List<Movie_DTO> list = home_service.getByTopmovie();
            return list;
    }
    @GetMapping("/view/{id}")
    public ResponseEntity<byte[]> viewFile(@PathVariable Long id) {
        Movie_DTO fileDTO = home_service.getImageByid(id);
        if (fileDTO != null && fileDTO.getImageurl() != null) {
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + fileDTO.getId() + "\"")
                    .contentType(MediaType.IMAGE_PNG)
                    .contentType(MediaType.IMAGE_JPEG)
                    .contentType(MediaType.IMAGE_GIF)
                    .body(fileDTO.getImageurl());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
