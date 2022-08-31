package com.fastbank.be.domain.search;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.bind.Name;

@Getter
@RequiredArgsConstructor
public enum Keyword {

    undefined("undefined","undefined"), // dropdown 중 전체 상품
    청소년("청소년","청소년"),
    대학생("대학생","대학생"),
    직장인("직장인","직장인"),
    퇴직자("퇴직자","퇴직자"),
    무직("무직","무직"),

    TEENAGER("10대","10대"),
    TWENTIES("20대","20대"),
    THIRTIES("30대","30대"),
    FORTIES("40대","40대"),
    FIFTIES("50대","50대"),
    SIXTIES("60대 이상","60대 이상");

    private final String key;
    private final String value;
}