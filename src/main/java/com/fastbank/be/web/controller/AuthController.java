package com.fastbank.be.web.controller;

import com.fastbank.be.domain.auth.Authority;
import com.fastbank.be.dto.ErrorMessageDto;
import com.fastbank.be.dto.auth.LoginDto;
import com.fastbank.be.dto.auth.TokenDto;
import com.fastbank.be.dto.auth.MemberIdentityDto;
import com.fastbank.be.jwt.TokenProvider;
import com.fastbank.be.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class AuthController {

    private static final String loginFailureMessage = "아이디가 존재하지 않거나 올바른 비밀번호가 아닙니다.";

    private final AuthService authService;
    private final TokenProvider tokenProvider;

    public AuthController(AuthService authService, TokenProvider tokenProvider) {
        this.authService = authService;
        this.tokenProvider = tokenProvider;
    }

    @PostMapping("/login")
    public ResponseEntity<Object> authenticateMember(@Valid @RequestBody LoginDto loginDto) {
        MemberIdentityDto memberIdentity = authService.authenticateMember(loginDto);

        if (memberIdentity.getAuthority() == Authority.UNAUTHORIZED) {
            return new ResponseEntity<>(new ErrorMessageDto(loginFailureMessage), HttpStatus.UNAUTHORIZED);
        }

        String jwt = tokenProvider.issueToken(memberIdentity);

        return new ResponseEntity<>(new TokenDto(jwt, memberIdentity.getName()), HttpStatus.OK);
    }
}