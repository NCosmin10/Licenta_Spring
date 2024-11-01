package com.cognigames.cognitivegames.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Data
public class UserDTO {
    private Long id;
    private String username;
    private String email;
}
