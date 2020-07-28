package com.example.demo.symptom.service;

import com.example.demo.symptom.repository.SymptomRepository;
import com.example.demo.symptom.dto.SymptomResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class SymptomService {

    private final SymptomRepository symptomRepository;

    @Transactional(readOnly = true)
    public List<SymptomResponseDto> findAllDesc() {
        return symptomRepository.findAllDesc().stream()
                .map(SymptomResponseDto::new)
                .collect(Collectors.toList());
    }
}
