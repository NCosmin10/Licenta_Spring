package com.cognigames.cognitivegames.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class LoginResponse {
    private String token;
    private String type = "Bearer";
    private String username;

    public LoginResponse(String token, String username) {

        this.token = token;
        this.username = username;
    }

}
