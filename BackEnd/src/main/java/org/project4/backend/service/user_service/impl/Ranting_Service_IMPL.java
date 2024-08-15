package org.project4.backend.service.user_service.impl;

import org.modelmapper.ModelMapper;
import org.project4.backend.dto.User_Ratings_DTO;
import org.project4.backend.entity.Movie_Entity;
import org.project4.backend.entity.User_Entity;
import org.project4.backend.entity.User_Ratings_Entity;
import org.project4.backend.repository.admin_repository.Movie_Repository;
import org.project4.backend.repository.admin_repository.User_Repository;
import org.project4.backend.repository.user_repository.Ratings_Repository;
import org.project4.backend.service.user_service.Ratings_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class Ranting_Service_IMPL implements Ratings_Service {
    @Autowired
    private Ratings_Repository ratings_Repository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private User_Repository userRepository;
    @Autowired
    private Movie_Repository movieRepository;
    @Override
    public List<User_Ratings_DTO> getByUser(Long userid, Pageable pageable) {
        return List.of();
    }

    @Override
    public List<User_Ratings_DTO> getByMovie(Long movieid, Pageable pageable) {
        return List.of();
    }

    @Override
    public User_Ratings_DTO getByMovieAndUser(Long movieid, Long userid) {
        try {
         if (movieid == null)
             throw new RuntimeException("Bạn chưa nhập id phim");
            if (userid == null)
                throw new RuntimeException(" bạn chọn id người dùng");
            User_Entity user = userRepository.findById(userid).orElseThrow(() -> new RuntimeException("Không có người dùng có id này"));
            Movie_Entity movie = movieRepository.findById(movieid).orElseThrow(() -> new RuntimeException("Không có phim có id này"));
            User_Ratings_Entity ratingsEntity = ratings_Repository.findByUserAndMovie(user, movie);
            if (ratingsEntity == null)
                throw new RuntimeException("Không có đánh giá nào");
            User_Ratings_DTO dto = modelMapper.map(ratingsEntity, User_Ratings_DTO.class);
            return dto;

        }catch (Exception e) {
            throw new RuntimeException("Không có đánh giá nào");
        }
    }

    @Override
    public void save(User_Ratings_DTO ratings) {
      if (ratings == null)
          throw new RuntimeException("Bạn chưa đánh giá phim");
        User_Entity user = userRepository.findById(ratings.getUser().getId()).orElseThrow(() -> new RuntimeException("Không có người dùng có id này"));
        Movie_Entity movie = movieRepository.findById(ratings.getMovie().getId()).orElseThrow(() -> new RuntimeException("Không có phim có id này"));
        User_Ratings_Entity ratingsEntity = new User_Ratings_Entity();
        ratingsEntity.setUser(user);
        ratingsEntity.setMovie(movie);
        ratingsEntity.setRating(ratings.getRating());
        ratings_Repository.save(ratingsEntity);
    }
}
