/*package com.fastbank.be.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class WishListDto {
    private Long[] wishList;
}
*/
package com.fastbank.be.dto.product;

import com.fastbank.be.domain.auth.Member;
import com.fastbank.be.domain.Product;
import com.fastbank.be.domain.WishList;
import com.fastbank.be.domain.auth.Member;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WishListDto {
    private Long id;

    private Product product;

    private Member member;

    // request
    public WishList toEntity() {
        return WishList.builder()
                .id(id)
                .product(product)
                .member(member)
                .build();
    }

}