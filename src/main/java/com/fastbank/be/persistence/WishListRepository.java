package com.fastbank.be.persistence;

import com.fastbank.be.domain.auth.Member;
import com.fastbank.be.domain.Product;
import com.fastbank.be.domain.WishList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface WishListRepository extends JpaRepository<WishList, Long> {
    Optional<WishList> findByMemberAndProduct(Member member, Product product);

    //List<WishList> findAllByEmail(String memberEmail);

//    @Transactional
//    void deleteByIdAndEmail(Long productId, String memberEmail);


    /**
     * 회원별 찜 목록
     * */


    // 특정 회원에 해당하는 찜 id 리스트
    @Query(value = "select w from WishList w where w.member.id = :wish ")
    List<WishList> findAllByMember(@Param("wish") Long memberId);

    @Query(value = "select w.id from WishList w where w.member.id = :wish ")
    Long findAllByMember2(@Param("wish") Long id);



    @Query(value = "select w from WishList w where w.product.id = :wish ")
    List<WishList> findAllByProduct(@Param("wish") Long productId);




//    @Query(value = "select p from Product p where p.id = (select w.id from WishList w where w.id = :wishList) ")
//    List<Product> findByProduct(@Param("wishList") List<WishList> wishList);


    /*
    select w
    from WishList w
    where w.member.id
        (select id
        from member
    */

}