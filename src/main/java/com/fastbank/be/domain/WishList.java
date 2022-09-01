package com.fastbank.be.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


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
        this.email = memberEmail;
        this.id = id;
        this.name = name;
        this.type = type;
    }
}