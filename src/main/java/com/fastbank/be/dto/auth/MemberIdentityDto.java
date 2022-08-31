package com.fastbank.be.dto.auth;

import com.fastbank.be.domain.auth.Authority;
import lombok.Getter;

@Getter
public class MemberIdentityDto {

    private Authority authority;
    private String email;
    private String name;

    public MemberIdentityDto(Authority authority) {
        this.authority = authority;
    }

    public MemberIdentityDto(Authority authority, String email, String name) {
        this.authority = authority;
        this.email = email;
        this.name = name;
    }
}