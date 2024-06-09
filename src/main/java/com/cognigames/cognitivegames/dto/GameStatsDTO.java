package com.cognigames.cognitivegames.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;

import java.util.List;


@Getter @Setter
@Data
public class GameStatsDTO {
    private String gameName;
    private Long personalAvgScore;
    private int personalBestScore;
    private Long communityAvgScore;
    private List<Date> gameDates;
    private List<Integer> gameScores;

    public GameStatsDTO() {
    }

    public GameStatsDTO(String gameName, Long personalAvgScore, int personalBestScore, Long communityAvgScore) {
        this.gameName = gameName;
        this.personalAvgScore = personalAvgScore;
        this.personalBestScore = personalBestScore;
        this.communityAvgScore = communityAvgScore;
        gameDates= new ArrayList<>();
        gameScores= new ArrayList<>();
    }
}
