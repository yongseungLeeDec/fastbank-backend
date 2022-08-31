package com.fastbank.be.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ResultFailureDto extends ResultDto {
    @JsonFormat
    private String errorMessage;

    public ResultFailureDto(String statusCode, String errorMessage) {
        super(statusCode);
        this.errorMessage = errorMessage;
    }
}