package com.berkayinac.TechCareerFullStack3_BootcampBitirmeOdevi.business.services;

import com.berkayinac.TechCareerFullStack3_BootcampBitirmeOdevi.business.dtos.UserLoginDto;
import com.berkayinac.TechCareerFullStack3_BootcampBitirmeOdevi.business.dtos.UserRegisterDto;
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
    void userServiceSpeedData(Long speedDataCount);
    D register(UserRegisterDto userRegisterDto);
    D login(UserLoginDto userLoginDto);
    void setLoginUser(E e);
    E getLoginUser();
}
