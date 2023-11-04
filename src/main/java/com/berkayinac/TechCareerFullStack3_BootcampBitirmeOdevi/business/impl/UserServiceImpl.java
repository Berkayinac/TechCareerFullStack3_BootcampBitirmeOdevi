package com.berkayinac.TechCareerFullStack3_BootcampBitirmeOdevi.business.impl;

import com.berkayinac.TechCareerFullStack3_BootcampBitirmeOdevi.business.UserService;
import com.berkayinac.TechCareerFullStack3_BootcampBitirmeOdevi.dataAccess.repositories.UserRepository;
import com.berkayinac.TechCareerFullStack3_BootcampBitirmeOdevi.entities.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;


    @Override
    public List<User> getAll() {
        return this.userRepository.findAll();
    }

    @Override
    public List<User> getAllByStatus(boolean status) {
        return this.userRepository.getUsersByStatus(status);
    }

    @Override
    public User getById(Long id) {
        // TODO: böyle bir id olup olmadığı kontrol edilecek
        return this.userRepository.findById(id).get();
    }

    @Override
    public void add(User user) {
        this.userRepository.save(user);
    }

    @Override
    public void delete(User user) {
        this.userRepository.delete(user);
    }

    @Override
    public void update(Long id,User user) {
        var userToUpdate = getById(id);
        userToUpdate.setFirstName(user.getFirstName());
        userToUpdate.setLastName(user.getLastName());
        userToUpdate.setEmail(user.getEmail());
        userToUpdate.setPassword(user.getPassword());
        userToUpdate.setStatus(user.isStatus());
        this.userRepository.save(userToUpdate);
    }
}
