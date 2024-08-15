package org.project4.backend.controller.api.user;

import org.project4.backend.dto.Movie_DTO;
import org.project4.backend.dto.User_DTO;
import org.project4.backend.dto.User_Ratings_DTO;
import org.project4.backend.service.user_service.Ratings_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user/ratings")
public class Ratings_Controller {
    @Autowired
    private Ratings_Service ratingsService;
    @PostMapping("/review")
    public ResponseEntity<?> save(@RequestParam("userid") Long userid, @RequestParam("movieid") Long movieid, @RequestParam("rating") Long rating) {
        try {
            User_DTO user = new User_DTO();
            user.setId(userid);
            Movie_DTO movie = new Movie_DTO();
            movie.setId(movieid);
            User_Ratings_DTO ratings = new User_Ratings_DTO();
            ratings.setUser(user);
            ratings.setMovie(movie);
            ratings.setRating(rating);
            ratingsService.save(ratings);
            return  ResponseEntity.ok("Đánh giá thành công!");
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/getuserandmovie")
    public ResponseEntity<?> getByMovieAndUser(@RequestParam("userid") Long userid, @RequestParam("movieid") Long movieid) {
        try {
            User_Ratings_DTO ratings = ratingsService.getByMovieAndUser(movieid, userid);
            return ResponseEntity.ok(ratings);
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
