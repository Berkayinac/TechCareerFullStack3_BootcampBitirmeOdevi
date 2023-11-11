package com.berkayinac.TechCareerFullStack3_BootcampBitirmeOdevi.api.controllers.impl;


import com.berkayinac.TechCareerFullStack3_BootcampBitirmeOdevi.api.controllers.IUserApi;
import com.berkayinac.TechCareerFullStack3_BootcampBitirmeOdevi.business.dtos.UserLoginDto;
import com.berkayinac.TechCareerFullStack3_BootcampBitirmeOdevi.business.dtos.UserRegisterDto;
import com.berkayinac.TechCareerFullStack3_BootcampBitirmeOdevi.business.services.UserService;
import com.berkayinac.TechCareerFullStack3_BootcampBitirmeOdevi.business.dtos.UserDto;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor

@RestController
@RequestMapping("user/api/v1.0.0")
public class UserApiImpl implements IUserApi<UserDto> {

    private UserService userService;

    @Override
    @GetMapping("/getall")
    public ResponseEntity<List<UserDto>> userApiGetAll() {
        return ResponseEntity.ok().body(this.userService.getAll());
    }

    @Override
    @GetMapping("/getAllByStatus")
    public ResponseEntity<List<UserDto>> userApiGetAllByStatus(@RequestParam(name = "status") boolean status) {
        return ResponseEntity.ok().body(this.userService.getAllByStatus(status));
    }

    @Override
    @GetMapping("/getById")
    public ResponseEntity<UserDto> userApiGetById(@RequestParam(name = "Id") Long id) {
        return ResponseEntity.ok().body((UserDto) this.userService.getById(id));
    }

    @Override
    @PostMapping("/create")
    public ResponseEntity<?> userApiCreate(@Valid @RequestBody UserDto userDto) {
        return ResponseEntity.ok(this.userService.add(userDto));
    }

    @Override
    @DeleteMapping("/delete")
    public ResponseEntity<?> userApiDelete(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(this.userService.delete(userDto));
    }

    @Override
    @PutMapping("/update")
    public ResponseEntity<?> userApiUpdate(@RequestParam(name = "Id") Long id, @Valid @RequestBody UserDto userDto) {
        return ResponseEntity.ok(this.userService.update(id,userDto));
    }

    @Override
    @PostMapping("/register")
    public ResponseEntity<?> userApiRegister(@Valid @RequestBody UserRegisterDto userRegisterDto) {
        return ResponseEntity.ok(this.userService.register(userRegisterDto));
    }

    @Override
    @PostMapping("/login")
    public ResponseEntity<?> userApiLogin(@Valid @RequestBody UserLoginDto userLoginDto) {
        return ResponseEntity.ok(this.userService.login(userLoginDto));
    }
}
