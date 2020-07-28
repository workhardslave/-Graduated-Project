package com.example.demo.Item.repository;

import com.example.demo.Item.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {

    @Query("SELECT i FROM Item i WHERE i.hospital = :hospital ORDER BY i.id DESC ")
    List<Item> findAllDesc();

}
