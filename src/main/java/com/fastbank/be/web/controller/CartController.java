package com.fastbank.be.web.controller;

import com.fastbank.be.domain.CartItem;
import com.fastbank.be.domain.Product;
import com.fastbank.be.dto.cart.CartDto;
import com.fastbank.be.dto.cart.MemberCartDto;
import com.fastbank.be.jwt.TokenProvider;
import com.fastbank.be.persistence.CartItemRepository;
import com.fastbank.be.persistence.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@Slf4j
public class CartController {

    private final TokenProvider tokenProvider;
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;

    public CartController(TokenProvider tokenProvider, CartItemRepository cartItemRepository, ProductRepository productRepository) {
        this.tokenProvider = tokenProvider;
        this.cartItemRepository = cartItemRepository;
        this.productRepository = productRepository;
    }

    @GetMapping("/cart")
    public ResponseEntity<MemberCartDto> getMemberCart(HttpServletRequest request) {
        String memberEmail = tokenProvider.getSubject(request);
        List<CartItem> cartItems = cartItemRepository.findAllByEmail(memberEmail);

        return new ResponseEntity<>(new MemberCartDto("200", cartItems), HttpStatus.OK);
    }

    @PostMapping("/cart")
    public ResponseEntity<MemberCartDto> addItemToCart(HttpServletRequest request, @Valid @RequestBody CartDto cartDto) {
        Long[] productIds = cartDto.getCart();
        String memberEmail = tokenProvider.getSubject(request);
        List<CartItem> newlyAddedItems = new ArrayList<>();

        for (Long productId : productIds) {
            Optional<Product> result = productRepository.findById(productId);
            Product product = result.orElseThrow(NoSuchElementException::new);
            newlyAddedItems.add(new CartItem(memberEmail, product.getId(), product.getName(), product.getType()));
            log.info("memberEmail, product.getId(), product.getName(), product.getType() -> {}, {}, {}, {}", memberEmail, product.getId(), product.getName(), product.getType());

        }

        cartItemRepository.saveAll(newlyAddedItems);

        return new ResponseEntity<>(new MemberCartDto("200", cartItemRepository.findAllByEmail(memberEmail)), HttpStatus.OK);
    }

    @DeleteMapping("/cart")
    public ResponseEntity<MemberCartDto> removeItemFromCart(HttpServletRequest request, @RequestBody CartDto cartDto) {
        Long[] productIds = cartDto.getCart();
        String memberEmail = tokenProvider.getSubject(request);

        for (Long productId : productIds) {
            cartItemRepository.deleteByIdAndEmail(productId, memberEmail);
        }

        return new ResponseEntity<>(new MemberCartDto("200", cartItemRepository.findAllByEmail(memberEmail)), HttpStatus.OK);
    }
}