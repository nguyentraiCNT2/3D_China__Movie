package org.project4.backend.service.admin_service.impl;

import org.modelmapper.ModelMapper;
import org.project4.backend.dto.Movie_DTO;
import org.project4.backend.entity.*;
import org.project4.backend.repository.admin_repository.*;
import org.project4.backend.service.admin_service.Movie_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

@Service
public class Movie_Service_IMPL implements Movie_Service {
    @Autowired
    private Movie_Repository movieRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private User_Repository userRepository;
    @Autowired
    private Category_Repository categoryRepository;
    @Autowired
    private Category_Movie_Repository categoryMovieRepository;
    @Autowired
    private Episode_Repository episodeRepository;
    @Autowired
    private Schedule_Movie_Repository scheduleMovieRepository;
    @Autowired
    private Schedule_Repository scheduleRepository;
    @Override
    public List<Movie_DTO> getAll(Pageable pageable) {
        List<Movie_DTO> result = new ArrayList<>();
        List<Movie_Entity> movie_entity = movieRepository.findAll(pageable).getContent();
        if (movie_entity == null)
            throw new RuntimeException("Không có bộ phim nào");
        for (Movie_Entity movie : movie_entity) {
            Movie_DTO dto = modelMapper.map(movie, Movie_DTO.class);
            dto.setImageurl(null);
            result.add(dto);
        }
        return result;
    }

    @Override
    public List<Movie_DTO> getByNew_movie(Boolean new_movie, Pageable pageable) {
        List<Movie_DTO> result = new ArrayList<>();
        List<Movie_Entity> movie_entity = movieRepository.findByNewmovie(new_movie, pageable);
        if (movie_entity == null)
            throw new RuntimeException("Không có bộ phim mới nào");
        for (Movie_Entity movie : movie_entity) {
            Movie_DTO dto = modelMapper.map(movie, Movie_DTO.class);
            dto.setImageurl(Base64.getEncoder().encodeToString(movie.getImageurl()).getBytes());
            result.add(dto);
        }
        return result;
    }

    @Override
    public List<Movie_DTO> getByHot_movie(Boolean hot_movie, Pageable pageable) {
        List<Movie_DTO> result = new ArrayList<>();
        List<Movie_Entity> movie_entity = movieRepository.findByHotmovie(hot_movie, pageable);
        if (movie_entity == null)
            throw new RuntimeException("Không có bộ phim hot nào");
        for (Movie_Entity movie : movie_entity) {
            Movie_DTO dto = modelMapper.map(movie, Movie_DTO.class);
            dto.setImageurl(Base64.getEncoder().encodeToString(movie.getImageurl()).getBytes());
            result.add(dto);
        }
        return result;
    }

    @Override
    public List<Movie_DTO> getByVip_movie(Boolean vip_movie, Pageable pageable) {
        List<Movie_DTO> result = new ArrayList<>();
        List<Movie_Entity> movie_entity = movieRepository.findByVipmovie(vip_movie, pageable);
        if (movie_entity == null)
            throw new RuntimeException("Không có bộ phim trả phí nào");
        for (Movie_Entity movie : movie_entity) {
            Movie_DTO dto = modelMapper.map(movie, Movie_DTO.class);
            dto.setImageurl(Base64.getEncoder().encodeToString(movie.getImageurl()).getBytes());
            result.add(dto);
        }
        return result;
    }

    @Override
    public List<Movie_DTO> getByAuthor(String author, Pageable pageable) {
        List<Movie_DTO> result = new ArrayList<>();
        List<Movie_Entity> movie_entity = movieRepository.findByAuthorLike("%"+author+"%", pageable);
        if (movie_entity == null)
            throw new RuntimeException("Không có bộ phim trả phí nào");
        for (Movie_Entity movie : movie_entity) {
            Movie_DTO dto = modelMapper.map(movie, Movie_DTO.class);
            dto.setImageurl(Base64.getEncoder().encodeToString(movie.getImageurl()).getBytes());
            result.add(dto);
        }
        return result;
    }

    @Override
    public List<Movie_DTO> getByVn_name(String vn_name, Pageable pageable) {
        List<Movie_DTO> result = new ArrayList<>();
        List<Movie_Entity> movie_entity = movieRepository.findByVnnameLike("%"+vn_name+"%", pageable);
        if (movie_entity == null)
            throw new RuntimeException("Không có bộ phim trả phí nào");
        for (Movie_Entity movie : movie_entity) {
            Movie_DTO dto = modelMapper.map(movie, Movie_DTO.class);
            dto.setImageurl(null);
            result.add(dto);
        }
        return result;
    }

    @Override
    public List<Movie_DTO> getByCn_name(String cn_name, Pageable pageable) {
        List<Movie_DTO> result = new ArrayList<>();
        List<Movie_Entity> movie_entity = movieRepository.findByCnnameLike("%"+cn_name+"%", pageable);
        if (movie_entity == null)
            throw new RuntimeException("Không có bộ phim có tên này");
        for (Movie_Entity movie : movie_entity) {
            Movie_DTO dto = modelMapper.map(movie, Movie_DTO.class);
            dto.setImageurl(null);
            result.add(dto);
        }
        return result;
    }

    @Override
    public List<Movie_DTO> getByUser_add(Long user_add, Pageable pageable) {
        List<Movie_DTO> result = new ArrayList<>();
        User_Entity user_entity = userRepository.findById(user_add).orElseThrow(() -> new RuntimeException("Không có người dùng này"));
        List<Movie_Entity> movie_entity = movieRepository.findByUseradd(user_entity, pageable);
        if (movie_entity == null)
            throw new RuntimeException("Không có bộ phim nào");
        for (Movie_Entity movie : movie_entity) {
            Movie_DTO dto = modelMapper.map(movie, Movie_DTO.class);
            dto.setImageurl(null);
            result.add(dto);
        }
        return result;
    }

    @Override
    public List<Movie_DTO> getByUser_update(Long user_update, Pageable pageable) {
        List<Movie_DTO> result = new ArrayList<>();
        User_Entity user_entity = userRepository.findById(user_update).orElseThrow(() -> new RuntimeException("Không có người dùng này"));
        List<Movie_Entity> movie_entity = movieRepository.findByUserupdate(user_entity, pageable);
        if (movie_entity == null)
            throw new RuntimeException("Không có bộ phim nào");
        for (Movie_Entity movie : movie_entity) {
            Movie_DTO dto = modelMapper.map(movie, Movie_DTO.class);
            dto.setImageurl(null);
            result.add(dto);
        }
        return result;
    }

    @Override
    public List<Movie_DTO> getByTime_add(Date time_add, Pageable pageable) {
        List<Movie_DTO> result = new ArrayList<>();
        List<Movie_Entity> movie_entity = movieRepository.findByTimeadd(time_add, pageable);
        if (movie_entity == null)
            throw new RuntimeException("Không có bộ phim có tên này");
        for (Movie_Entity movie : movie_entity) {
            Movie_DTO dto = modelMapper.map(movie, Movie_DTO.class);
            dto.setImageurl(null);
            result.add(dto);
        }
        return result;
    }

    @Override
    public List<Movie_DTO> getByTime_update(Date time_update, Pageable pageable) {
        List<Movie_DTO> result = new ArrayList<>();
        List<Movie_Entity> movie_entity = movieRepository.findByTimeupdate(time_update, pageable);
        if (movie_entity == null)
            throw new RuntimeException("Không có bộ phim có tên này");
        for (Movie_Entity movie : movie_entity) {
            Movie_DTO dto = modelMapper.map(movie, Movie_DTO.class);
            result.add(dto);
        }
        return result;
    }

    @Override
    public Movie_DTO getById(Long id) {
       try {
           Movie_Entity movie_entity = movieRepository.findById(id).orElseThrow(() -> new RuntimeException("Không có bộ phim này"));
           Movie_DTO dto = modelMapper.map(movie_entity, Movie_DTO.class);
           return dto;

       }catch (Exception e) {
           throw new RuntimeException("Không tìm thấy bộ phim");
       }
    }

    @Override
    public Movie_DTO getByMovieId(Long id) {
        try {
            Movie_Entity movie_entity = movieRepository.findById(id).orElseThrow(() -> new RuntimeException("Không có bộ phim này"));
            List<Episode_enitty> episode_enitty = episodeRepository.findByMovie(movie_entity);
            if (episode_enitty == null)
                movie_entity.setTotalviews(0L);
            else{

                long totalviews = 0;
                for (Episode_enitty episode : episode_enitty) {
                    totalviews += episode.getViews();
                }
                if(totalviews == movie_entity.getTotalviews()){
                    movie_entity.setTotalviews(totalviews);
                    movieRepository.save(movie_entity);
                }
            }

            Movie_DTO dto = modelMapper.map(movie_entity, Movie_DTO.class);
            dto.setImageurl(null);
            return dto;

        }catch (Exception e) {
            throw new RuntimeException("Không tìm thấy bộ phim");
        }
    }

    @Override
    public Movie_DTO create(Movie_DTO movie_DTO, MultipartFile file, String categorylist, String scheduleList) throws IOException {
        try {
            if (movie_DTO == null) {
                throw new RuntimeException("Bạn chưa nhập dữ liệu!");
            }

            if (file == null) {
                throw new RuntimeException("Vui lòng chọn ảnh của bộ phim!");
            }

            if (categorylist == null || categorylist.isEmpty()) {
                throw new RuntimeException("Vui lòng chọn thể loại của bộ phim!");
            }

            // Xử lý danh sách thể loại
            List<String> categoryIds = Arrays.asList(categorylist.split(","));
            List<Category_Entity> categoryEntities = new ArrayList<>();

            for (String idStr : categoryIds) {
                Long id = Long.valueOf(idStr.trim()); // Chuyển đổi và loại bỏ khoảng trắng
                Category_Entity category = new Category_Entity();
                category.setId(id);
                categoryEntities.add(category);
            }
            // Xử lý danh sách thể loại
            List<String> scheduleIds = Arrays.asList(scheduleList.split(","));
            List<Schedule_Entity> scheduleEntities = new ArrayList<>();

            for (String idStr : scheduleIds) {
                Long id = Long.valueOf(idStr.trim()); // Chuyển đổi và loại bỏ khoảng trắng
                Schedule_Entity schedule = new Schedule_Entity();
                schedule.setId(id);
                scheduleEntities.add(schedule);
            }

            // Tạo đối tượng Movie_Entity
            movie_DTO.setNewmovie(true); // Bạn có thể điều chỉnh điều này theo nhu cầu thực tế
            Movie_Entity movieEntity = modelMapper.map(movie_DTO, Movie_Entity.class);
            Date currentDate = Date.valueOf(LocalDate.now());
            User_Entity userEntity = userRepository.findById(movieEntity.getUseradd().getId())
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng"));

            movieEntity.setUseradd(userEntity);
            movieEntity.setUserupdate(userEntity);
            movieEntity.setTimeupdate(currentDate);
            movieEntity.setImageurl(file.getBytes());
            movieEntity.setTimeadd(currentDate);
            movieEntity = movieRepository.save(movieEntity);

            // Xử lý các thể loại liên quan
            for (Category_Entity category : categoryEntities) {
                Category_Entity categoryItem = categoryRepository.findById(category.getId())
                        .orElseThrow(() -> new RuntimeException("Không tìm thấy thể loại"));
                Category_Movie_Entity categoryMovieEntity = new Category_Movie_Entity();
                categoryMovieEntity.setCategory(categoryItem);
                categoryMovieEntity.setMovie(movieEntity);
                categoryMovieRepository.save(categoryMovieEntity);
            }
            // Xử lý thêm lịch đăng liên quan
            for (Schedule_Entity schedule : scheduleEntities) {
                Schedule_Entity scheduleitem = scheduleRepository.findById(schedule.getId())
                        .orElseThrow(() -> new RuntimeException("Không tìm thấy thể loại"));
                Schedule_Movie_Entity scheduleMovieEntity = new Schedule_Movie_Entity();
                scheduleMovieEntity.setSchedule(scheduleitem);
                scheduleMovieEntity.setMovie(movieEntity);
                scheduleMovieRepository.save(scheduleMovieEntity);
            }
            // Chuyển đổi entity sang DTO
            return modelMapper.map(movieEntity, Movie_DTO.class);

        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi tạo phim: " + e.getMessage(), e);
        }
    }

    @Override
    public Movie_DTO update(Movie_DTO movie_DTO, MultipartFile file, String categorylist, String scheduleList) throws IOException{
        try {
                if (movie_DTO == null)
                    throw new RuntimeException("Bạn chưa nhập dữ liệu!");
                Movie_Entity movie_entity = movieRepository.findById(movie_DTO.getId()).orElseThrow(() -> new RuntimeException("Không tìm thấy bộ phim"));
               User_Entity user_entity = userRepository.findById(movie_DTO.getUserupdate().getId()).orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng"));
                if (file == null)
                    movie_DTO.setImageurl(movie_entity.getImageurl());

                else
                    movie_DTO.setImageurl(file.getBytes());
                if (categorylist == null)
                    throw new RuntimeException("Vui là chọn thể loại của bộ phim!");
            List<String> categoryIds = Arrays.asList(categorylist.split(","));
            List<Category_Entity> list = new ArrayList<>();

            for (String idStr : categoryIds) {
                Long id = Long.valueOf(idStr.trim()); // Chuyển đổi và loại bỏ khoảng trắng
                Category_Entity category = new Category_Entity();
                category.setId(id);
                list.add(category);
            }
            List<String> scheduleIds = Arrays.asList(scheduleList.split(","));
            List<Schedule_Entity> scheduleEntities = new ArrayList<>();

            for (String idStr : scheduleIds) {
                Long id = Long.valueOf(idStr.trim()); // Chuyển đổi và loại bỏ khoảng trắng
                Schedule_Entity schedule = new Schedule_Entity();
                schedule.setId(id);
                scheduleEntities.add(schedule);
            }
            movie_entity.setId(movie_DTO.getId());
            movie_entity.setVnname(movie_DTO.getVnname());
            movie_entity.setCnname(movie_DTO.getCnname());
            movie_entity.setImageurl(movie_DTO.getImageurl());
            movie_entity.setTimeadd(movie_entity.getTimeadd());
            movie_entity.setTimeupdate(Date.valueOf(LocalDate.now()));
            movie_entity.setUseradd(movie_entity.getUseradd());
            movie_entity.setUserupdate(user_entity);
            movie_entity.setNewmovie(movie_DTO.getNewmovie());
            movie_entity.setPrice(movie_DTO.getPrice());
            movie_entity.setAuthor(movie_DTO.getAuthor());
             movie_entity.setHotmovie(movie_DTO.getHotmovie());
             movie_entity.setVipmovie(movie_DTO.getVipmovie());
             movie_entity.setYear(movie_DTO.getYear());
            movie_entity.setEpisodenumber(movie_DTO.getEpisodenumber());
                Movie_Entity movie_entity_update = movieRepository.save(movie_entity);
            List<Category_Movie_Entity>   category_movie_entity = categoryMovieRepository.findByMovie(movie_entity);
            for (Category_Movie_Entity category_movie_entity1 : category_movie_entity) {
                categoryMovieRepository.delete(category_movie_entity1);
            }
            for (Category_Entity category : list) {
                Category_Entity categoryitem = categoryRepository.findById(category.getId()).orElseThrow(() -> new RuntimeException("Không tìm thấy thể loại"));
                Category_Movie_Entity category_movie_entityupdate = new Category_Movie_Entity();
                category_movie_entityupdate.setCategory(categoryitem);
                category_movie_entityupdate.setMovie(movie_entity_update);
                categoryMovieRepository.save(category_movie_entityupdate);
            }
            List<Schedule_Movie_Entity> schedule_movie_entity = scheduleMovieRepository.findByMovie(movie_entity);
            for (Schedule_Movie_Entity item : schedule_movie_entity) {
                scheduleMovieRepository.delete(item);
            }
            for (Schedule_Entity  item : scheduleEntities) {
                Schedule_Entity schedule = scheduleRepository.findById(item.getId()).orElseThrow(() -> new RuntimeException("Không tìm thấy lịch chiếu"));
                Schedule_Movie_Entity scheduleMovieEntity = new Schedule_Movie_Entity();
                scheduleMovieEntity.setSchedule(schedule);
                scheduleMovieEntity.setMovie(movie_entity_update);
                scheduleMovieRepository.save(scheduleMovieEntity);
            }
            Movie_DTO movie_dto = modelMapper.map(movie_entity_update, Movie_DTO.class);
            return movie_dto;

        }catch (Exception e) {
            throw new RuntimeException("Có lỗi khi chỉnh sửa bộ phim!");
        }

    }

    @Override
    public void delete(Long id) {
    try {
        Movie_Entity movie_entity = movieRepository.findById(id).orElseThrow(() -> new RuntimeException("Không tìm thấy bộ phim"));
        List<Category_Movie_Entity> category_movie_entity = categoryMovieRepository.findByMovie(movie_entity);
        for (Category_Movie_Entity category_movie_entity1 : category_movie_entity) {
            categoryMovieRepository.delete(category_movie_entity1);
        }
        movieRepository.delete(movie_entity);
    } catch (Exception e) {
        throw new RuntimeException("Có lỗi khi xóa bộ phim!");
    }
    }

    @Override
    public int totalItem() {
        return (int) movieRepository.count();
    }
}
