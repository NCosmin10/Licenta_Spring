package com.cognigames.cognitivegames.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter @Setter
@Data
public class ScoreDTO {
    private Long id;
    private Long userId;
    private Long gameId;
    private int score;
    private Date date;
}
