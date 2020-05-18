package com.example.demo.member.dao;

import com.example.demo.member.vo.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.*;

public interface MemberRepository extends JpaRepository<Member, Long> {


    @Query("SELECT m FROM Member m WHERE m.role ='GUEST' ORDER BY m.id DESC ")
    List<Member> findAllDesc();
    /**
     *
     * @param email
     * @return 이미 회원가입 된 사람 입니다.
     */
    @Query("SELECT m FROM Member m where m.email =  :email")
    Member findEmailCheck(String email);

//    @Query("SELECT m FROM Member m where m.email =  :email")
    Optional<Member> findByEmail(String email);

    /**
     * @param id
     * return 회원찾기
     */
    @Query("SELECT m FROM Member m where m.id = :id")
    Member findOne(Long id);

    /**
     *
     * @param email
     * @param password
     * @return 이메일과 패스워드를 확인해주세요.
     */
    @Query("SELECT m FROM Member m where m.email = :email AND m.password = :password")
    Member findByEmailPassword(Object email, Object password);
//
//    /**
//     *
//     * @param email
//     * @param password
//     * @return 이메일 중복 확인
//     */
//    @Query("SELECT m FROM Member m where m.email = :email")
//    Member findByEmail(Object email, Object password);


//    @Modifying
//    @Query("UPDATE Member m set m.address = :address where m.id = :id")
//    Member UpdateAddress(Long id, Object address);






}
