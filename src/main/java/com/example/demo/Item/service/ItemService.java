package com.example.demo.item.service;

import com.example.demo.item.dto.ItemResponseDto;
import com.example.demo.item.repository.ItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ItemService {

    // 수의사, 판매상품 조회
//    @Transactional(readOnly = true)
//    public List<ItemResponseDto> findAllDesc() {
//        return ItemRepository.findAllDesc().stream()
//                .map(ItemResponseDto::new)
//                .collect(Collectors.toList());
//    }

    // 수의사, 판매상품 등록
}
