package com.fastbank.be.dto.product;

import com.fastbank.be.domain.WishList;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class MemberWishListDto {
    @JsonFormat
    private String statusCode;

    @JsonFormat
    private List<WishList> wishList;
}
