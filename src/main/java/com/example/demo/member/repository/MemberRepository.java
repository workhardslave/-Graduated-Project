package com.example.demo.member.repository;

import com.example.demo.hospital.vo.Hospital;
import com.example.demo.hospital.vo.HospitalSaveRequestDto;
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

    /**
     * @param id
     * return 회원찾기
     */
    @Query("SELECT m FROM Member m where m.id = :id")
    Member findOne(Long id);

    /**
     * @param id
     * @param hospital
     * @return member에 존재하는 hospital FK에 hospital을 등록합니다.
     */
    @Query("UPDATE Member m SET m.hospital = :hospital   where m.id = :id")
    void InsertUpdateHospital(HospitalSaveRequestDto hospital, Long id);

    @Query("UPDATE Member m SET m.hospital = :null where m.id = :id")
    void DeleteHospital(Long id);

}
