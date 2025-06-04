package com.project.foody.restaurant.service;

import com.project.foody.base.service.CrudService;
import com.project.foody.restaurant.dto.MenuDto;

public interface MenuService extends CrudService<MenuDto.Request, MenuDto.Response, Long> {
}
