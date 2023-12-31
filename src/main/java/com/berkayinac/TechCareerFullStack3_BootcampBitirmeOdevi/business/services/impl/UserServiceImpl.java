package com.berkayinac.TechCareerFullStack3_BootcampBitirmeOdevi.business.services.impl;

import com.berkayinac.TechCareerFullStack3_BootcampBitirmeOdevi.business.dtos.BMIDto;
import com.berkayinac.TechCareerFullStack3_BootcampBitirmeOdevi.business.dtos.UserLoginDto;
import com.berkayinac.TechCareerFullStack3_BootcampBitirmeOdevi.business.dtos.UserRegisterDto;
import com.berkayinac.TechCareerFullStack3_BootcampBitirmeOdevi.business.services.BMIService;
import com.berkayinac.TechCareerFullStack3_BootcampBitirmeOdevi.business.services.UserService;
import com.berkayinac.TechCareerFullStack3_BootcampBitirmeOdevi.business.dtos.UserDto;
import com.berkayinac.TechCareerFullStack3_BootcampBitirmeOdevi.business.rules.UserBusinessRules;
import com.berkayinac.TechCareerFullStack3_BootcampBitirmeOdevi.core.bean.ModelMapperBeanClass;
import com.berkayinac.TechCareerFullStack3_BootcampBitirmeOdevi.core.bean.PasswordEncoderBeanClass;
import com.berkayinac.TechCareerFullStack3_BootcampBitirmeOdevi.dataAccess.repositories.UserRepository;
import com.berkayinac.TechCareerFullStack3_BootcampBitirmeOdevi.entities.User;

import com.berkayinac.TechCareerFullStack3_BootcampBitirmeOdevi.entities.enums.UserRoles;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService<UserDto, User> {

    private UserRepository userRepository;
    private UserBusinessRules userBusinessRules;
    private final ModelMapperBeanClass modelMapperBeanClass;
    private final PasswordEncoderBeanClass passwordEncoderBeanClass;
    private BMIService bmiService;
    private static User loginUser;

    @Override
    public UserDto entityToDto(User user) {
        return this.modelMapperBeanClass.modelMapperMethod().map(user, UserDto.class);
    }

    @Override
    public User dtoToEntity(UserDto userDto) {
        return this.modelMapperBeanClass.modelMapperMethod().map(userDto, User.class);
    }

    @Override
    public List<UserDto> getAll() {


        List<UserDto> dtos = new ArrayList<>();
        var users = this.userRepository.findAll();

        //Business Rule
        this.userBusinessRules.checkUsersNotExists(users);

        users.forEach(user -> dtos.add(entityToDto(user)));

        return dtos;
    }

    @Override
    public List<UserDto> getAllByStatus(boolean status) {
        List<UserDto> dtos = new ArrayList<>();

        var users = this.userRepository.getUsersByStatus(status);

        users.forEach(user -> dtos.add(entityToDto(user)));

        //Business Rule
        this.userBusinessRules.checkUsersNotExists(users);

        return dtos;
    }

    @Override
    public UserDto getById(Long id) {
        //Business Rule
        this.userBusinessRules.checkIfUserExists(id);

        var user = this.userRepository.findById(id).get();

        var userDto = entityToDto(user);
        return userDto;
    }

    @Override
    @Transactional
    public UserDto add(UserDto userDto) {
        this.userBusinessRules.checkIfUserExists(userDto.getEmail());

        User user = dtoToEntity(userDto);

        user.setPassword(passwordEncoderBeanClass.passwordEncoderMethod().encode(userDto.getPassword()));

        this.userRepository.save(user);

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

        //Kullanıcı silindiğinde onunla birlikte ona ait olan Vucut kitle indeksleride silinecektir.
        BMIDto bmiDto = new BMIDto();
        bmiDto.setUserId(userDto.getId());
        this.bmiService.deleteAllByUserId(bmiDto);

        this.userRepository.delete(user);
        userDto.setSystemDate(new Date(System.currentTimeMillis()));
        return userDto;
    }

    // hali hazırda UserDto içerisinde validation işlemleri gerçekleştirdiğimizden dolayı
    // herhangi bir null data gelmeyeceğinden dolayı kontrol yapılmamıştır.
    @Override
    @Transactional
    public UserDto update(Long id, UserDto userDto) {

        var user = getById(id);
        User userToUpdate = dtoToEntity(userDto);

        this.userBusinessRules.checkIfUserEmailUpdate(user.getEmail(), userDto.getEmail());

        user.setFirstName(userToUpdate.getFirstName());
        user.setLastName(userToUpdate.getLastName());
        user.setEmail(userToUpdate.getEmail());
        user.setPassword(userToUpdate.getPassword());

        if (loginUser.getRole().equals(UserRoles.ADMIN.toString())) {
            user.setStatus(userToUpdate.isStatus());
            user.setRole(userToUpdate.getRole());
        }

        this.userRepository.save(dtoToEntity(user));
        userDto.setId(userToUpdate.getId());
        userDto.setSystemDate(new Date(System.currentTimeMillis()));
        return userDto;
    }

    @Override
    @Transactional
    public void userServiceSpeedData(Long speedDataCount) {
        if (speedDataCount == null) {
            return;
        }

        for (int i = 1; i <= speedDataCount; i++) {
            UserDto userDto = new UserDto();
            userDto.setFirstName("first name " + i);
            userDto.setLastName("last name " + i);
            userDto.setEmail("email " + UUID.randomUUID() + "@gmail.com");
            userDto.setPassword("password" + UUID.randomUUID());
            this.add(userDto);
        }
    }

    @Override
    @Transactional
    public UserDto register(UserRegisterDto userRegisterDto) {
        //Business Rule
        this.userBusinessRules.checkIfUserExists(userRegisterDto.getEmail());

        var userDto = this.modelMapperBeanClass.modelMapperMethod().map(userRegisterDto,UserDto.class);

        this.add(userDto);

        return userDto;
    }

    @Override
    @Transactional
    public UserDto login(UserLoginDto userLoginDto) {
        this.userBusinessRules.checkIfUserNotExists(userLoginDto.getEmail());

        var user = this.userRepository.getUserByEmail(userLoginDto.getEmail());

        this.userBusinessRules.passwordMatch(userLoginDto.getPassword(), user.getPassword());

        setLoginUser(user);

        return entityToDto(user);
    }

    public void setLoginUser(User user) {
        loginUser = user;
    }

    public User getLoginUser() {
        return loginUser;
    }
}
