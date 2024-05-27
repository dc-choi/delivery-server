package com.delivery.server.global.init;

import com.delivery.server.domain.item.dao.ItemRepository;
import com.delivery.server.domain.item.dao.OptionDetailRepository;
import com.delivery.server.domain.item.dao.OptionRepository;
import com.delivery.server.domain.item.entity.ItemEntity;
import com.delivery.server.domain.item.entity.OptionDetailEntity;
import com.delivery.server.domain.item.entity.OptionEntity;
import com.delivery.server.domain.store.dao.StoreRepository;
import com.delivery.server.domain.store.entity.StoreEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Configuration
public class NotProd {
    private final StoreRepository storeRepository;
    private final ItemRepository itemRepository;
    private final OptionRepository optionRepository;
    private final OptionDetailRepository optionDetailRepository;

    @Bean
    @Order(1)
    public ApplicationRunner init() {
        return args -> {
            StoreEntity store = StoreEntity.builder()
                    .name("BHC")
                    .build();

            ItemEntity chicken = ItemEntity.builder()
                    .name("후라이드치킨")
                    .unitPrice(BigDecimal.valueOf(16000))
                    .status(true)
                    .store(store)
                    .build();

            OptionEntity sauce = OptionEntity.builder()
                    .name("양념 소스")
                    .isRequired(false)
                    .item(chicken)
                    .build();

            OptionDetailEntity taste = OptionDetailEntity.builder()
                    .name("추가")
                    .detailPrice(BigDecimal.valueOf(500))
                    .option(sauce)
                    .build();

            OptionEntity mySauce = OptionEntity.builder()
                    .name("특제 소스")
                    .isRequired(false)
                    .item(chicken)
                    .build();

            OptionDetailEntity myTaste = OptionDetailEntity.builder()
                    .name("추가")
                    .detailPrice(BigDecimal.valueOf(2000))
                    .option(mySauce)
                    .build();

            ItemEntity prinkle = ItemEntity.builder()
                    .name("뿌링클")
                    .unitPrice(BigDecimal.valueOf(19000))
                    .status(true)
                    .store(store)
                    .build();

            OptionEntity prinkleSauce = OptionEntity.builder()
                    .name("전용 소스")
                    .isRequired(true)
                    .item(prinkle)
                    .build();

            OptionDetailEntity prinkleTaste = OptionDetailEntity.builder()
                    .name("배송 여부")
                    .detailPrice(BigDecimal.valueOf(0))
                    .option(prinkleSauce)
                    .build();

            storeRepository.save(store);
            itemRepository.save(chicken);
            optionRepository.save(sauce);
            optionDetailRepository.save(taste);
            optionRepository.save(mySauce);
            optionDetailRepository.save(myTaste);
            itemRepository.save(prinkle);
            optionRepository.save(prinkleSauce);
            optionDetailRepository.save(prinkleTaste);
        };
    }
}
