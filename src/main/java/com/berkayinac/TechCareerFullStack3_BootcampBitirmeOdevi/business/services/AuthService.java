package com.berkayinac.TechCareerFullStack3_BootcampBitirmeOdevi.business.services;

import com.berkayinac.TechCareerFullStack3_BootcampBitirmeOdevi.business.dtos.BMIDto;
import com.berkayinac.TechCareerFullStack3_BootcampBitirmeOdevi.business.dtos.UserDto;
import com.berkayinac.TechCareerFullStack3_BootcampBitirmeOdevi.business.dtos.UserLoginDto;
import com.berkayinac.TechCareerFullStack3_BootcampBitirmeOdevi.business.dtos.UserRegisterDto;
import com.berkayinac.TechCareerFullStack3_BootcampBitirmeOdevi.entities.User;

import java.util.List;

public interface AuthService {
    List<UserDto> getAllUsers();
    List<UserDto> getAllUsersByStatus(boolean status);
    UserDto getUserById(Long userId);
    UserDto updateUser(Long userId, UserDto userDto);
    UserDto deleteUser(UserDto userDto);
    User getLoginUser();
    UserDto register(UserRegisterDto userRegisterDto);
    UserDto login(UserLoginDto userLoginDto);

    BMIDto calculateBMI(Long userId,BMIDto bmiDto);
    BMIDto deleteBMIByUserId(Long userId, BMIDto bmiDto);
    BMIDto deleteAllByUserId(Long userId, BMIDto bmiDto);
    List<BMIDto> getAllBMIs();
    BMIDto getBMIById(Long id);
}
