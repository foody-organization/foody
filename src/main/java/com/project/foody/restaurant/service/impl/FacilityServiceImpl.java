package com.project.foody.restaurant.service.impl;

import com.project.foody.restaurant.dto.FacilityDto;
import com.project.foody.restaurant.entity.Facility;
import com.project.foody.restaurant.repository.FacilityRepository;
import com.project.foody.restaurant.service.FacilityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class FacilityServiceImpl implements FacilityService {

    private final FacilityRepository repository;

    @Override
    public Long create(FacilityDto.Request dto) {
        Facility facility = Facility.builder()
                .name(dto.getName())
                .build();

        return repository.save(facility).getId();
    }

    @Override
    public void update(Long id, FacilityDto.Request dto) {
        Facility facility = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Facility not found"));

        facility.update(dto.getName());
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public FacilityDto.Response findById(Long id) {
        return repository.findById(id)
                .map(f -> FacilityDto.Response.builder()
                        .id(f.getId())
                        .name(f.getName())
                        .createDate(f.getCreateDate())
                        .updateDate(f.getUpdateDate())
                        .build())
                .orElseThrow(() -> new IllegalArgumentException("Facility not found"));
    }

    @Override
    public List<FacilityDto.Response> findAll() {
        return repository.findAll().stream()
                .map(f -> FacilityDto.Response.builder()
                        .id(f.getId())
                        .name(f.getName())
                        .createDate(f.getCreateDate())
                        .updateDate(f.getUpdateDate())
                        .build())
                .collect(Collectors.toList());
    }
}
