package com.fastbank.be.dto.cart;

import com.fastbank.be.domain.CartItem;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class MemberCartDto {
    @JsonFormat
    private String statusCode;

    @JsonFormat
    private List<CartItem> cartItems;
}