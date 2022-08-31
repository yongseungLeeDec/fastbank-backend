package com.fastbank.be.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
/*
    @OneToMany(mappedBy = "product")
    private List<WishList> wishList = new ArrayList<>();

    public void setWishList(WishList wishList) {
        this.wishList.add(wishList);
        if (wishList.getProduct() != this) {
            wishList.setProduct(this);
        }
    }

    public void addProduct(WishList wishList) {
        this.wishList.add(wishList);
        // 무한루프 방지
        if (wishList.getProduct() != this) {
            wishList.setProduct(this);
        }
    }
*/
//    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
//    Set<WishList> wishListSet = new HashSet<>();

    //private String targetAge;   // 회원별 맞춤형 상품 기준 - 나이
    //private String targetJob;   // 회원별 맞춤형 상품 기준 - 직업

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "member_id")
//    private Member member;

    //==연관 관계 메서드==//
//    public void setTargetAge(Member member) {
//        this.targetAge = member.getAge();
//    }

//    public void setMember(Member member) {
//        this.member = member;
//        member.getProducts().add(this);
//    }

//    public void setWishList(WishList wishList) {
//        this.wishList = (List<WishList>) wishList;
//        wishList.getProduct().add(this);
//    }
}