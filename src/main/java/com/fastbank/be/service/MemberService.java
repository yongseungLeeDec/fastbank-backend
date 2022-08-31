package com.fastbank.be.service;

import com.fastbank.be.domain.auth.Member;
import com.fastbank.be.domain.auth.MemberIdentity;
import com.fastbank.be.domain.auth.Authority;
import com.fastbank.be.dto.signup.MemberDto;
import com.fastbank.be.encryptor.Encryptor;
import com.fastbank.be.persistence.MemberIdentityRepository;
import com.fastbank.be.persistence.MemberRepository;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberIdentityRepository memberIdentityRepository;

    public MemberService(MemberRepository memberRepository, MemberIdentityRepository memberIdentityRepository) {
        this.memberRepository = memberRepository;
        this.memberIdentityRepository = memberIdentityRepository;
    }

    public MemberDto signup(MemberDto memberDto) {

        if (memberRepository.findOneByEmail(memberDto.getEmail()).isPresent()) {
            return new MemberDto();
        }

        Member newMember = Member.builder()
                .email(memberDto.getEmail())
                .name(memberDto.getName())
                .job(memberDto.getJob())
                .age(memberDto.getAgeGroup()).build();

        newMember = memberRepository.save(newMember);

        MemberIdentity newMemberIdentity = MemberIdentity.builder()
                .email(memberDto.getEmail())
                .password(new String(Encryptor.hashPassword(memberDto.getPassword()), StandardCharsets.UTF_8))
                .name(memberDto.getName())
                .authority(Authority.MEMBER).build();

        memberIdentityRepository.save(newMemberIdentity);

        return new MemberDto(newMember.getEmail());
    }
}