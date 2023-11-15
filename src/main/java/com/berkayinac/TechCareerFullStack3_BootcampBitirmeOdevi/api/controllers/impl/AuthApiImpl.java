package com.berkayinac.TechCareerFullStack3_BootcampBitirmeOdevi.api.controllers.impl;

import com.berkayinac.TechCareerFullStack3_BootcampBitirmeOdevi.api.controllers.IAuthApi;
import com.berkayinac.TechCareerFullStack3_BootcampBitirmeOdevi.business.dtos.BMIDto;
import com.berkayinac.TechCareerFullStack3_BootcampBitirmeOdevi.business.dtos.UserDto;
import com.berkayinac.TechCareerFullStack3_BootcampBitirmeOdevi.business.dtos.UserLoginDto;
import com.berkayinac.TechCareerFullStack3_BootcampBitirmeOdevi.business.dtos.UserRegisterDto;
import com.berkayinac.TechCareerFullStack3_BootcampBitirmeOdevi.business.services.AuthService;
import com.berkayinac.TechCareerFullStack3_BootcampBitirmeOdevi.entities.User;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/auth/api/v1.0.0")
public class AuthApiImpl implements IAuthApi {

    private AuthService authService;

    @Override
    @GetMapping("/getallusers")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok(this.authService.getAllUsers());
    }

    @Override
    @GetMapping("/getallusersbystatus")
    public ResponseEntity<List<UserDto>> getAllUsersByStatus(@RequestParam boolean status) {
        return ResponseEntity.ok(this.authService.getAllUsersByStatus(status));
    }

    @Override
    @GetMapping("/getuserbyid")
    public ResponseEntity<UserDto> getUserById(@RequestParam Long userId) {
        return ResponseEntity.ok(this.authService.getUserById(userId));
    }

    @Override
    @PutMapping("/updateuser")
    public ResponseEntity<UserDto> updateUser(@RequestParam Long userId, @Valid @RequestBody UserDto userDto) {
        return ResponseEntity.ok(this.authService.updateUser(userId, userDto));
    }

    @Override
    @DeleteMapping("/deleteuser")
    public ResponseEntity<UserDto> deleteUser(@Valid @RequestBody UserDto userDto) {
        return ResponseEntity.ok(this.authService.deleteUser(userDto));
    }

    @Override
    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@Valid @RequestBody UserRegisterDto userRegisterDto) {
        return ResponseEntity.ok(this.authService.register(userRegisterDto));
    }

    @Override
    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@Valid @RequestBody UserLoginDto userLoginDto) {
        return ResponseEntity.ok(this.authService.login(userLoginDto));
    }

    @Override
    @PostMapping("/calculatebmi")
    public ResponseEntity<BMIDto> calculateBMI(@RequestParam Long userId, @Valid @RequestBody BMIDto bmiDto) {
        return ResponseEntity.ok(this.authService.calculateBMI(userId, bmiDto));
    }

    @Override
    @DeleteMapping("/deletebmibyuserid")
    public ResponseEntity<BMIDto> deleteBMIByUserId(@RequestParam Long userId, @Valid @RequestBody BMIDto bmiDto) {
        return ResponseEntity.ok(this.authService.deleteBMIByUserId(userId, bmiDto));
    }

    @Override
    @DeleteMapping("/deleteallbyuserid")
    public ResponseEntity<BMIDto> deleteAllByUserId(@RequestParam Long userId, @Valid @RequestBody BMIDto bmiDto) {
        return ResponseEntity.ok(this.authService.deleteAllByUserId(userId, bmiDto));
    }

    @Override
    @GetMapping("/getallbmis")
    public ResponseEntity<List<BMIDto>> getAllBMIs() {
        return ResponseEntity.ok(this.authService.getAllBMIs());
    }

    @Override
    @GetMapping("/getbmibyid")
    public ResponseEntity<BMIDto> getBMIById(@RequestParam Long id) {
        return ResponseEntity.ok(this.authService.getBMIById(id));
    }

    @Override
    @PutMapping("/updatebmi")
    public ResponseEntity<BMIDto> updateBMI(@RequestParam Long id, @Valid @RequestBody BMIDto bmiDto) {
        return ResponseEntity.ok(this.authService.updateBMI(id,bmiDto));
    }
}
