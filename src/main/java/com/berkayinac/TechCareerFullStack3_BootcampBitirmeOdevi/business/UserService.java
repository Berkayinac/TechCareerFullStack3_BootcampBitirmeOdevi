package com.berkayinac.TechCareerFullStack3_BootcampBitirmeOdevi.business;

import com.berkayinac.TechCareerFullStack3_BootcampBitirmeOdevi.entities.User;

import java.util.List;

//D: Dto
//E: Entity
public interface UserService<D,E> {
    // MODEL MAPPER
    D entityToDto(E e);
    E dtoToEntity(D d);
    List<D> getAll();
    List<D> getAllByStatus(boolean status);
    D getById(Long id);
    D add(D d);
    D delete(D d);
    D update(Long id,D d);

}
