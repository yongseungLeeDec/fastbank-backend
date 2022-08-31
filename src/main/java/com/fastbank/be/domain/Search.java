package com.fastbank.be.domain;

import com.fastbank.be.domain.search.Catalog;
import com.fastbank.be.domain.search.Keyword;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
public class Search {
    String word;
    Catalog catalog;
    Keyword keyword;

    @Builder
    public Search(String word, Catalog catalog, Keyword keyword) {
        this.word = word;
        this.catalog = catalog;
        this.keyword = keyword;
    }

}