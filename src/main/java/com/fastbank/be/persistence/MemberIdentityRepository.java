package com.fastbank.be.persistence;

import com.fastbank.be.domain.auth.MemberIdentity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberIdentityRepository extends JpaRepository<MemberIdentity, Long> {
    Optional<MemberIdentity> findOneByEmail(String email);
}