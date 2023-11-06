package com.berkayinac.TechCareerFullStack3_BootcampBitirmeOdevi.business.services.impl;

import com.berkayinac.TechCareerFullStack3_BootcampBitirmeOdevi.business.services.UserService;
import com.berkayinac.TechCareerFullStack3_BootcampBitirmeOdevi.business.dtos.UserDto;
import com.berkayinac.TechCareerFullStack3_BootcampBitirmeOdevi.business.rules.UserBusinessRules;
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
import java.util.UUID;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService<UserDto,User> {
    private UserRepository userRepository;
    private UserBusinessRules userBusinessRules;
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

        //Business Rule
        userBusinessRules.checkUsersNotExists(users);

        users.forEach(user -> dtos.add(entityToDto(user)));

       return dtos;
    }

    @Override
    public List<UserDto> getAllByStatus(boolean status) {
        List<UserDto> dtos = new ArrayList<>();
        var users =  this.userRepository.findAll();

        //Business Rule
        userBusinessRules.checkUsersNotExists(users);

        users.forEach(user -> {
            if(user.isStatus() == status){
                dtos.add(entityToDto(user));
            }
        });

        return dtos;
    }

    @Override
    public UserDto getById(Long id) {
        var user = this.userRepository.findById(id);

        //Business Rule
        userBusinessRules.checkIfUserExists(id);

        var userDto =  entityToDto(user.get());
        return userDto;
    }

    @Override
    @Transactional
    public UserDto add(UserDto userDto) {
        //Business Rule
        userBusinessRules.checkIfUserExists(userDto.getEmail());

        User user = dtoToEntity(userDto);
        user.setPassword(passwordEncoderBeanClass.passwordEncoderMethod().encode(userDto.getPassword()));
        userRepository.save(user);

        userDto.setId(user.getId());
        userDto.setSystemDate(new Date(System.currentTimeMillis()));

        return userDto;
    }

    // hali hazırda UserDto içerisinde validation işlemleri gerçekleştirdiğimizden dolayı
    // herhangi bir null data gelmeyeceğinden dolayı kontrol yapılmamıştır.
    @Override
    @Transactional
    public UserDto delete(UserDto userDto) {
        var user = this.userRepository.getUserByEmail(userDto.getEmail());
        userRepository.delete(user);
        userDto.setSystemDate(new Date(System.currentTimeMillis()));
        return userDto;
    }

    // hali hazırda UserDto içerisinde validation işlemleri gerçekleştirdiğimizden dolayı
    // herhangi bir null data gelmeyeceğinden dolayı kontrol yapılmamıştır.
    @Override
    @Transactional
    public UserDto update(Long id, UserDto userDto) {
        // TODO: email hali hazırda var olduğundan userDto tarafından gelen email'de aynı olduğundan sistem hata veriyor ilgilen.
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
        return userDto;
    }

    @Override
    public void userServiceSpeedData(Long speedDataCount) {
        if(speedDataCount == null){
            return;
        }

        for (int i = 1; i <=speedDataCount ; i++) {
            UserDto userDto = new UserDto();
            userDto.setFirstName("first name "+ i);
            userDto.setLastName("last name "+ i);
            userDto.setEmail("email " + UUID.randomUUID() + "@gmail.com");
            userDto.setPassword("password" + UUID.randomUUID());
            this.add(userDto);
        }

    }
}
