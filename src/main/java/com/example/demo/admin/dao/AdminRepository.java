package com.example.demo.admin.dao;

import com.example.demo.admin.vo.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Long> {

    @Query("SELECT ad FROM Member ad ORDER BY ad.id DESC")
    List<Admin> findAllDesc();

    @Query("SELECT ad FROM Member ad WHERE ad.email = :email")
    Admin findEmailCheck(String email);

    Optional<Admin> findByEmail(String email);

    @Query("SELECT ad FROM Member ad WHERE ad.id = :id")
    Admin findOne(Long id);

    @Query("SELECT ad FROM Member ad WHERE ad.email = :email AND ad.password = :password")
    Admin findByEmailPassword(Object email, Object password);
}
