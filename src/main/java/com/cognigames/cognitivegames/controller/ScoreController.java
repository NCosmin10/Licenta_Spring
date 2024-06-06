package com.cognigames.cognitivegames.controller;

import com.cognigames.cognitivegames.dto.ScoreDTO;
import com.cognigames.cognitivegames.service.ScoreService;
import com.cognigames.cognitivegames.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
public class ScoreController {

    @Autowired
    private ScoreService scoreService;
    @Autowired
    private UserService userService;

    @PostMapping("/scoreSave")
    public ResponseEntity<?> saveScore(@RequestBody ScoreDTO scoreDTO) {
        scoreDTO.setDate(Date.from(new Date().toInstant()));
        scoreDTO.setUserId(userService.getIdByUsername(scoreDTO.getUsername()));
        //System.out.println("Score: " + scoreDTO.getScore()+ " User: " + scoreDTO.getUsername() + " UserID: "+scoreDTO.getUserId()+"Game:" + scoreDTO.getGameId() + " Date: " + scoreDTO.getDate());
        scoreService.saveScore(scoreDTO);
        return ResponseEntity.ok("Score saved successfully");
    }
}