package com.example.demo.member.repository;


import com.example.demo.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {


    @Query("SELECT m FROM Member m WHERE m.role ='GUEST' OR m.role = 'VET' ORDER BY m.id DESC ")
    List<Member> findAllDesc();

    @Query("SELECT m FROM Member m where m.email =  :email")
    Member findEmailCheck(String email);

}
