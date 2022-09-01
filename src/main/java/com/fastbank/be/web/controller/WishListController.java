package com.fastbank.be.web.controller;

import com.fastbank.be.domain.Product;
import com.fastbank.be.domain.WishList;
import com.fastbank.be.dto.product.MemberWishListDto;
import com.fastbank.be.dto.product.WishListDto;
import com.fastbank.be.jwt.TokenProvider;
import com.fastbank.be.persistence.ProductRepository;
import com.fastbank.be.persistence.WishListRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Slf4j
public class WishListController {
    private final TokenProvider tokenProvider;
    private final ProductRepository productRepository;
    private final WishListRepository wishListRepository;

    /**
     * 상품 찜하기
     */

    @PostMapping("/wishList")
    public ResponseEntity<MemberWishListDto> addWishList(HttpServletRequest httpServletRequest, @Valid @RequestBody WishListDto wishListDto) {
        Long[] productIds = wishListDto.getWishList();
        String memberEmail = tokenProvider.getSubject(httpServletRequest);
        List<WishList> addWishList = new ArrayList<>();

        for (Long productId : productIds) {
            if (isNotAlreadyLike(memberEmail, productId).isPresent()) {
                continue;
            }
            Optional<Product> result = productRepository.findById(productId);
            Product product = result.orElseThrow(NoSuchElementException::new);
            addWishList.add(new WishList(memberEmail, product.getId(), product.getName(), product.getType()));
        }
        wishListRepository.saveAll(addWishList);
        return new ResponseEntity<>(
                new MemberWishListDto("200", wishListRepository.findAllByEmail(memberEmail)), HttpStatus.OK);
    }

    /**
     * 상품 찜 목록
     * */
    @GetMapping("/wishList")
    public ResponseEntity<MemberWishListDto> getMemberWishList(HttpServletRequest httpServletRequest) {
        String memberEmail = tokenProvider.getSubject(httpServletRequest);
        List<WishList> wishLists = wishListRepository.findAllByEmail(memberEmail);

        return new ResponseEntity<>(new MemberWishListDto("200", wishLists), HttpStatus.OK);
    }


    /**
     * 상품 찜 해제
     * */
    @DeleteMapping("/wishList")
    public ResponseEntity<MemberWishListDto> removeWishList(HttpServletRequest httpServletRequest, @RequestBody WishListDto wishListDto) {
        Long[] productIds = wishListDto.getWishList();
        String memberEmail = tokenProvider.getSubject(httpServletRequest);

        for (Long productId : productIds) {
            wishListRepository.deleteByIdAndEmail(productId, memberEmail);
        }
        return new ResponseEntity<>(new MemberWishListDto("200", wishListRepository.findAllByEmail(memberEmail)), HttpStatus.OK);
    }

    /**
     * 찜 중복 체크
     * */
    private Optional<WishList> isNotAlreadyLike(String memberEmail, Long productId) {
        return wishListRepository.findByEmailAndId(memberEmail, productId);
    }

}