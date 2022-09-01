package com.fastbank.be.persistence;

import com.fastbank.be.domain.WishList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface WishListRepository extends JpaRepository<WishList, Long> {


    List<WishList> findAllByEmail(String memberEmail);

    /**
     * 찜 해제
     * */
    @Transactional
    void deleteByIdAndEmail(Long productId, String memberEmail);

    /**
     * 찜 중복 체크
     * */
    Optional<WishList> findByEmailAndId(String memberEmail, Long productId);
}