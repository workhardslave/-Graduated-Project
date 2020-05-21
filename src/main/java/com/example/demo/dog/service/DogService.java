package com.example.demo.dog.service;


import com.example.demo.dog.dto.Dog;
import com.example.demo.dog.dao.DogRepository;
import com.example.demo.dog.dto.DogResponseDto;
import com.example.demo.dog.dto.DogSaveRequestDto;
import com.example.demo.dog.dto.DogUpdateRequestDto;
import com.example.demo.member.vo.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DogService {

    @Autowired
    DogRepository dogRepository;


    //사용자가 본인의 반려견정보저장
    @Transactional
    public Long dog_SignUp(DogSaveRequestDto Dto) {
        return dogRepository.save(Dto.toEntity()).getId();
    }

    //사용자가 본인의 반려견정보조회
    @Transactional(readOnly = true)
    public List<DogResponseDto> findAllDesc(Member member) {
        return dogRepository.findAllDesc(member).stream()
                .map(DogResponseDto::new)
                .collect(Collectors.toList());
    }

    //사용자가 본인의 반려견정보수정 POST
    @Transactional
    public Long update(Long id, DogUpdateRequestDto requestDto) {
        Dog dog = dogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 회원이 없습니다. id=" + id));

        dog.update(requestDto.getAge(),requestDto.getName(),requestDto.getValue());
        return id;
    }

    //사용자가 본인의 반려견정보수정 GET
    @Transactional(readOnly = true)
    public DogResponseDto findById(Long id) {
        Dog entity = dogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 회원이 없습니다. id=" + id));

        return new DogResponseDto(entity);
    }

    //사용자가 본인의 반려견정보삭제
    @Transactional
    public void delete (Long id) {
        Dog dog = dogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));
        dogRepository.delete(dog);
    }


}

