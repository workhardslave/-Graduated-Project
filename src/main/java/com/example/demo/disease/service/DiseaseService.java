package com.example.demo.disease.service;

import com.example.demo.disease.repository.DiseaseRepository;
import com.example.demo.disease.dto.DiseaseNameCountDto;
import com.example.demo.disease.dto.DiseaseResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DiseaseService {

    private final DiseaseRepository diseaseRepository;

    @Transactional
    public List<DiseaseResponseDto> findAllDesc() {
        return diseaseRepository.findAllDesc().stream()
                .map(DiseaseResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<DiseaseNameCountDto> findNameCount() {
        List<DiseaseNameCountDto> dname = diseaseRepository.findNameCount();
        return dname;
    }
}
