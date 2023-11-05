package com.berkayinac.TechCareerFullStack3_BootcampBitirmeOdevi.business.impl;

import com.berkayinac.TechCareerFullStack3_BootcampBitirmeOdevi.business.UserService;
import com.berkayinac.TechCareerFullStack3_BootcampBitirmeOdevi.business.dtos.UserDto;
import com.berkayinac.TechCareerFullStack3_BootcampBitirmeOdevi.core.bean.ModelMapperBeanClass;
import com.berkayinac.TechCareerFullStack3_BootcampBitirmeOdevi.core.bean.PasswordEncoderBeanClass;
import com.berkayinac.TechCareerFullStack3_BootcampBitirmeOdevi.dataAccess.repositories.UserRepository;
import com.berkayinac.TechCareerFullStack3_BootcampBitirmeOdevi.entities.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService<UserDto,User> {
    private UserRepository userRepository;
    private final ModelMapperBeanClass modelMapperBeanClass;
    private final PasswordEncoderBeanClass passwordEncoderBeanClass;

    @Override
    public UserDto entityToDto(User user) {
        return modelMapperBeanClass.modelMapperMethod().map(user,UserDto.class);
    }

    @Override
    public User dtoToEntity(UserDto userDto) {
        return modelMapperBeanClass.modelMapperMethod().map(userDto,User.class);
    }

    @Override
    public List<UserDto> getAll() {
        List<UserDto> dtos = new ArrayList<>();
        var users =  this.userRepository.findAll();

        if(users.isEmpty()){
            // TODO: Hiç kullanıcı yoksa kullanıcı bulunamadı mesajı gönderilsin.
            return null;
        }

        users.forEach(user -> dtos.add(entityToDto(user)));

       return dtos;
    }

    @Override
    public List<UserDto> getAllByStatus(boolean status) {
        List<UserDto> dtos = new ArrayList<>();
        var users =  this.userRepository.findAll().stream().filter(user -> status);

        if(users.toList().isEmpty()){
            // TODO: kullanıcı yoksa kullanıcı yok mesajı döndür.
            return null;
        }

        users.forEach(user -> dtos.add(entityToDto(user)));
        return dtos;
    }

    @Override
    public UserDto getById(Long id) {
        var user = this.userRepository.findById(id);
        if(user.isEmpty()){
            // TODO: kullanıcı yoksa kullanıcı yok mesajı döndür.
            return null;
        }
        var userDto =  entityToDto(user.get());
        return userDto;
    }

    @Override
    @Transactional
    public UserDto add(UserDto userDto) {
        if(userDto!=null){
            User user = dtoToEntity(userDto);
            user.setPassword(passwordEncoderBeanClass.passwordEncoderMethod().encode(userDto.getPassword()));
            userRepository.save(user);
            userDto.setId(user.getId());
            userDto.setSystemDate(new Date(System.currentTimeMillis()));
        }
        return userDto;
    }

    @Override
    @Transactional
    public UserDto delete(UserDto userDto) {
        if(userDto!=null){
            User user = dtoToEntity(userDto);
            userRepository.delete(user);
            userDto.setId(user.getId());
            userDto.setSystemDate(new Date(System.currentTimeMillis()));
        }
        return userDto;
    }

    @Override
    @Transactional
    public UserDto update(Long id, UserDto userDto) {
        if(userDto!=null){
            var user = getById(id);
            User userToUpdate = dtoToEntity(userDto);

            user.setFirstName(userToUpdate.getFirstName());
            user.setLastName(userToUpdate.getLastName());
            user.setEmail(userToUpdate.getEmail());
            user.setPassword(userToUpdate.getPassword());
            user.setStatus(userToUpdate.isStatus());

            userRepository.save(dtoToEntity(user));
            userDto.setId(userToUpdate.getId());
            userDto.setSystemDate(new Date(System.currentTimeMillis()));
        }
        return userDto;
    }


//    @Override
//    public List<User> getAll() {
//        return this.userRepository.findAll();
//    }
//
//    @Override
//    public List<User> getAllByStatus(boolean status) {
//        return this.userRepository.getUsersByStatus(status);
//    }
//
//    @Override
//    public User getById(Long id) {
//        // TODO: böyle bir id olup olmadığı kontrol edilecek
//        return this.userRepository.findById(id).get();
//    }
//
//    @Override
//    public void add(User user) {
//        this.userRepository.save(user);
//    }
//
//    @Override
//    public void delete(User user) {
//        this.userRepository.delete(user);
//    }
//
//    @Override
//    public void update(Long id,User user) {
//        var userToUpdate = getById(id);
//        userToUpdate.setFirstName(user.getFirstName());
//        userToUpdate.setLastName(user.getLastName());
//        userToUpdate.setEmail(user.getEmail());
//        userToUpdate.setPassword(user.getPassword());
//        userToUpdate.setStatus(user.isStatus());
//        this.userRepository.save(userToUpdate);
//    }



}
