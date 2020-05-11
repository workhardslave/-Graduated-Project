package com.example.demo.reserve.dao;

import com.example.demo.reserve.vo.Reserve;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ReserveRepository extends JpaRepository<Reserve, Long> {

}
