package com.fastbank.be.web.controller;

import com.fastbank.be.domain.auth.Member;
import com.fastbank.be.domain.Product;
import com.fastbank.be.domain.Search;
import com.fastbank.be.domain.search.Catalog;
import com.fastbank.be.domain.search.Keyword;
import com.fastbank.be.dto.SearchDto;
import com.fastbank.be.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ProductController {
    private final ProductService productService;

    /**
     * 전체 상품 목록
     */
    @GetMapping("/product")
    public ResponseEntity<List<Product>> findAllProduct() {
        log.debug("전체 상품 리스트 : ", productService.getProductList());
        return new ResponseEntity<>(productService.getProductList(), HttpStatus.OK);
    }


    /**
     * 맞춤형 상품 목록
     */
    @GetMapping("/product-custom")
    public List<Product> findCustomProduct() {
        // 회원 임시 데이터 -> jwt 토큰
        Member member = new Member(1L, "aaa@gmail.com", "aaa", "aaa", "무직", "10대");
        return productService.getCustomProductList(member);
    }

    /**
     * 상품 검색
     */
    @GetMapping("/product-search")
    public List<Product> searchProduct(
            @RequestParam(value = "word", required = false) String word,
            @RequestParam(value = "catalog", required = false) String catalog,
            @RequestParam(value = "keyword", required = false) String keyword) {

        String changeKeyword = changeAge(keyword); // keyword 가 나이인 경우 한글->영문 변환
        log.info("keyword : {}", changeKeyword);

        SearchDto searchDto = SearchDto.builder()
                .word(word)
                .catalog(Catalog.valueOf(catalog))
                .keyword(Keyword.valueOf(changeKeyword))
                .build();
        Search search = searchDto.toEntity();
        log.info("controller word : url -> {}, searchDto.getWord -> {}, search.getWord -> {} ", word, searchDto.getWord(), search.getWord());
        return productService.searchProduct(search);
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