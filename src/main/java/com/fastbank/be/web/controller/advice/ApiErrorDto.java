package com.fastbank.be.web.controller.advice;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat
public class ApiErrorDto {
    @JsonFormat
    private String status;
    @JsonFormat
    private String error;

    public ApiErrorDto(String status, String error) {
        this.status = status;
        this.error = error;
    }
}
