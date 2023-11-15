package com.berkayinac.TechCareerFullStack3_BootcampBitirmeOdevi.api.controllers;

import com.berkayinac.TechCareerFullStack3_BootcampBitirmeOdevi.business.dtos.BMIDto;
import com.berkayinac.TechCareerFullStack3_BootcampBitirmeOdevi.business.dtos.UserDto;
import com.berkayinac.TechCareerFullStack3_BootcampBitirmeOdevi.business.dtos.UserLoginDto;
import com.berkayinac.TechCareerFullStack3_BootcampBitirmeOdevi.business.dtos.UserRegisterDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IAuthApi {
    ResponseEntity<List<UserDto>> getAllUsers();
    ResponseEntity<List<UserDto>> getAllUsersByStatus(boolean status);
    ResponseEntity<UserDto> getUserById(Long userId);
    ResponseEntity<UserDto> updateUser(Long userId, UserDto userDto);
    ResponseEntity<UserDto> deleteUser(UserDto userDto);
    ResponseEntity<UserDto> register(UserRegisterDto userRegisterDto);
    ResponseEntity<UserDto> login(UserLoginDto userLoginDto);
    ResponseEntity<BMIDto> calculateBMI(Long userId, BMIDto bmiDto);
    ResponseEntity<BMIDto> deleteBMIByUserId(Long userId, BMIDto bmiDto);
    ResponseEntity<BMIDto> deleteAllByUserId(Long userId, BMIDto bmiDto);
    ResponseEntity<List<BMIDto>> getAllBMIs();
    ResponseEntity<BMIDto> getBMIById(Long id);

}