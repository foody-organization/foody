package com.project.foody.restaurant.service;

import com.project.foody.base.service.CrudService;
import com.project.foody.restaurant.dto.FacilityDto;

public interface FacilityService extends CrudService<FacilityDto.Request, FacilityDto.Response, Long> {
    // 추가적인 메서드가 필요하다면 여기에 선언할 수 있어
}

