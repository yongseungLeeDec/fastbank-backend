/*package com.fastbank.be.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
public class WishList {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long wishListId;

    @JsonIgnore
    @Column(name = "member_email")
    private String email;

    @JsonFormat
    @Column(name = "product_id")
    private Long id;

    @JsonFormat
    @Column(name = "product_name")
    private String name;
    @JsonFormat
    @Column(name = "product_type")
    private String type;

    public WishList() {
    }

    public WishList(String memberEmail, Long id, String name, String type) {
    }
}
*/
package com.fastbank.be.domain;

import com.fastbank.be.domain.auth.Member;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WishList {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Member member;




//    @Column(name = "member_email")
//    @JsonIgnore
//    private String email;
//
//    @Column(name = "product_id")
//    @JsonIgnore
//    private Long productId;
//
//    @Column(name = "product_name")
//    @JsonIgnore
//    private String productName;
//
//    @Column(name = "product_type")
//    @JsonIgnore
//    private String productType;


    //    public WishList() {}
//
    public WishList(String email, Long productId, String productName, String productType) {}


    public WishList(Product product, Member member) {
        this.product = product;
        this.member = member;
    }
/*
    public void setProduct(Product product) {
        if (this.product != null) {
            this.product.getWishList().remove(this);
        }
        this.product = product;
        product.getWishList().add(this);

        // 무한루프 방지
       if (!product.getWishList().contains(this))
            product.addProduct(this);
    }

 */
}