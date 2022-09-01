package com.fastbank.be.dto.product;

import com.fastbank.be.domain.Product;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class MemberProductDto {
    @JsonFormat
    private String statusCode;

    @JsonFormat
    private List<Product> products;
}
