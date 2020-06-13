package com.example.demo.diagnosis.service;

import com.example.demo.diagnosis.dao.DiagnosisRepository;
import com.example.demo.diagnosis.dto.DiagnosisResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DiagnosisService {

    private final DiagnosisRepository diagnosisRepository;

    @Transactional
    public List<DiagnosisResponseDto> findAllDesc() {
        return diagnosisRepository.findAllDesc().stream()
                .map(DiagnosisResponseDto::new)
                .collect(Collectors.toList());
    }

}
