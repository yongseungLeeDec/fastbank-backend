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

        // search 의 catalog, keyword, word 값에 따른 상품 리스트
        List<Product> searchAndDropdown = productRepository.findBySearchAndDropdown(search.getWord(), search.getWord(), search.getCatalog().getValue(), search.getKeyword().getValue());

        // search word && search keyword == jobless && product -> 무직일 때 기본 상품 리스트
        List<Product> basic = productRepository.findByBasicKeyword();

        // search word && search undefined && 상품키워드 undefined -> 전체 상품 리스트
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
    }
}