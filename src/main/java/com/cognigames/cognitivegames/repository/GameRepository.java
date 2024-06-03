package com.cognigames.cognitivegames.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cognigames.cognitivegames.model.GameEntity;

@Repository
public interface GameRepository extends JpaRepository<GameEntity, Long> {
}
