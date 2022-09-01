package com.fastbank.be.dto.signup;

import com.fastbank.be.dto.ResultDto;
import com.fasterxml.jackson.annotation.JsonFormat;

public class SignUpSuccessDto {

    @JsonFormat
    private String status;

    @JsonFormat
    private String email;

    public SignUpSuccessDto(String status, String email) {
        this.status = status;
        this.email = email;
    }
}
