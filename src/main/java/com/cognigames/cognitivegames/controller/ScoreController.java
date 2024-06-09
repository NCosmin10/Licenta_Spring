package com.cognigames.cognitivegames.controller;

import com.cognigames.cognitivegames.dto.GameDTO;
import com.cognigames.cognitivegames.dto.GameStatsDTO;
import com.cognigames.cognitivegames.dto.ScoreDTO;
import com.cognigames.cognitivegames.dto.UserDTO;
import com.cognigames.cognitivegames.service.GameService;
import com.cognigames.cognitivegames.service.ScoreService;
import com.cognigames.cognitivegames.service.UserService;
import jakarta.persistence.Tuple;
import org.modelmapper.internal.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class ScoreController {

    @Autowired
    private ScoreService scoreService;
    @Autowired
    private UserService userService;
    @Autowired
    private GameService gameService;

    @PostMapping("/score/save")
    public ResponseEntity<?> saveScore(@RequestBody ScoreDTO scoreDTO) {
        scoreDTO.setDate(Date.from(new Date().toInstant()));
        scoreDTO.setUserId(userService.getIdByUsername(scoreDTO.getUsername()));
        //System.out.println("Score: " + scoreDTO.getScore()+ " User: " + scoreDTO.getUsername() + " UserID: "+scoreDTO.getUserId()+"Game:" + scoreDTO.getGameId() + " Date: " + scoreDTO.getDate());
        scoreService.saveScore(scoreDTO);
        return ResponseEntity.ok("Score saved successfully");
    }

    private List<GameStatsDTO> getAveragesAndDates(String username){
        UserDTO currentUser = userService.getUserByUsername(username);

        List<ScoreDTO> allScores = scoreService.getAllScores();
        List<ScoreDTO> userScores = allScores.stream().filter(score -> score.getUserId().equals(currentUser.getId())).toList();
        List<GameStatsDTO> allStats = new ArrayList<>();

        List<GameDTO> games = gameService.getAllGames();
        Integer numberOfGames = games.size();

        for (int i = 1; i <= numberOfGames; i++) {
            int finalI = i;

            int numberOfScores = userScores.stream().filter(score -> score.getGameId() == finalI).toList().size();
            int numberOfScores2 = allScores.stream().filter(score -> score.getGameId() == finalI).toList().size();

            GameStatsDTO gameStatsDTO = new GameStatsDTO("Game ", 0L, 0, 0L);
            if(i == 1){
                gameStatsDTO.setPersonalBestScore(Integer.MAX_VALUE);
            }
            List<ScoreDTO> gameScores = userScores.stream().filter(score -> score.getGameId() == finalI).toList();

            gameStatsDTO.setGameName(games.get(i - 1).getGameName());

            for (ScoreDTO score : gameScores) {
                gameStatsDTO.setPersonalAvgScore(gameStatsDTO.getPersonalAvgScore() + score.getScore());
                if(i!=1)
                {
                    if (score.getScore() > gameStatsDTO.getPersonalBestScore()) {
                        gameStatsDTO.setPersonalBestScore(score.getScore());
                    }
                }else {
                    if (score.getScore() < gameStatsDTO.getPersonalBestScore()) {
                        gameStatsDTO.setPersonalBestScore(score.getScore());
                    }
                }
                gameStatsDTO.getGameDates().add(score.getDate());
                gameStatsDTO.getGameScores().add(score.getScore());
            }
            if(numberOfScores != 0)
                gameStatsDTO.setPersonalAvgScore(gameStatsDTO.getPersonalAvgScore() / numberOfScores);

            for (ScoreDTO score : allScores) {
                if (score.getGameId() == i) {
                    gameStatsDTO.setCommunityAvgScore(gameStatsDTO.getCommunityAvgScore() + score.getScore());
                }
            }
            if(numberOfScores2 != 0)
                gameStatsDTO.setCommunityAvgScore(gameStatsDTO.getCommunityAvgScore() / numberOfScores2);
            allStats.add(gameStatsDTO);
        }
        return allStats;
    }
    @GetMapping("/score/{username}")
    public ResponseEntity<?> getAllScores(@PathVariable String username) {
        return ResponseEntity.ok(getAveragesAndDates(username));
    }
    @GetMapping("/score/{username}/{gameId}")
    public ResponseEntity<?> getScoresByGame(@PathVariable String username, @PathVariable Integer gameId) {
        return ResponseEntity.ok(getAveragesAndDates(username).get(gameId - 1));
    }

}