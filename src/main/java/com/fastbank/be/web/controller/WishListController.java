package com.fastbank.be.web.controller;

import com.fastbank.be.domain.auth.Member;
import com.fastbank.be.domain.WishList;
import com.fastbank.be.dto.product.WishListDto;
import com.fastbank.be.jwt.TokenProvider;
import com.fastbank.be.persistence.ProductRepository;
import com.fastbank.be.persistence.WishListRepository;
import com.fastbank.be.service.WishListService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class WishListController {
    private final WishListService wishListService;
    private final TokenProvider tokenProvider;
    private final ProductRepository productRepository;
    private final WishListRepository wishListRepository;

    /**
     * 상품 찜하기
     */

/*
    @PostMapping("/wishList")
    public ResponseEntity<MemberWishListDto> addWishList(HttpServletRequest httpServletRequest, @Valid @RequestBody WishListDto wishList) {
        Long[] productIds = wishList.getWishList();
        String memberEmail = tokenProvider.getSubject(httpServletRequest);
        List<WishList> addWishList = new ArrayList<>();

        for (Long productId : productIds) {
            Optional<Product> result = productRepository.findById(productId);
            Product product = result.orElseThrow();
            addWishList.add(new WishList(memberEmail, product.getId(), product.getName(), product.getType()));
        }
        wishListRepository.saveAll(addWishList);
        return new ResponseEntity<>(
                new MemberWishListDto("200", wishListRepository.findAllByEmail(memberEmail)), HttpStatus.OK);
    }

*/

    @PostMapping("/wishList")
    public ResponseEntity<WishList> addLike(@RequestBody @Valid WishListDto wishListDto) {
        WishList wishList = wishListDto.toEntity();
        boolean result = wishListService.addLike(wishList.getMember(), wishList.getProduct().getId());
        return result ? new ResponseEntity<>(wishList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST); // 찜 중복 시 에러
    }

    /**
     * 상품 찜 취소하기
     * */
    @DeleteMapping("/wishList")
    public ResponseEntity<WishListDto> unLike(@RequestBody @Valid WishListDto wishListDto) {
        WishList wishList = wishListDto.toEntity();
        boolean result = wishListService.unLike(wishList);
        return result ? new ResponseEntity<>(wishListDto, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.BAD_REQUEST); // memberId, productId 일치하지 않는 찜 해제 시 에러
    }

    /**
     * 상품 찜 목록
     * */

    @GetMapping("/wishList")
    public ResponseEntity<List<WishList>> findWishList(/*@RequestParam(value = "memberId", required = false) Long memberId*/) {
        Member member = new Member(2L, "aaa@gmail.com", "aaa", "aaa", "무직", "10대");
        return new ResponseEntity<>(wishListService.getWishList(member), HttpStatus.OK);
    }
}