package com.project.foody.base.service;

import java.util.List;

public interface CrudService<Req, Res, ID> {
    ID create(Req dto);
    void update(ID id, Req dto);
    void delete(ID id);
    Res findById(ID id);
    List<Res> findAll();
}