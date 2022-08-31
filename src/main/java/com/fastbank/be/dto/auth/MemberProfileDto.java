package com.fastbank.be.dto.auth;

import com.fastbank.be.domain.auth.Member;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberProfileDto {
    private String statusCode;

    @JsonFormat
    private Member memberProfile;
}