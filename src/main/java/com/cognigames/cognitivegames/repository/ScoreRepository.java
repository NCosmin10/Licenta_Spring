package com.cognigames.cognitivegames.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cognigames.cognitivegames.model.ScoreEntity;

@Repository
public interface ScoreRepository extends JpaRepository<ScoreEntity, Long> {
}
