package org.project4.backend.service.user_service;

import org.project4.backend.dto.Comment_Episode_DTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface Comment_Episode_Service {
List<Comment_Episode_DTO> getByEpisode(Long episodeid, Pageable pageable);

Comment_Episode_DTO save(Comment_Episode_DTO comment);
int totalItem();
}
