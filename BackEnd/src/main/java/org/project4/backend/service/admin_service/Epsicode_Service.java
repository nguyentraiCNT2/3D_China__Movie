package org.project4.backend.service.admin_service;

import io.jsonwebtoken.io.IOException;
import org.project4.backend.dto.Episode_DTO;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface Epsicode_Service {
    List<Episode_DTO> getAll(Pageable pageable);
    List<Episode_DTO> getByMovie(Long movie_id, Pageable pageable);
    List<Episode_DTO> getByMovie(Long movie_id);
    Episode_DTO getById(Long id);
    Episode_DTO getByEpId(Long id);
    Episode_DTO create(Episode_DTO episode_DTO, MultipartFile file, MultipartFile subtitles) throws IOException;
    Episode_DTO update(Episode_DTO episode_DTO, MultipartFile file, MultipartFile subtitles) throws IOException;
    void delete(Long id);
    int totalItem();
}
