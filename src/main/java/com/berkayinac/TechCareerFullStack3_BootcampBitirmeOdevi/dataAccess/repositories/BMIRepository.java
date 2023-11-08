package com.berkayinac.TechCareerFullStack3_BootcampBitirmeOdevi.dataAccess.repositories;

import com.berkayinac.TechCareerFullStack3_BootcampBitirmeOdevi.entities.BMIEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BMIRepository extends JpaRepository<BMIEntity,Long> {
}
