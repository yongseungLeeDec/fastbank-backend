package com.fastbank.be.web.controller;

import com.fastbank.be.domain.auth.Member;
import com.fastbank.be.dto.ResultDto;
import com.fastbank.be.dto.auth.MemberProfileDto;
import com.fastbank.be.dto.signup.EmailDto;
import com.fastbank.be.dto.signup.IdCheckerDto;
import com.fastbank.be.dto.signup.MemberDto;
import com.fastbank.be.dto.ResultSuccessDto;
import com.fastbank.be.dto.ResultFailureDto;
import com.fastbank.be.dto.signup.SignUpSuccessDto;
import com.fastbank.be.jwt.TokenProvider;
import com.fastbank.be.persistence.MemberRepository;
import com.fastbank.be.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Optional;

@RestController
public class MemberController {

    private static final String invalidSignUpFailureMessage = "기입된 회원 가입 정보가 올바르지 않습니다.";
    private static final String memberProfileNotFoundMessage = "회원 프로필을 조회할 수 없습니다.";

    private final MemberService memberService;
    private final MemberRepository memberRepository;
    private final TokenProvider tokenProvider;

    public MemberController(MemberService memberService, MemberRepository memberRepository, TokenProvider tokenProvider) {
        this.memberService = memberService;
        this.memberRepository = memberRepository;
        this.tokenProvider = tokenProvider;
    }

    @PostMapping("/signup/check")
    public ResponseEntity<IdCheckerDto> checkIdAvailability(@Valid @RequestBody EmailDto emailDto) {
        String email = emailDto.getEmail();
        Optional<Member> result = memberRepository.findOneByEmail(email);

        if (result.isPresent()) {
            return new ResponseEntity<>(new IdCheckerDto("false"), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new IdCheckerDto("true"), HttpStatus.OK);
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<Object> signUp(@Valid @RequestBody MemberDto memberDto) {
        MemberDto registeredMemberInfo = memberService.signup(memberDto);

        if (registeredMemberInfo == null) {
            return new ResponseEntity<>(new ResultFailureDto("401", invalidSignUpFailureMessage), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(new SignUpSuccessDto("200", registeredMemberInfo.getEmail()), HttpStatus.OK);
    }

    @GetMapping("/auth")
    public ResponseEntity<Object> getMemberProfile(HttpServletRequest request) {
        String memberEmail = tokenProvider.getSubject(request);

        Optional<Member> member = memberRepository.findOneByEmail(memberEmail);

        if (member.isEmpty()) {
            return new ResponseEntity<>(new ResultFailureDto("401", memberProfileNotFoundMessage), HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity<>(new MemberProfileDto("200", member.get()), HttpStatus.OK);
    }
}