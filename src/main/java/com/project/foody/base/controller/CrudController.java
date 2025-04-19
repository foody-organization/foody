package com.project.foody.base.controller;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CrudController<Req, Res, ID> {
    ResponseEntity<ID> create(Req dto);
    ResponseEntity<Res> findById(ID id);
    void update(ID id, Req dto);
    void delete(ID id);
    ResponseEntity<List<Res>> findAll();
}
