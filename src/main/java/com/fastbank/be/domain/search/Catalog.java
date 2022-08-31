package com.fastbank.be.domain.search;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Catalog {

    undefined("undefined","undefined"), // dropdown 중 전체 상품
    예금("예금","예금"),
    적금("적금","적금"),
    대출("대출", "대출");

    private final String key;
    private final String value;
}