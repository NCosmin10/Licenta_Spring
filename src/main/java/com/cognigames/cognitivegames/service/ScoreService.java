package com.cognigames.cognitivegames.service;

import com.cognigames.cognitivegames.config.util.JwtTokenUtil;
import com.cognigames.cognitivegames.dto.ScoreDTO;
import com.cognigames.cognitivegames.model.ScoreEntity;
import com.cognigames.cognitivegames.repository.ScoreRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScoreService {
    @Autowired
    private ScoreRepository scoreRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UserService userService;
    @Autowired
    private GameService gameService;

    public ScoreDTO saveScore(ScoreDTO scoreDTO) {
        //ScoreEntity score = modelMapper.map(scoreDTO, ScoreEntity.class);
        ScoreEntity score = new ScoreEntity();
        score.setScore(scoreDTO.getScore());
        score.setUser(userService.getUserById2(scoreDTO.getUserId()));
        score.setGame(gameService.getGameById2(scoreDTO.getGameId()));
        score.setDate(scoreDTO.getDate());
        score = scoreRepository.save(score);
        return modelMapper.map(score, ScoreDTO.class);
    }

    public List<ScoreDTO> getAllScores() {
        return scoreRepository.findAll().stream()
                .map(score -> modelMapper.map(score, ScoreDTO.class))
                .collect(Collectors.toList());
    }

    public ScoreDTO getScoreById(Long id) {
        ScoreEntity score = scoreRepository.findById(id).orElse(null);
        return modelMapper.map(score, ScoreDTO.class);
    }

    public void deleteScore(Long id) {
        scoreRepository.deleteById(id);
    }
}
