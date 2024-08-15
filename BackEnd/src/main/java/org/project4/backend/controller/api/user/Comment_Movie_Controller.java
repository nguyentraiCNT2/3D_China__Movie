package org.project4.backend.controller.api.user;

import org.project4.backend.controller.output.Comment_movie_output;
import org.project4.backend.controller.output.User_OutPut;
import org.project4.backend.dto.Comment_Movie_DTO;
import org.project4.backend.dto.Movie_DTO;
import org.project4.backend.dto.User_DTO;
import org.project4.backend.service.user_service.Comment_Movice_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user/comment/movie")
public class Comment_Movie_Controller {
    @Autowired
    private Comment_Movice_Service comment_movice_service;
    @GetMapping("/bymovie/{id}")
    public Comment_movie_output getAll(@PathVariable Long id, @RequestParam("page") int page, @RequestParam("limit") int limit) {
        Comment_movie_output result = new Comment_movie_output();
        result.setPage(page);
        Pageable pageable = PageRequest.of(page - 1, limit);
        result.setListResult(comment_movice_service.getByMovie(id,pageable));
        result.setTotalPage((int) Math.ceil((double) (comment_movice_service.totalItem()) / limit));
        return result;
    }
    @PostMapping("/add")
    public ResponseEntity<?> addComment(@RequestParam("movieid") Long movieid, @RequestParam("userid") Long userid, @RequestParam("content") String content) {
        try {
            User_DTO user = new User_DTO();
            user.setId(userid);
            Movie_DTO movie = new Movie_DTO();
            movie.setId(movieid);
            Comment_Movie_DTO commentMovieDto = new Comment_Movie_DTO();
            commentMovieDto.setMovie(movie);
            commentMovieDto.setUseradd(user);
            commentMovieDto.setContent(content);
            comment_movice_service.save(commentMovieDto);
            return ResponseEntity.status(HttpStatus.OK).body("success");
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
