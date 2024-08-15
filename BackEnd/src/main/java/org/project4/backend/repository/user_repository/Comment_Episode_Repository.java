package org.project4.backend.repository.user_repository;

import org.project4.backend.entity.Comment_Episode_Entity;
import org.project4.backend.entity.Episode_enitty;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Comment_Episode_Repository extends JpaRepository<Comment_Episode_Entity, Long> {
    List<Comment_Episode_Entity> findByEpisode(Episode_enitty episodeId, Pageable pageable);
    List<Comment_Episode_Entity> findByEpisode(Episode_enitty episodeId);
}
