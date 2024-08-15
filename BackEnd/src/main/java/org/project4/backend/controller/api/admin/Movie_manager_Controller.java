package org.project4.backend.controller.api.admin;

import org.project4.backend.config.TokenUtil;
import org.project4.backend.controller.output.Movie_output;
import org.project4.backend.dto.Movie_DTO;
import org.project4.backend.dto.User_DTO;
import org.project4.backend.service.admin_service.Movie_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.*;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/admin/movies")
public class Movie_manager_Controller {
    @Autowired
    private Movie_Service movieService;

    @GetMapping("/all")
    public Movie_output getAll(@RequestParam("page") int page, @RequestParam("limit") int limit) {
        Movie_output result = new Movie_output();
        result.setPage(page);
        Pageable pageable = PageRequest.of(page - 1, limit);
        result.setListResult(movieService.getAll(pageable));
        result.setTotalPage((int) Math.ceil((double) (movieService.totalItem()) / limit));
        return result;
    }
    @GetMapping("/getbyname")
    public Movie_output getAll(@RequestParam("name") String name,@RequestParam("page") int page, @RequestParam("limit") int limit) {
        Movie_output result = new Movie_output();
        result.setPage(page);
        Pageable pageable = PageRequest.of(page - 1, limit);
        result.setListResult(movieService.getByVn_name(name,pageable));
        result.setTotalPage((int) Math.ceil((double) (movieService.totalItem()) / limit));
        return result;
    }


    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestParam("vn_name") String vn_name, @RequestParam("cn_name") String cn_name,
                                         @RequestParam("description") String description, @RequestParam("user_add") Long user_add,
                                         @RequestParam("author") String author,@RequestParam("categorylist") String categorylist,
                                         @RequestParam("episode_number") Long episode_number, @RequestParam("status") String status,
                                         @RequestParam("new_movie") Boolean new_movie, @RequestParam("hot_movie") Boolean hot_movie,
                                         @RequestParam("vip_movie") Boolean vip_movie, @RequestParam("price") BigDecimal price,
                                         @RequestParam("image") MultipartFile file,@RequestParam("year") Long year,
                                         @RequestParam("schedulelist") String schedulelist) {
        try {

            //Tạo user
            User_DTO user_dto = new User_DTO();
            user_dto.setId(user_add);
            //Tạo movie
            Movie_DTO movie_dto = new Movie_DTO();
            movie_dto.setCnname(cn_name);
            movie_dto.setVnname(vn_name);
            movie_dto.setDescription(description);
            movie_dto.setUseradd(user_dto);
            movie_dto.setUserupdate(user_dto);
            movie_dto.setAuthor(author);
            movie_dto.setEpisodenumber(episode_number);
            movie_dto.setStatus(status);
            movie_dto.setNewmovie(new_movie);
            movie_dto.setHotmovie(hot_movie);
            movie_dto.setVipmovie(vip_movie);
            movie_dto.setPrice(price);
            movie_dto.setTotalviews(0L);
            movie_dto.setYear(year);
            movieService.create(movie_dto, file, categorylist,schedulelist );
            return new ResponseEntity<>("Thêm mới thành công!", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }
    @PutMapping("/update/{id}")
    public ResponseEntity<String> update( @PathVariable("id") Long id,@RequestParam("vn_name") String vn_name, @RequestParam("cn_name") String cn_name,
                                          @RequestParam("description") String description, @RequestParam("user_add") Long user_add,
                                          @RequestParam("author") String author,@RequestParam("categorylist") String categorylist,
                                          @RequestParam("episode_number") Long episode_number, @RequestParam("status") String status,
                                          @RequestParam("new_movie") Boolean new_movie, @RequestParam("hot_movie") Boolean hot_movie,
                                          @RequestParam("vip_movie") Boolean vip_movie, @RequestParam("price") BigDecimal price,
                                          @RequestParam(value = "image", required = false) MultipartFile file,
                                          @RequestParam("year") Long year, @RequestParam("schedulelist") String schedulelist) {
        try {

            //Tạo user
            User_DTO user_dto = new User_DTO();
            user_dto.setId(user_add);
            //Tạo movie
            Movie_DTO movie_dto = new Movie_DTO();
            movie_dto.setId(id);
            movie_dto.setCnname(cn_name);
            movie_dto.setVnname(vn_name);
            movie_dto.setDescription(description);
            movie_dto.setUseradd(user_dto);
            movie_dto.setUserupdate(user_dto);
            movie_dto.setAuthor(author);
            movie_dto.setEpisodenumber(episode_number);
            movie_dto.setStatus(status);
            movie_dto.setNewmovie(new_movie);
            movie_dto.setHotmovie(hot_movie);
            movie_dto.setVipmovie(vip_movie);
            movie_dto.setPrice(price);
            movie_dto.setYear(year);
            movieService.update(movie_dto, file, categorylist, schedulelist);

            return new ResponseEntity<>("Thêm mới thành công!", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }
    @GetMapping("/getbyid/{id}")
    public ResponseEntity<?> getByid(@PathVariable Long id) {
        try {
            Movie_DTO movieDTO = movieService.getByMovieId(id);
            return new ResponseEntity<>(movieDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @Transactional
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        try {
            movieService.delete(id);
            return new ResponseEntity<>("Xóa thành công!", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/view/{id}")
    public ResponseEntity<byte[]> viewFile(@PathVariable Long id) {
        Movie_DTO fileDTO = movieService.getById(id);
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
