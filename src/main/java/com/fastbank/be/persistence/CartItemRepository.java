package com.fastbank.be.persistence;

import com.fastbank.be.domain.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findAllByEmail(String authorization);

    @Transactional
    void deleteByIdAndEmail(Long productIds, String memberEmail);
}