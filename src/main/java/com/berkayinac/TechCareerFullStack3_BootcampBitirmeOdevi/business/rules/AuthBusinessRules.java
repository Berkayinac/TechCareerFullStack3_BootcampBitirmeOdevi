package com.berkayinac.TechCareerFullStack3_BootcampBitirmeOdevi.business.rules;

import com.berkayinac.TechCareerFullStack3_BootcampBitirmeOdevi.core.exception.BusinessRulesException;
import com.berkayinac.TechCareerFullStack3_BootcampBitirmeOdevi.entities.User;
import com.berkayinac.TechCareerFullStack3_BootcampBitirmeOdevi.entities.enums.UserRoles;
import org.springframework.stereotype.Service;

@Service
public class AuthBusinessRules {
    public void checkUserLogin(User user) {
        String userLogin = "Lütfen giriş yapınız.";
        if (user == null) {
            throw new BusinessRulesException(userLogin);
        }
    }

    public void checkIfUserRoleUnAuthorized(String role) {
        String userRoleUnAuthorized = "Yetkiniz Yok!";
        if (!role.equals(UserRoles.ADMIN.toString())) {
            throw new BusinessRulesException(userRoleUnAuthorized);
        }
    }

    public void checkIfUsersNotMatch(Long loginUserId, Long userId) {
        String usersNotMatch = "Yetkiniz Yok!";
        if (!loginUserId.equals(userId)) {
            throw new BusinessRulesException(usersNotMatch);
        }
    }
}
