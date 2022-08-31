package com.fastbank.be.dto;

import com.fastbank.be.domain.auth.Member;
import com.fasterxml.jackson.annotation.JsonFormat;

public class ResultSuccessDto extends ResultDto {
    @JsonFormat
    private Object dtoObject;

    public ResultSuccessDto(String statusCode, Object dtoObject) {
        super(statusCode);
        this.dtoObject = dtoObject;
    }
}