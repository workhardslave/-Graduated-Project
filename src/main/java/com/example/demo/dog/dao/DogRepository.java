package com.example.demo.dog.dao;

import com.example.demo.dog.Dog;
import com.example.demo.member.vo.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DogRepository extends JpaRepository<Dog, Long > {

    @Query("SELECT d FROM Dog d WHERE d.member = :member")
    List<Dog> findAllDesc(Member member);
}
