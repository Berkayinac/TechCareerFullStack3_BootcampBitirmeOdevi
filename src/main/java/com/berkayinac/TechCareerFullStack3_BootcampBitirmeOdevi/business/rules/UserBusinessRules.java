package com.berkayinac.TechCareerFullStack3_BootcampBitirmeOdevi.business.rules;

import com.berkayinac.TechCareerFullStack3_BootcampBitirmeOdevi.core.exception.BusinessRulesException;
import com.berkayinac.TechCareerFullStack3_BootcampBitirmeOdevi.dataAccess.repositories.UserRepository;
import com.berkayinac.TechCareerFullStack3_BootcampBitirmeOdevi.entities.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class UserBusinessRules {

    private UserRepository userRepository;

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

}
