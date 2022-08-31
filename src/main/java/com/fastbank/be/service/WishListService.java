package com.fastbank.be.service;

import com.fastbank.be.domain.auth.Member;
import com.fastbank.be.domain.Product;
import com.fastbank.be.domain.WishList;
import com.fastbank.be.persistence.ProductRepository;
import com.fastbank.be.persistence.WishListRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@AllArgsConstructor
@Slf4j
public class WishListService {
    private final WishListRepository wishListRepository;
    private final ProductRepository productRepository;


    /**
     * 상품 찜하기
     * */

//    @Transactional
//    public List<WishListDto> saveWishList(WishListDto wishListDto) {
//        Product product = productRepository.findById(wishListDto.getProductId()).orElseThrow();
//        String email = "aaa@gmail.com";
//        List<WishList> addWishList = new ArrayList<>();
//        addWishList.add(new WishList(email, product.getId(), product.getName(), product.getType()));
//        wishListRepository.saveAll(addWishList);
//
//        return wishListRepository.findAllByEmail(wishListDto.getEmail());
//    }



    // https://coco-log.tistory.com/133?category=912572
    @Transactional
    public boolean addLike(Member member, Long productId) {
        Product product = productRepository.findById(productId).orElseThrow();
        log.info("member, product -> {}, {}", member, product);

//        WishList wishList = new WishList();
//        wishList.setProduct(product);

/*
        WishList wishList = WishList.builder()
                .member(member)
                .product(product)
                .build();

        wishListRepository.save(wishList);
        log.info("save 후 member, product -> {}, {}", member.getId(), product.getId());*/

        // 중복 찜 방지
        if(isNotAlreadyLike(member, product)) {
            log.info("if문 안 member, product -> {}, {}", member, product);
            wishListRepository.save(new WishList(product, member));


            return true;
        }
        return false;
    }

    /**
     * 상품 찜 해제
     * */
    @Transactional
    public boolean unLike(WishList wishList) {
        //Product product = productRepository.findById(productId).orElseThrow();

        if (!isNotAlreadyLike(wishList.getMember(), wishList.getProduct())) {
            wishListRepository.delete(wishList);
            return true;
        }
        return false;

    }

    /**
     * 상품 찜 리스트
     * */
    public List<WishList> getWishList(Member member) {
        List<WishList> wishListMember = wishListRepository.findAllByMember(member.getId());
        log.info("wishList -> {}", wishListMember);

        //Long findWishListId = wishListRepository.findAllByMember2(member.getId());

        // 회원과 일치하는 위시리스트 아이디값 얻고(findWishListId) =
        // 원하는 위시리스트 찾고(findWishList) -> fidnWishList의 product의 wishList를 찾고 -> 제품 리스트 반환
        // 원하는 위시리스트 중에서 일치하는 아이디값들을 반복문으로 돌려서 찾기
/*
        // 추가
        WishList findWishList = wishListRepository.findById();
        List<WishList> wishLists = findWishList.getProduct().getWishList();
        for (WishList wishList : wishLists) {

        }
*/

        // 추가

        return wishListMember;
    }




    /**
     * 찜 여부 체크
     * */
    private boolean isNotAlreadyLike(Member member, Product product) {
        return wishListRepository.findByMemberAndProduct(member, product).isEmpty();
    }


}