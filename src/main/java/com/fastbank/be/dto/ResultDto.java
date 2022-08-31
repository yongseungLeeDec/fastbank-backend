package com.fastbank.be.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ResultDto {
    @JsonFormat
    private String statusCode;

    public ResultDto(String statusCode) {
        this.statusCode = statusCode;
    }
}