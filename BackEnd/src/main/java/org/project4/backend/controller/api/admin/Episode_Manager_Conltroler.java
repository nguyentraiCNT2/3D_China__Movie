package org.project4.backend.controller.api.admin;

import jakarta.servlet.http.HttpServletResponse;
import org.project4.backend.controller.output.Episode_Output;
import org.project4.backend.controller.output.Movie_output;
import org.project4.backend.dto.Episode_DTO;
import org.project4.backend.dto.Movie_DTO;
import org.project4.backend.dto.User_DTO;
import org.project4.backend.service.admin_service.Epsicode_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/admin/episode")
public class Episode_Manager_Conltroler {
    @Autowired
    private Epsicode_Service episodeService;
    @GetMapping("/getBymovie/{id}")
    public Episode_Output getAll(@PathVariable Long id, @RequestParam("page") int page, @RequestParam("limit") int limit) {
        Episode_Output result = new Episode_Output();
        result.setPage(page);
        Pageable pageable = PageRequest.of(page - 1, limit);
        result.setListResult(episodeService.getByMovie(id,pageable));
        result.setTotalPage((int) Math.ceil((double) (episodeService.totalItem()) / limit));
        return result;
    }
    @GetMapping("/getBymovie/all/{id}")
    public Episode_Output getbymovie(@PathVariable Long id) {
        Episode_Output result = new Episode_Output();
        result.setPage(1);
        result.setListResult(episodeService.getByMovie(id));
        result.setTotalPage((int) Math.ceil((double) (episodeService.totalItem()) / 10));
        return result;
    }
    @GetMapping("/getbyid/{id}")
    public ResponseEntity<?> getByid(@PathVariable Long id) {
        try {
            Episode_DTO episodeDTO = episodeService.getByEpId(id);
            return new ResponseEntity<>(episodeDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestParam("name") String name, @RequestParam("description") String description,
                                         @RequestParam("useradd") Long useradd, @RequestParam("views") Long views,
                                         @RequestParam("likes") Long likes, @RequestParam("movie") Long movie,
                                         @RequestParam(value = "videofile", required = false) MultipartFile videofile, @RequestParam(value = "subfile",required = false) MultipartFile subfile) {
        try {
            //Tạo user
            User_DTO user_dto = new User_DTO();
            user_dto.setId(useradd);
            Movie_DTO movie_dto = new Movie_DTO();
            movie_dto.setId(movie);
            //Tạo tâp phim
           Episode_DTO episode_dto = new Episode_DTO();
            episode_dto.setName(name);
            episode_dto.setDescription(description);
            episode_dto.setViews(views);
            episode_dto.setLikes(likes);
            episode_dto.setUseradd(user_dto);
            episode_dto.setUserupdate(user_dto);
            episode_dto.setMovie(movie_dto);
            episodeService.create(episode_dto, videofile, subfile);
            return new ResponseEntity<>("Thêm mới thành công!", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable Long id,@RequestParam("name") String name, @RequestParam("description") String description,
                                         @RequestParam("useradd") Long useradd, @RequestParam("views") Long views,
                                         @RequestParam("likes") Long likes, @RequestParam("movie") Long movie,
                                         @RequestParam(value = "videofile",required = false) MultipartFile videofile, @RequestParam(value = "subfile",required = false) MultipartFile subfile) {
        try {

            //Tạo user
            User_DTO user_dto = new User_DTO();
            user_dto.setId(useradd);
            Movie_DTO movie_dto = new Movie_DTO();
            movie_dto.setId(movie);
            //Tạo tâp phim
            Episode_DTO episode_dto = new Episode_DTO();
            episode_dto.setId(id);
            episode_dto.setName(name);
            episode_dto.setDescription(description);
            episode_dto.setViews(views);
            episode_dto.setLikes(likes);
            episode_dto.setUseradd(user_dto);
            episode_dto.setUserupdate(user_dto);
            episode_dto.setMovie(movie_dto);
            episodeService.update(episode_dto, videofile, subfile);
            return new ResponseEntity<>("Thêm mới thành công!", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Transactional
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        try {
            episodeService.delete(id);
            return new ResponseEntity<>("Xóa thành công!", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/video/{id}")
    public ResponseEntity<byte[]> getVideo(@PathVariable Long id) {

        Episode_DTO video = episodeService.getById(id);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + video.getId() + ".mp4\"")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(video.getFileepisodes());
    }

    @GetMapping("/sub/{id}")
    public ResponseEntity<byte[]> viewSUB(@PathVariable Long id) {
        Episode_DTO fileDTO = episodeService.getById(id);

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDTO.getId() + ".srt\"")
                    .contentType(MediaType.TEXT_PLAIN)
                    .body(fileDTO.getSubtitles());

    }
}
