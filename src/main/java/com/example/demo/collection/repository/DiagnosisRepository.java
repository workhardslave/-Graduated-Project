package com.example.demo.collection.repository;

import com.example.demo.collection.domain.Diagnosis;
import com.example.demo.member.vo.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DiagnosisRepository extends JpaRepository<Diagnosis, Long> {

    //증상 시각화
    @Query("SELECT m FROM Diagnosis m where m.member = :member")
    Diagnosis findOne(Member member);

    @Query("SELECT m FROM Diagnosis m where m.member =:member ORDER BY m.id DESC ")
    List<Diagnosis> findAllDesc(Member member);
}
