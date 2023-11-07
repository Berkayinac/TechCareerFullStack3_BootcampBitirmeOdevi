package com.berkayinac.TechCareerFullStack3_BootcampBitirmeOdevi.business.rules;

import com.berkayinac.TechCareerFullStack3_BootcampBitirmeOdevi.core.bean.PasswordEncoderBeanClass;
import com.berkayinac.TechCareerFullStack3_BootcampBitirmeOdevi.core.exception.BusinessRulesException;
import com.berkayinac.TechCareerFullStack3_BootcampBitirmeOdevi.dataAccess.repositories.UserRepository;
import com.berkayinac.TechCareerFullStack3_BootcampBitirmeOdevi.entities.User;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class UserBusinessRules {

    private UserRepository userRepository;
    private final PasswordEncoderBeanClass passwordEncoderBeanClass;

// Sistemde kayıtlı kullanıcılar bulunamadıysa mesaj göndersin.
    public void checkUsersNotExists(List<User> userList){
        String usersNotExistsMessage = "Sistemde kayıtlı kullanıcı bulunamadı.";
        if(userList.isEmpty()){
            throw new BusinessRulesException(usersNotExistsMessage);
        }
    }


    //kullanıcı yoksa kullanıcı yok mesajı döndür.
    public void checkIfUserExists(Long id){
        String userExistsMessage = "Kullanıcı bulunamadı.";
        var userV1 = this.userRepository.existsById(id);
        if(!userV1){
            throw new BusinessRulesException(userExistsMessage);
        }
    }

    //kullanıcı yoksa kullanıcı yok mesajı döndür.
    public void checkIfUserExists(String email){
        String userExistsMessage = "Kullanıcı zaten kayıtlı.";
        var userV1 = this.userRepository.existsUserByEmail(email);
        if(userV1){
            throw new BusinessRulesException(userExistsMessage);
        }
    }

    public void checkIfUserNotExists(String email){
        String userExistsMessage = "Email veya şifre yanlış";
        var userV1 = this.userRepository.existsUserByEmail(email);
        if(!userV1){
            throw new BusinessRulesException(userExistsMessage);
        }
    }

    public void passwordMatch(String password, String encodedPassword){
        String passwordMatchMessage = "Email veya şifre yanlış.";
        var result = this.passwordEncoderBeanClass.passwordEncoderMethod().matches(password,encodedPassword);
        if(!result){
            throw new BusinessRulesException(passwordMatchMessage);
        }
    }

}
