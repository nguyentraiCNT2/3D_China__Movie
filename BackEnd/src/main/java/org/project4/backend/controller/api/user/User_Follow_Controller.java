package org.project4.backend.controller.api.user;

import org.project4.backend.controller.output.Movie_output;
import org.project4.backend.controller.output.User_Follow_Output;
import org.project4.backend.dto.Movie_DTO;
import org.project4.backend.dto.Orders_DTO;
import org.project4.backend.dto.User_DTO;
import org.project4.backend.dto.User_Follow_DTO;
import org.project4.backend.service.user_service.User_Follow_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user/follow")
public class User_Follow_Controller {
    @Autowired
    private User_Follow_Service userFollowService;

    @GetMapping("/getbyuser/{id}")
    public User_Follow_Output getAll(@PathVariable Long id, @RequestParam("page") int page, @RequestParam("limit") int limit) {
        User_Follow_Output result = new User_Follow_Output();
        result.setPage(page);
        Pageable pageable = PageRequest.of(page - 1, limit);
        result.setListResult(userFollowService.findByUser(id, pageable));
        result.setTotalPage((int) Math.ceil((double) (userFollowService.totalItem()) / limit));
        return result;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addFollow(@RequestParam("userid") Long userid, @RequestParam("movieid") Long movieid) {
        try {
            User_DTO user = new User_DTO();
            user.setId(userid);
            Movie_DTO movie = new Movie_DTO();
            movie.setId(movieid);
            User_Follow_DTO dto = new User_Follow_DTO();
            dto.setUser(user);
            dto.setMovie(movie);
            userFollowService.save(dto);
            return new ResponseEntity<>("Mua phim thành công!", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Transactional
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteFollow(@PathVariable Long id) {
        try {
            userFollowService.delete(id);
            return new ResponseEntity<>("Xoa thanh cong", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
