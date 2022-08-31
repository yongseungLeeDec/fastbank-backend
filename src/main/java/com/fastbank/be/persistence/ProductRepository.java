package com.fastbank.be.persistence;

import com.fastbank.be.domain.auth.Member;
import com.fastbank.be.domain.Product;
import com.fastbank.be.domain.search.Catalog;
import com.fastbank.be.dto.ProductDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    /**
     * member 맞춤형 상품 리스트
     * */
    // 회원의 나이 or 직업과 일치하는 상품 리스트
    @Query(value = "select p from Product p where p.keyword like %:age% or p.keyword like %:job% or p.keyword='기본'")
    List<Product> findByKeyword(@Param("age") String age, @Param("job") String job);



    /**
     * 상품 리스트 검색
     * */
    // dropdown 상품 유형별 -> 예금, 적금, 대출
    @Query(value = "select p from Product p where p.type like %:type%")
    List<Product> findByType(@Param("type") String type);

    // dropdwon 상품 키워드별 중 무직일 때 기본 상품 리스트만
    @Query(value = "select p from Product p where p.keyword='기본'")
    List<Product> findByBasicKeyword();

    // search -> 상품 이름 및 상세설명
    @Query(value = "select p from Product p where p.name like %:name% or p.content like %:content%")
    List<Product> findByNameOrContent(@Param("name") String name, @Param("content") String content);

    // dropdown 상품 유형별 + 상품 키워드별
    @Query(value = "select p from Product p where p.type like %:type% and p.keyword like %:keyword%")
    List<Product> findByTypeOrKeyword(@Param("type") String type, @Param("keyword") String keyword);

    // search + dropdown
    @Query(value = "select p from Product p where (p.name like %:name% or p.content like %:content%) and (p.type like %:type% and p.keyword like %:keyword%)")
    List<Product> findBySearchAndDropdown(@Param("name") String name, @Param("content") String content, @Param("type") String type, @Param("keyword") String keyword);


}



