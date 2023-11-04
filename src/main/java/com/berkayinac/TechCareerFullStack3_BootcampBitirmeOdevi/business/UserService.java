package com.berkayinac.TechCareerFullStack3_BootcampBitirmeOdevi.business;

import com.berkayinac.TechCareerFullStack3_BootcampBitirmeOdevi.entities.User;

import java.util.List;

public interface UserService {

    List<User> getAll();
    List<User> getAllByStatus(boolean status);
    User getById(Long id);
    void add(User user);
    void delete(User user);
    void update(Long id,User user);

}
