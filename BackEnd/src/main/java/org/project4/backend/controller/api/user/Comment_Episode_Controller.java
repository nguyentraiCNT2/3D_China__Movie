package org.project4.backend.controller.api.user;

import org.project4.backend.controller.output.Comment_Episode_Output;
import org.project4.backend.controller.output.Comment_movie_output;
import org.project4.backend.dto.*;
import org.project4.backend.service.user_service.Comment_Episode_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user/comment/episode")
public class Comment_Episode_Controller {
    @Autowired
    private Comment_Episode_Service comment_Episode_Service;
    @GetMapping("/byep/{id}")
    public Comment_Episode_Output getAll(@PathVariable Long id, @RequestParam("page") int page, @RequestParam("limit") int limit) {
        Comment_Episode_Output result = new Comment_Episode_Output();
        result.setPage(page);
        Pageable pageable = PageRequest.of(page - 1, limit);
        result.setListResult(comment_Episode_Service.getByEpisode(id,pageable));
        result.setTotalPage((int) Math.ceil((double) (comment_Episode_Service.totalItem()) / limit));
        return result;
    }
    @PostMapping("/add")
    public ResponseEntity<?> addComment(@RequestParam("epid") Long epid, @RequestParam("userid") Long userid, @RequestParam("content") String content) {
        try {
            User_DTO user = new User_DTO();
            user.setId(userid);
            Episode_DTO episodeDto = new Episode_DTO();
            episodeDto.setId(epid);
            Comment_Episode_DTO commentMovieDto = new Comment_Episode_DTO();
            commentMovieDto.setEpisode(episodeDto);
            commentMovieDto.setUseradd(user);
            commentMovieDto.setContent(content);
            comment_Episode_Service.save(commentMovieDto);
            return ResponseEntity.status(HttpStatus.OK).body("success");
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
