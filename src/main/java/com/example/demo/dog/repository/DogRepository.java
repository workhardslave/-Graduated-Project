package com.example.demo.dog.repository;

import com.example.demo.dog.vo.Dog;
import com.example.demo.member.vo.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DogRepository extends JpaRepository<Dog, Long > {

    @Query("SELECT d FROM Dog d WHERE d.member = :member")
    List<Dog> findAllDesc(Member member);
}
