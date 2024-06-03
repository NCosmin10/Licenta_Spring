package com.cognigames.cognitivegames.service;

import com.cognigames.cognitivegames.dto.GameDTO;
import com.cognigames.cognitivegames.model.GameEntity;
import com.cognigames.cognitivegames.repository.GameRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GameService {
    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private ModelMapper modelMapper;

    public GameDTO saveGame(GameDTO gameDTO) {
        GameEntity game = modelMapper.map(gameDTO, GameEntity.class);
        game = gameRepository.save(game);
        return modelMapper.map(game, GameDTO.class);
    }

    public List<GameDTO> getAllGames() {
        return gameRepository.findAll().stream()
                .map(game -> modelMapper.map(game, GameDTO.class))
                .collect(Collectors.toList());
    }

    public GameDTO getGameById(Long id) {
        GameEntity game = gameRepository.findById(id).orElse(null);
        return modelMapper.map(game, GameDTO.class);
    }

    public void deleteGame(Long id) {
        gameRepository.deleteById(id);
    }
}
