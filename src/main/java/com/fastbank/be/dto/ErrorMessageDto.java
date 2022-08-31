package com.fastbank.be.dto;

import lombok.Getter;

@Getter
public class ErrorMessageDto {
    private final String errorMessage;

    public ErrorMessageDto(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}