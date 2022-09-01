package com.fastbank.be.persistence;

import com.fastbank.be.domain.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findAllByEmail(String authorization);

    @Transactional
    void deleteByIdAndEmail(Long productIds, String memberEmail);

    Optional<CartItem> findByIdAndEmail(Long productId, String memberEmail);
}