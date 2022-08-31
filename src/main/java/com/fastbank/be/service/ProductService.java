package com.fastbank.be.service;

import com.fastbank.be.domain.auth.Member;
import com.fastbank.be.domain.Product;
import com.fastbank.be.domain.Search;
import com.fastbank.be.domain.search.Catalog;
import com.fastbank.be.domain.search.Keyword;
import com.fastbank.be.persistence.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@AllArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    /**
     * 전체 상품 목록
     */
    public List<Product> getProductList() {
        log.info("전체 상품 리스트 : {} ", productRepository.findAll());
        return productRepository.findAll();
    }


    /**
     * 맞춤형 상품 목록(메인 화면)
     */

    public List<Product> getCustomProductList(Member member) {
        return productRepository.findByKeyword(member.getAge(), member.getJob());
    }

    /**
     * 상품 검색 목록
     * */
    public List<Product> searchProduct(Search search) {

        List<Product> dropDown = productRepository.findByTypeOrKeyword(search.getCatalog().getValue(), search.getKeyword().getValue());
        List<Product> word = productRepository.findByNameOrContent(search.getWord(), search.getWord());

        // 1번+2번
        List<Product> searchAndDropdown = productRepository.findBySearchAndDropdown(search.getWord(), search.getWord(), search.getCatalog().getValue(), search.getKeyword().getValue());

        // search word && search keyword == jobless && product -> 무직일 때 기본 상품
        List<Product> basic = productRepository.findByBasicKeyword();

        // search word && search undefined && 상품키워드 undefined -> 전체 상품
        List<Product> all = productRepository.findAll();

        if (search.getKeyword().equals(Keyword.undefined) && search.getCatalog().equals(Catalog.undefined)){
            if (search.getWord() == null || search.getWord().isBlank()) {
                return all;
            }
            return word;
        }
        if (search.getWord() == null || search.getWord().isBlank()) {
            return dropDown;
        }
        if (search.getKeyword().equals(Keyword.무직)){
            return basic;
        }
        return searchAndDropdown;

        /* 로직 흐름
        search word && search catalog && product type -> 예금 적금 대출
        1. search 의 catalog 가 product 의 type 과 일치하는 product list 출력
        2. search 의 word 는 product 의 name, content 와 일치하는 product list 출력

        search word && search keyword && product keyword -> 나이 직업
        1. search 의 keyword 가 product 의 keyword 와 일치하는 product list 출력
        2. search 의 word 는 product 의 name, content 와 일치하는 product list 출력

        1번 -> List<Product> type = productRepository.findByTypeOrKeyword(search.getCatalog().getValue(), search.getKeyword().getValue());
        2번 -> List<Product> word = productRepository.findByNameOrContent(search.getWord(), search.getWord());
        */
        /* 검색 여부에 따른 상품 리스트
        dropdown 중 keyword 만 선택하고 + 검색창에 입력 안함 -> findByTypeOrKeyword
        dropdown 중 catalog 만 선택하고 + 검색창에 입력 안함 -> findByTypeOrKeyword
        dropdown 중 keyword 만 선택하고 + 검색창에 입력-> searchAndDropdown
        dropdown 중 catalog 만 선택하고 + 검색창에 입력-> searchAndDropdown

        dropdown 둘 다 선택 + 검색창에 입력 -> searchAndDropdown
        dropdwon 둘 다 선택 안함(UNDIFINED) + 검색창에 입력 -> findByNameOrContent
        dropdown 둘 다 선택 안함(UNDIFINED) + 검색창에 입력 안함(search.getWord() == "") -> 상품전체리스트
        */
    }
}