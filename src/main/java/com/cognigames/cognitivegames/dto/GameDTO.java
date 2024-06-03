package com.cognigames.cognitivegames.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Data
public class GameDTO {
    private Long id;
    private String gameName;
    private String gameDescription;
}
