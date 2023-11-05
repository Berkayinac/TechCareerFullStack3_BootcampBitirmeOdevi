package com.berkayinac.TechCareerFullStack3_BootcampBitirmeOdevi.api.controllers;

import com.berkayinac.TechCareerFullStack3_BootcampBitirmeOdevi.entities.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

// D -> Dto
public interface IUserApi<D> {
    ResponseEntity<List<D>> userApiGetAll();
    ResponseEntity<List<D>> userApiGetAllByStatus(boolean status);
    ResponseEntity<D> userApiGetById(Long id);
    ResponseEntity<?> userApiCreate(D d);
    ResponseEntity<?> userApiDelete(D d);
    ResponseEntity<?> userApiUpdate(Long id,D d);
}
