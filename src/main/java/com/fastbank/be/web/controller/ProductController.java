package com.fastbank.be.web.controller;

import com.fastbank.be.domain.auth.Member;
import com.fastbank.be.domain.Product;
import com.fastbank.be.domain.Search;
import com.fastbank.be.domain.search.Catalog;
import com.fastbank.be.domain.search.Keyword;
import com.fastbank.be.dto.product.MemberProductDto;
import com.fastbank.be.dto.product.SearchDto;
import com.fastbank.be.jwt.TokenProvider;
import com.fastbank.be.persistence.MemberRepository;
import com.fastbank.be.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ProductController {
    private final ProductService productService;
    private final TokenProvider tokenProvider;
    private final MemberRepository memberRepository;

    /**
     * 전체 상품 목록
     */
    @GetMapping("/product")
    public ResponseEntity<MemberProductDto> findAllProduct(HttpServletRequest httpServletRequest) {
        String memberEmail = tokenProvider.getSubject(httpServletRequest);
        Optional<Member> member  = memberRepository.findAllByEmail(memberEmail);

        if (!(member.isPresent())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        List<Product> productList = productService.getProductList();
        return new ResponseEntity<>(new MemberProductDto("200", productList), HttpStatus.OK);
    }


    /**
     * 맞춤형 상품 목록
     */
    @GetMapping("/product-custom")
    public ResponseEntity<MemberProductDto> findCustomProduct(HttpServletRequest httpServletRequest) {
        String memberEmail = tokenProvider.getSubject(httpServletRequest);
        Optional<Member> member  = memberRepository.findOneByEmail(memberEmail);
        List<Product> customProductList = productService.getCustomProductList(member.get());
        return new ResponseEntity<>(new MemberProductDto("200", customProductList), HttpStatus.OK);
    }

    /**
     * 상품 검색
     */
    @GetMapping("/product-search")
    public ResponseEntity<MemberProductDto> searchProduct(
            HttpServletRequest httpServletRequest,
            @RequestParam(value = "word", required = false) String word,
            @RequestParam(value = "catalog", required = false) String catalog,
            @RequestParam(value = "keyword", required = false) String keyword) {
        String memberEmail = tokenProvider.getSubject(httpServletRequest);
        Optional<Member> member  = memberRepository.findOneByEmail(memberEmail);

        if (!(member.isPresent())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        String changeKeyword = changeAge(keyword); // keyword 가 나이인 경우 한글->영문 변환
        log.info("keyword : {}", changeKeyword);

        SearchDto searchDto = SearchDto.builder()
                .word(word)
                .catalog(Catalog.valueOf(catalog))
                .keyword(Keyword.valueOf(changeKeyword))
                .build();
        Search search = searchDto.toEntity();
        log.info("controller word : url -> {}, searchDto.getWord -> {}, search.getWord -> {} ", word, searchDto.getWord(), search.getWord());

        List<Product> searchProduct = productService.searchProduct(search);
        return new ResponseEntity<>(new MemberProductDto("200", searchProduct), HttpStatus.OK);

    }

    // keyword 나이일 때 한글->영문
    private String changeAge(String keyword) {
        String[] age = {"10대", "20대", "30대", "40대", "50대", "60대 이상"};
        boolean check = Arrays.asList(age).contains(keyword);
        log.info("check : {}", check);

        if (check) {
            if (keyword.equals("10대")) {
                keyword = "TEENAGER";
            }
            if (keyword.equals("20대")) {
                keyword = "TWENTIES";
            }
            if (keyword.equals("30대")) {
                keyword = "THIRTIES";
            }
            if (keyword.equals("40대")) {
                keyword = "FORTIES";
            }
            if (keyword.equals("50대")) {
                keyword = "FIFTIES";
            }
            if (keyword.equals("60대 이상")) {
                keyword = "SIXTIES";
            }
        }
        //int index = Arrays.binarySearch(age, keyword);
        return keyword;
    }

}