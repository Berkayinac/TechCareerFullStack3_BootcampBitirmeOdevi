package com.berkayinac.TechCareerFullStack3_BootcampBitirmeOdevi.business.services;

import java.util.List;

//DTO : D
//ENTITY: E
public interface BMIService<D,E> {
    // MODEL MAPPER
    D entityToDto(E e);
    E dtoToEntity(D d);
    List<D> getAll();
    D getById(Long id);
    D add(D d);
    D delete(D d);
    D update(Long id,D d);
    D calculate(D d);
    D deleteAllByUserId(D d);
}
