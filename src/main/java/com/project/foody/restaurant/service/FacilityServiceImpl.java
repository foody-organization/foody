package com.project.foody.restaurant.service;

import com.project.foody.restaurant.dto.FacilityDto;
import com.project.foody.restaurant.entity.Facility;
import com.project.foody.restaurant.repository.FacilityRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class FacilityServiceImpl implements FacilityService {

    private final FacilityRepository facilityRepository;

    @Override
    public Long create(FacilityDto.Request dto) {
        Facility facility = Facility.builder()
                .name(dto.getName())
                .build();
        return facilityRepository.save(facility).getId();
    }

    @Override
    public void update(Long id, FacilityDto.Request dto) {
        Facility facility = facilityRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Facility not found with id: " + id));

        facility.update(dto.getName());
        facilityRepository.save(facility);
    }

    @Override
    public void delete(Long id) {
        Facility facility = facilityRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Facility not found with id: " + id));
        facilityRepository.delete(facility);
    }

    @Override
    @Transactional(readOnly = true)
    public FacilityDto.Response findById(Long id) {
        Facility facility = facilityRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Facility not found with id: " + id));
        return FacilityDto.Response.builder()
                .id(facility.getId())
                .name(facility.getName())
                .createDate(facility.getCreateDate())
                .updateDate(facility.getUpdateDate())
                .build();
    }

    @Override
    @Transactional(readOnly = true)
    public List<FacilityDto.Response> findAll() {
        return facilityRepository.findAll().stream()
                .map(f -> FacilityDto.Response.builder()
                        .id(f.getId())
                        .name(f.getName())
                        .createDate(f.getCreateDate())
                        .updateDate(f.getUpdateDate())
                        .build())
                .collect(Collectors.toList());
    }
}
