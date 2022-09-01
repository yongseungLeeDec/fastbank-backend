package com.fastbank.be.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;        // 상품 타입 - 예금, 적금, 대출
    private String name;        // 상품 이름
    private String keyword;     // 상품 키워드 - 나이/직업
    private String content;     // 상품 설명

}