package com.fastbank.be.dto;

import com.fastbank.be.domain.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductDto {
    private Long id;
    private String type;        // 상품 타입 - 예금, 적금, 대출
    private String name;        // 상품 이름
    private String keyword;     // 상품 키워드 - 나이/직업
    private String content;     // 상품 설명


    // response
    public ProductDto(Product product) {
        this.id = product.getId();
        this.type = product.getType();
        this.name = product.getName();
        this.keyword = product.getKeyword();
        this.content = product.getContent();

    }
}