package com.berkayinac.TechCareerFullStack3_BootcampBitirmeOdevi.api.controllers;

import org.springframework.http.ResponseEntity;

import java.util.List;

// DTO : D
public interface IBMIApi<D> {

    ResponseEntity<List<D>> getAll();
    ResponseEntity<D> getById(Long id);
    ResponseEntity<?> create(D d);
    ResponseEntity<?> delete(D d);
    ResponseEntity<?> update(Long id,D d);
    ResponseEntity<?> calculate(D d);
    ResponseEntity<?> deleteAllByUserId(D d);
}
