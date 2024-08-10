package org.project4.backend.service.admin_service.impl;

import io.jsonwebtoken.io.IOException;
import org.modelmapper.ModelMapper;
import org.project4.backend.dto.Episode_DTO;
import org.project4.backend.dto.Movie_DTO;
import org.project4.backend.dto.User_DTO;
import org.project4.backend.entity.Episode_enitty;
import org.project4.backend.entity.Movie_Entity;
import org.project4.backend.entity.User_Entity;
import org.project4.backend.repository.admin_repository.Episode_Repository;
import org.project4.backend.repository.admin_repository.Movie_Repository;
import org.project4.backend.repository.admin_repository.User_Repository;
import org.project4.backend.service.admin_service.Epsicode_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class Episode_Service_IMPL implements Epsicode_Service {
    @Autowired
    private Episode_Repository episodeRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private Movie_Repository movieRepository;
    @Autowired
    private User_Repository userRepository;

    @Override
    public List<Episode_DTO> getAll(Pageable pageable) {
        List<Episode_DTO> result = new ArrayList<>();
        List<Episode_enitty> episode_entity = episodeRepository.findAll(pageable).getContent();
        if (episode_entity == null)
            throw new RuntimeException("Không có bộ phim nào");
        for (Episode_enitty episode : episode_entity) {
            Episode_DTO dto = modelMapper.map(episode, Episode_DTO.class);
            dto.setFileepisodes(null);
            result.add(dto);
        }
        return result;
    }

    @Override
    public List<Episode_DTO> getByMovie(Long movie_id, Pageable pageable) {
        List<Episode_DTO> result = new ArrayList<>();
        Movie_Entity movie = movieRepository.findById(movie_id).orElseThrow(() -> new RuntimeException("Không tìm thấy phim nào có id là " + movie_id));
        List<Episode_enitty> episode_entity = episodeRepository.findByMovie(movie, pageable);
        if (episode_entity == null)
            throw new RuntimeException("Không có bộ phim nào");
        for (Episode_enitty episode : episode_entity) {
            Episode_DTO dto = new Episode_DTO();
            dto.setId(episode.getId());
            dto.setName(episode.getName());
            dto.setViews(episode.getViews());
            dto.setLikes(episode.getLikes());
            dto.setMovie(modelMapper.map(episode.getMovie(), Movie_DTO.class));
            dto.setUseradd(modelMapper.map(episode.getUseradd(), User_DTO.class));
            dto.setUserupdate(modelMapper.map(episode.getUserupdate(), User_DTO.class));
            dto.setTimeadd(episode.getTimeadd());
            dto.setTimeupdate(episode.getTimeupdate());
            dto.setDescription(episode.getDescription());
            dto.setFileepisodes(null);
            dto.setSubtitles(null);
            result.add(dto);
        }
        return result;
    }

    @Override
    public List<Episode_DTO> getByMovie(Long movie_id) {
        List<Episode_DTO> result = new ArrayList<>();
        Movie_Entity movie = movieRepository.findById(movie_id).orElseThrow(() -> new RuntimeException("Không tìm thấy phim nào có id là " + movie_id));
        List<Episode_enitty> episode_entity = episodeRepository.findByMovie(movie);
        Long totalviews = 0L;
        if (episode_entity == null)
            throw new RuntimeException("Không có bộ phim nào");
        for (Episode_enitty episode : episode_entity) {
            Episode_DTO dto = new Episode_DTO();
            dto.setId(episode.getId());
            dto.setName(episode.getName());
            dto.setViews(episode.getViews());
            dto.setLikes(episode.getLikes());
            dto.setMovie(modelMapper.map(episode.getMovie(), Movie_DTO.class));
            dto.setUseradd(modelMapper.map(episode.getUseradd(), User_DTO.class));
            dto.setUserupdate(modelMapper.map(episode.getUserupdate(), User_DTO.class));
            dto.setTimeadd(episode.getTimeadd());
            dto.setTimeupdate(episode.getTimeupdate());
            dto.setFileepisodes(null);
            dto.setSubtitles(null);
             totalviews = episode.getViews() + totalviews;
            result.add(dto);
        }
        if (totalviews != movie.getTotalviews()){
            movie.setTotalviews(totalviews);
            movieRepository.save(movie);
        }
        return result;
    }

    @Override
    public Episode_DTO getById(Long id) {
        try {
            Episode_enitty episode = episodeRepository.findById(id).orElseThrow(() -> new RuntimeException("Không có bộ phim nào có id là: " + id));
           Movie_DTO movie = modelMapper.map(episode.getMovie(), Movie_DTO.class);
           movie.setImageurl(null);
            Episode_DTO dto = new Episode_DTO();
            dto.setId(episode.getId());
            dto.setName(episode.getName());
            dto.setViews(episode.getViews());
            dto.setLikes(episode.getLikes());
            dto.setMovie(movie);
            dto.setUseradd(modelMapper.map(episode.getUseradd(), User_DTO.class));
            dto.setUserupdate(modelMapper.map(episode.getUserupdate(), User_DTO.class));
            dto.setDescription(episode.getDescription());
            dto.setTimeadd(episode.getTimeadd());
            dto.setTimeupdate(episode.getTimeupdate());
            dto.setFileepisodes(episode.getFileepisodes());
            dto.setSubtitles(episode.getSubtitles());
            dto.setViews(episode.getViews());

            return dto;
        } catch (Exception e) {
            throw new RuntimeException("Không có bộ phịm nào");
        }
    }

    @Override
    public Episode_DTO getByEpId(Long id) {
        try {
            Episode_enitty episode = episodeRepository.findById(id).orElseThrow(() -> new RuntimeException("Không có bộ phim nào có id là: " + id));
            Movie_DTO movie = modelMapper.map(episode.getMovie(), Movie_DTO.class);
            movie.setImageurl(null);
            episode.setViews(episode.getViews() + 1);
            Episode_DTO dto = new Episode_DTO();
            dto.setId(episode.getId());
            dto.setName(episode.getName());
            dto.setViews(episode.getViews());
            dto.setLikes(episode.getLikes());
            dto.setMovie(movie);
            dto.setUseradd(modelMapper.map(episode.getUseradd(), User_DTO.class));
            dto.setUserupdate(modelMapper.map(episode.getUserupdate(), User_DTO.class));
            dto.setTimeadd(episode.getTimeadd());
            dto.setTimeupdate(episode.getTimeupdate());
            dto.setDescription(episode.getDescription());
            dto.setFileepisodes(null);
            dto.setSubtitles(null);
            episodeRepository.save(episode);
            return dto;
        } catch (Exception e) {
            throw new RuntimeException("Không có bộ phịm nào");
        }
    }

    @Override
    public Episode_DTO create(Episode_DTO episode_DTO, MultipartFile file, MultipartFile subtitles) throws IOException {
        try {
            if (episode_DTO == null)
                throw new RuntimeException("Bạn chưa nhập dữ liệu tập phim");
            if (episode_DTO.getName() == null || episode_DTO.getName().equals(""))
                throw new RuntimeException("Vui lòng nhập tên tập phim");
            if (file == null)
                throw new RuntimeException("Vui lòng chọn video của tập phim");
//            if (subtitles == null)
//                throw new RuntimeException("Vui lòng chọn file sub của tập phim");
            if (episode_DTO.getViews() == null)
                episode_DTO.setViews(0L);
            if (episode_DTO.getLikes() == null)
                episode_DTO.setLikes(0L);
            Movie_Entity movie = movieRepository.findById(episode_DTO.getMovie().getId()).orElseThrow(() -> new RuntimeException("Không tìm thấy phim nào có id là " + episode_DTO.getMovie().getId()));
            User_Entity useradd = userRepository.findById(episode_DTO.getUseradd().getId()).orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng nào có id là " + episode_DTO.getUseradd().getId()));
            User_Entity userupdate = userRepository.findById(episode_DTO.getUserupdate().getId()).orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng nào có id là " + episode_DTO.getUserupdate().getId()));
            Episode_enitty episode = modelMapper.map(episode_DTO, Episode_enitty.class);
            episode.setMovie(movie);
            episode.setUseradd(useradd);
            episode.setUserupdate(userupdate);
            episode.setTimeadd(Date.valueOf(LocalDate.now()));
            episode.setTimeupdate(Date.valueOf(LocalDate.now()));
            episode.setFileepisodes(file.getBytes());
            if (subtitles != null)
            episode.setSubtitles(subtitles.getBytes());
            else
            episode.setSubtitles(null);
            episodeRepository.save(episode);
            Episode_DTO result = modelMapper.map(episode, Episode_DTO.class);
            return result;
        } catch (Exception e) {
            throw new RuntimeException("Không có bộ phim nào");
        }
    }

    @Override
    public Episode_DTO update(Episode_DTO episode_DTO, MultipartFile file, MultipartFile subtitles) throws IOException {
        try {
            if (episode_DTO == null)
                throw new RuntimeException("Bạn chưa nhập dữ liệu tập phim");
            if (episode_DTO.getName() == null || episode_DTO.getName().equals(""))
                throw new RuntimeException("Vui lòng nhập tên tập phim");

            if (episode_DTO.getViews() == null)
                episode_DTO.setViews(0L);
            if (episode_DTO.getLikes() == null)
                episode_DTO.setLikes(0L);
            Episode_enitty episode = episodeRepository.findById(episode_DTO.getId()).orElseThrow(() -> new RuntimeException("Không tìm thấy tập phim nào có id là " + episode_DTO.getId()));

            User_Entity userupdate = userRepository.findById(episode_DTO.getUserupdate().getId()).orElseThrow(() -> new RuntimeException("Không tìm thấy người dùng nào có id là " + episode_DTO.getUserupdate().getId()));
            episode.setName(episode_DTO.getName());
            episode.setViews(episode_DTO.getViews());
            episode.setLikes(episode_DTO.getLikes());
            episode.setMovie(episode.getMovie());
            episode.setUseradd(episode.getUseradd());
            episode.setUserupdate(userupdate);
            episode.setTimeadd(episode.getTimeadd());
            episode.setTimeupdate(Date.valueOf(LocalDate.now()));
            if (file == null)
                episode.setFileepisodes(episode.getFileepisodes());
            else
            episode.setFileepisodes(file.getBytes());
            if (subtitles == null)
                episode.setSubtitles(episode.getSubtitles());
            else
                episode.setSubtitles(subtitles.getBytes());
            episodeRepository.save(episode);
            Episode_DTO result = modelMapper.map(episode, Episode_DTO.class);
            return result;
        }catch (Exception e) {
            throw new RuntimeException("Không có bộ phim nào");
        }
    }

    @Override
    public void delete(Long id) {
        try {
            Episode_enitty episode = episodeRepository.findById(id).orElseThrow(() -> new RuntimeException("Không tìm thấy tập phim nào có id là " + id));
            episodeRepository.delete(episode);
        } catch (Exception e) {
            throw new RuntimeException("Có lỗi khi xóa tập phim");
        }
    }

    @Override
    public int totalItem() {
        return (int) episodeRepository.count();
    }
}
