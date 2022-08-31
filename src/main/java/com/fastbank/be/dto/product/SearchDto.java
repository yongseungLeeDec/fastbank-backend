package com.fastbank.be.dto;

import com.fastbank.be.domain.Search;
import com.fastbank.be.domain.search.Catalog;
import com.fastbank.be.domain.search.Keyword;
import lombok.Builder;
import lombok.Data;

@Data
public class SearchDto {

    String word;
    Catalog catalog;
    Keyword keyword;

    @Builder
    public SearchDto(String word, Catalog catalog, Keyword keyword) {
        this.word = word;
        this.catalog = catalog;
        this.keyword = keyword;
    }

    // dto -> entity (request)
    public Search toEntity() {
        return Search.builder()
                .word(word)
                .catalog(catalog)
                .keyword(keyword)
                .build();
    }

}