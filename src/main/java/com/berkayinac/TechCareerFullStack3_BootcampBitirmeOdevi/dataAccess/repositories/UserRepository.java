package com.berkayinac.TechCareerFullStack3_BootcampBitirmeOdevi.dataAccess.repositories;

import com.berkayinac.TechCareerFullStack3_BootcampBitirmeOdevi.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsUserByEmail(String email);
    User getUserByEmail(String email);

    //Delivered Query
    List<User> getUsersByStatus(boolean status);
}
