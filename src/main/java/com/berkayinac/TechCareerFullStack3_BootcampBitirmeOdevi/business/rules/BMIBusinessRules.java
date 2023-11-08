package com.berkayinac.TechCareerFullStack3_BootcampBitirmeOdevi.business.rules;

import com.berkayinac.TechCareerFullStack3_BootcampBitirmeOdevi.core.exception.BusinessRulesException;
import com.berkayinac.TechCareerFullStack3_BootcampBitirmeOdevi.dataAccess.repositories.BMIRepository;
import com.berkayinac.TechCareerFullStack3_BootcampBitirmeOdevi.entities.BMIEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;

@AllArgsConstructor
@Service
public class BMIBusinessRules {
    private BMIRepository bmiRepository;

    public void checkBMIsNotExists(List<BMIEntity> bmiEntityList){
        String bmisNotExistsMessage = "Sistemde kayıtlı vücut kitle indeksleri bulunamadı.";
        if(bmiEntityList.isEmpty()){
            throw new BusinessRulesException(bmisNotExistsMessage);
        }
    }

    public void checkIfBMIExists(Long id){
        String bmiExistsMessage = "vücut kitle indeksi bulunamadı.";
        var bmi = this.bmiRepository.existsById(id);
        if(!bmi){
            throw new BusinessRulesException(bmiExistsMessage);
        }
    }
}
