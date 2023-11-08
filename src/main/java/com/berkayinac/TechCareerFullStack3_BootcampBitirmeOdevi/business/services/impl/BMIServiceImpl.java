package com.berkayinac.TechCareerFullStack3_BootcampBitirmeOdevi.business.services.impl;

import com.berkayinac.TechCareerFullStack3_BootcampBitirmeOdevi.business.dtos.BMIDto;
import com.berkayinac.TechCareerFullStack3_BootcampBitirmeOdevi.business.rules.BMIBusinessRules;
import com.berkayinac.TechCareerFullStack3_BootcampBitirmeOdevi.business.services.BMIService;
import com.berkayinac.TechCareerFullStack3_BootcampBitirmeOdevi.core.bean.ModelMapperBeanClass;
import com.berkayinac.TechCareerFullStack3_BootcampBitirmeOdevi.dataAccess.repositories.BMIRepository;
import com.berkayinac.TechCareerFullStack3_BootcampBitirmeOdevi.entities.BMIEntity;
import com.berkayinac.TechCareerFullStack3_BootcampBitirmeOdevi.entities.enums.BMIResults;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class BMIServiceImpl implements BMIService<BMIDto, BMIEntity> {

    private BMIRepository bmiRepository;
    private ModelMapperBeanClass modelMapperBeanClass;
    private BMIBusinessRules bmiBusinessRules;

    @Override
    public BMIDto entityToDto(BMIEntity bmiEntity) {
        return modelMapperBeanClass.modelMapperMethod().map(bmiEntity,BMIDto.class);
    }

    @Override
    public BMIEntity dtoToEntity(BMIDto bmiDto) {
        return modelMapperBeanClass.modelMapperMethod().map(bmiDto,BMIEntity.class);
    }

    @Override
    public List<BMIDto> getAll() {
        var bmiEntityList = this.bmiRepository.findAll();

        this.bmiBusinessRules.checkBMIsNotExists(bmiEntityList);

        List<BMIDto> bmiDtoList = new ArrayList<>();
        bmiEntityList.forEach(bmiEntity -> bmiDtoList.add(entityToDto(bmiEntity)));

        return bmiDtoList;
    }

    @Override
    public BMIDto getById(Long id) {
        this.bmiBusinessRules.checkIfBMIExists(id);
        var bmiEntity = this.bmiRepository.findById(id).get();
        var bmiDto = entityToDto(bmiEntity);

        return bmiDto;
    }

    @Override
    @Transactional
    public BMIDto add(BMIDto bmiDto) {
        this.bmiRepository.save(this.dtoToEntity(bmiDto));
        return bmiDto;
    }

    @Override
    @Transactional
    public BMIDto delete(BMIDto bmiDto) {
        this.bmiRepository.delete(this.dtoToEntity(bmiDto));
        return bmiDto;
    }

    @Override
    @Transactional
    public BMIDto update(Long id,BMIDto bmiDto) {
        var currentBMIDto = this.getById(id);
        currentBMIDto.setHeight(bmiDto.getHeight());
        currentBMIDto.setWeight(bmiDto.getWeight());
        currentBMIDto.setBodyMassIndex(bmiDto.getBodyMassIndex());
        currentBMIDto.setResult(bmiDto.getResult());
        this.bmiRepository.save(dtoToEntity(currentBMIDto));
        return currentBMIDto;
    }

    @Override
    @Transactional
    public BMIDto calculate(BMIDto bmiDto) {
        var calculateBMI = bmiDto.getWeight() / Math.pow(bmiDto.getHeight(), 2);
        bmiDto.setBodyMassIndex(calculateBMI);
        if(bmiDto.getBodyMassIndex() < 18.5){
            bmiDto.setResult(BMIResults.ZAYIF.toString());
        }

        else if(bmiDto.getBodyMassIndex() >= 18.5 && bmiDto.getBodyMassIndex() < 24.9){
            bmiDto.setResult(BMIResults.NORMAL.toString());
        }

        else if(bmiDto.getBodyMassIndex() >= 25 && bmiDto.getBodyMassIndex() < 29.9){
            bmiDto.setResult(BMIResults.FAZLAKILOLU.toString());
        }

        else if(bmiDto.getBodyMassIndex() >= 30 && bmiDto.getBodyMassIndex() < 34.9){
            bmiDto.setResult(BMIResults.BIRINCIDERECEOBEZITE.toString());
        }

        else if(bmiDto.getBodyMassIndex() >= 35 && bmiDto.getBodyMassIndex() < 39.9){
            bmiDto.setResult(BMIResults.IKINCIDERECEOBEZITE.toString());
        }

        else if(bmiDto.getBodyMassIndex() >= 40){
            bmiDto.setResult(BMIResults.UCUNCUDERECEOBEZITE.toString());
        }
        else{
            bmiDto.setResult(BMIResults.HATALIISLEM.toString());
        }

        this.add(bmiDto);
        return bmiDto;
    }
}
