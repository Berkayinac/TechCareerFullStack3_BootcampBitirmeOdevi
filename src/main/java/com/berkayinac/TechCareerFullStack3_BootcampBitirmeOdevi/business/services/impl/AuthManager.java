package com.berkayinac.TechCareerFullStack3_BootcampBitirmeOdevi.business.services.impl;

import com.berkayinac.TechCareerFullStack3_BootcampBitirmeOdevi.business.dtos.BMIDto;
import com.berkayinac.TechCareerFullStack3_BootcampBitirmeOdevi.business.dtos.UserDto;
import com.berkayinac.TechCareerFullStack3_BootcampBitirmeOdevi.business.dtos.UserLoginDto;
import com.berkayinac.TechCareerFullStack3_BootcampBitirmeOdevi.business.dtos.UserRegisterDto;
import com.berkayinac.TechCareerFullStack3_BootcampBitirmeOdevi.business.rules.AuthBusinessRules;
import com.berkayinac.TechCareerFullStack3_BootcampBitirmeOdevi.business.services.AuthService;
import com.berkayinac.TechCareerFullStack3_BootcampBitirmeOdevi.business.services.BMIService;
import com.berkayinac.TechCareerFullStack3_BootcampBitirmeOdevi.business.services.UserService;
import com.berkayinac.TechCareerFullStack3_BootcampBitirmeOdevi.entities.BMIEntity;
import com.berkayinac.TechCareerFullStack3_BootcampBitirmeOdevi.entities.User;
import com.berkayinac.TechCareerFullStack3_BootcampBitirmeOdevi.entities.enums.UserRoles;
import org.springframework.transaction.annotation.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class AuthManager implements AuthService {

    private UserService<UserDto, User> userService;
    private AuthBusinessRules authBusinessRules;
    private BMIService<BMIDto, BMIEntity> bmiService;

    @Override
    public List<UserDto> getAllUsers() {
        var user = this.getLoginUser();
        this.authBusinessRules.checkUserLogin(user);

        // this.authBusinessRules.checkIfUserRoleUnAuthorized(user.getRole());

        var userDtoList = this.userService.getAll();

        if(user.getRole().equals(UserRoles.ADMIN.toString())){
            return userDtoList;
        }
        else{
            return userDtoList.stream().filter(userDto -> Objects.equals(userDto.getId(), user.getId())).toList();
        }
    }

    @Override
    public List<UserDto> getAllUsersByStatus(boolean status) {
        var user = this.getLoginUser();
        this.authBusinessRules.checkUserLogin(user);
       // this.authBusinessRules.checkIfUserRoleUnAuthorized(this.getLoginUser().getRole());

        var userDtoList = this.userService.getAllByStatus(status);

        if(user.getRole().equals(UserRoles.ADMIN.toString())){
            return userDtoList;
        }
        else{
            return userDtoList.stream().filter(userDto -> Objects.equals(userDto.getId(), user.getId())).toList();
        }
    }

    @Override
    public UserDto getUserById(Long userId) {
        // Login yapan kullanıcının rolü ADMIN ise bu metot kullanılabilir.
        // Rolü USER veya EDITOR ise get edilmek istenen id ile istek yapan kullanıcının ID'sinin match edilmesi kontrol edilir.
        this.authBusinessRules.checkUserLogin(this.getLoginUser());
        // this.authBusinessRules.checkIfUserRoleUnAuthorized(this.getLoginUser().getRole());
        if (!this.getLoginUser().getRole().equals(UserRoles.ADMIN.toString())) {
            this.authBusinessRules.checkIfUsersNotMatch(this.getLoginUser().getId(), userId);
        }

        return this.userService.getById(userId);
    }

    @Override
    @Transactional
    public UserDto updateUser(Long userId, UserDto userDto) {
        this.authBusinessRules.checkUserLogin(this.getLoginUser());
        return this.userService.update(userId,userDto);
    }

    @Override
    @Transactional
    public UserDto deleteUser(UserDto userDto) {
        this.authBusinessRules.checkUserLogin(this.getLoginUser());
        if (!this.getLoginUser().getRole().equals(UserRoles.ADMIN.toString())) {
            this.authBusinessRules.checkIfUsersNotMatch(this.getLoginUser().getId(), userDto.getId());
        }
        return this.userService.delete(userDto);
    }

    @Override
    public User getLoginUser() {
        return this.userService.getLoginUser();
    }

    @Override
    @Transactional
    public UserDto register(UserRegisterDto userRegisterDto) {
        return this.userService.register(userRegisterDto);
    }

    @Override
    @Transactional
    public UserDto login(UserLoginDto userLoginDto) {
        return this.userService.login(userLoginDto);
    }

    @Override
    @Transactional
    public BMIDto calculateBMI(Long userId, BMIDto bmiDto) {
        this.authBusinessRules.checkUserLogin(this.getLoginUser());
        this.verifyUserRoleAndUserId(userId,bmiDto);

        return this.bmiService.calculate(bmiDto);
    }

    @Override
    @Transactional
    public BMIDto deleteBMIByUserId(Long userId, BMIDto bmiDto) {
        this.authBusinessRules.checkUserLogin(this.getLoginUser());
    // this.authBusinessRules.checkIfUserRoleUnAuthorized(this.getLoginUser().getRole());
        this.verifyUserRoleAndUserId(userId,bmiDto);

        return this.bmiService.delete(bmiDto);
    }

    @Override
    @Transactional
    public BMIDto deleteAllByUserId(Long userId, BMIDto bmiDto) {
        this.authBusinessRules.checkUserLogin(this.getLoginUser());
        // this.authBusinessRules.checkIfUserRoleUnAuthorized(this.getLoginUser().getRole());
        this.verifyUserRoleAndUserId(userId,bmiDto);

        return this.bmiService.deleteAllByUserId(bmiDto);
    }

    @Override
    public List<BMIDto> getAllBMIs() {
        var user = this.getLoginUser();
        this.authBusinessRules.checkUserLogin(user);
        // this.authBusinessRules.checkIfUserRoleUnAuthorized(user.getRole());

        List<BMIDto> bmiDtoList = this.bmiService.getAll();

        if(user.getRole().equals(UserRoles.ADMIN.toString())){
            return bmiDtoList;
        }
        else{
            return bmiDtoList.stream().filter(bmiDto -> Objects.equals(bmiDto.getUserId(), user.getId())).toList();
        }
    }

    @Override
    public BMIDto getBMIById(Long id) {
        var user = this.getLoginUser();
        this.authBusinessRules.checkUserLogin(user);

        // this.authBusinessRules.checkIfUserRoleUnAuthorized(user.getRole());

        if(user.getRole().equals(UserRoles.ADMIN.toString())){
           return this.bmiService.getById(id);
        }

        var bmiDtoList = this.getAllBMIs();
        return bmiDtoList.stream().filter(bmiDto -> Objects.equals(bmiDto.getId(), id)).findFirst().get();
    }

    private void verifyUserRoleAndUserId(Long userId, BMIDto bmiDto){
        var user = this.getLoginUser();

        // calculate metodunu çalıştıran kişi ADMIN yetkisine sahip ise userId parametreden alınarak işlem yapılır.
        if(user.getRole().equals(UserRoles.ADMIN.toString())){
            bmiDto.setUserId(userId);
        }

        // eğer kullanıcı admin yetkisine sahip değil ise login yapan kullanıcının Id'si kullanılır.
        else{
            bmiDto.setUserId(user.getId());
        }
    }
}
