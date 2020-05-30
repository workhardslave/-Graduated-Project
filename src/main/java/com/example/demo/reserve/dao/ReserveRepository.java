package com.example.demo.reserve.dao;

import com.example.demo.member.vo.Member;
import com.example.demo.reserve.vo.Reserve;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface ReserveRepository extends JpaRepository<Reserve, Long> {

    @Query("SELECT r FROM Reserve r WHERE r.member = :member")
    List<Reserve> findAllDesc(Member member);

}
