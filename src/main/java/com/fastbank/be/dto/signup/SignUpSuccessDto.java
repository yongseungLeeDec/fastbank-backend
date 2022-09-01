package com.fastbank.be.dto.signup;

import com.fastbank.be.dto.ResultDto;
import com.fasterxml.jackson.annotation.JsonFormat;

public class SignUpSuccessDto extends ResultDto {
    @JsonFormat
    private String email;

    public SignUpSuccessDto(String statusCode, String email) {
        super(statusCode);
        this.email = email;
    }
}
