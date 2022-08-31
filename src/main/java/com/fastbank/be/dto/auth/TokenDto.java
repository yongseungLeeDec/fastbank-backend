package com.fastbank.be.dto.auth;

import lombok.Getter;

@Getter
public class TokenDto {

    private final String accessToken;
    private final String name;

    public TokenDto(String accessToken, String name) {
        this.accessToken = accessToken;
        this.name = name;
    }
}