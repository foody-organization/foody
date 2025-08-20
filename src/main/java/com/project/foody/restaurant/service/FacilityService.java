// src/main/java/com/project/foody/restaurant/service/FacilityService.java
package com.project.foody.restaurant.service;

import com.project.foody.base.service.CrudService;
import com.project.foody.restaurant.dto.FacilityDto;

public interface FacilityService
        extends CrudService<FacilityDto.Request, FacilityDto.Response, Long> {
    // 추가 메서드가 필요하면 여기에 선언
}
