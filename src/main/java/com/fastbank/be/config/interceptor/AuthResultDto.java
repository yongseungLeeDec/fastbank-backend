package com.fastbank.be.config.interceptor;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

@Getter
public class AuthResultDto {

    @JsonFormat
    private String statusCode;
    @JsonFormat
    private String error;

    public AuthResultDto(String statusCode, String error) {
        this.statusCode = statusCode;
        this.error = error;
    }
}
