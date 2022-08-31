package com.fastbank.be.service;

import com.fastbank.be.domain.auth.MemberIdentity;
import com.fastbank.be.domain.auth.Authority;
import com.fastbank.be.dto.auth.LoginDto;
import com.fastbank.be.dto.auth.MemberIdentityDto;
import com.fastbank.be.encryptor.Encryptor;
import com.fastbank.be.persistence.MemberIdentityRepository;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Optional;

@Service
public class AuthService {
    private final MemberIdentityRepository memberIdentityRepository;

    public AuthService(MemberIdentityRepository memberIdentityRepository) {
        this.memberIdentityRepository = memberIdentityRepository;
    }

    public MemberIdentityDto authenticateMember(LoginDto loginDto) {
        Optional<MemberIdentity> searchResult = memberIdentityRepository.findOneByEmail(loginDto.getEmail());

        if (searchResult.isEmpty()) {
            return new MemberIdentityDto(Authority.UNAUTHORIZED);
        }

        MemberIdentity memberIdentity = searchResult.get();

        if (!checkPasswordValidity(loginDto.getPassword(), memberIdentity.getPassword())) {
            return new MemberIdentityDto(Authority.UNAUTHORIZED);
        }

        return new MemberIdentityDto(memberIdentity.getAuthority(), memberIdentity.getEmail(), memberIdentity.getName());
    }

    private boolean checkPasswordValidity(String given, String stored) {
        String hashValue = new String(Encryptor.hashPassword(given), StandardCharsets.UTF_8);
        return hashValue.equals(stored);
    }
}