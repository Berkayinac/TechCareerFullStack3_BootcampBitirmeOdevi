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

        var result = bmiDto.getResult();

        while (result == null){
            result = calculateBmiZayif(bmiDto.getBodyMassIndex(),result);
            result = calculateBmiNormal(bmiDto.getBodyMassIndex(),result);
            result = calculateBmiFazlakilolu(bmiDto.getBodyMassIndex(),result);
            result = calculateBmiBirinciDereceObezite(bmiDto.getBodyMassIndex(),result);
            result = calculateBmiIkinciDereceObezite(bmiDto.getBodyMassIndex(),result);
            result = calculateBmiUcuncuDereceObezite(bmiDto.getBodyMassIndex(),result);
        }
        bmiDto.setResult(result);

        return this.add(bmiDto);
    }

    @Override
    public BMIDto deleteAllByUserId(BMIDto bmiDto) {
        var list = this.bmiRepository.findAllByUserId(bmiDto.getUserId());
        this.bmiRepository.deleteAll(list);
        return bmiDto;
    }

    private String calculateBmiZayif(double bodyMassIndex, String result){
        if(bodyMassIndex < 18.5){
            result = BMIResults.ZAYIF.toString();
        }
        return result;
    }

    private String calculateBmiNormal(double bodyMassIndex, String result){
        if(bodyMassIndex >= 18.5 && bodyMassIndex < 24.9){
            result = BMIResults.NORMAL.toString();
        }
        return result;
    }

    private String calculateBmiFazlakilolu(double bodyMassIndex, String result){
        if(bodyMassIndex >= 25 && bodyMassIndex <  29.9){
            result = BMIResults.NORMAL.toString();
        }
        return result;
    }

    private String calculateBmiBirinciDereceObezite(double bodyMassIndex, String result){
        if(bodyMassIndex >= 30 && bodyMassIndex < 34.9){
            result = BMIResults.BIRINCIDERECEOBEZITE.toString();
        }
        return result;
    }

    private String calculateBmiIkinciDereceObezite(double bodyMassIndex, String result){
        if(bodyMassIndex >= 35 && bodyMassIndex < 39.9){
            result = BMIResults.IKINCIDERECEOBEZITE.toString();
        }
        return result;
    }

    private String calculateBmiUcuncuDereceObezite(double bodyMassIndex, String result){
        if(bodyMassIndex >= 40){
            result = BMIResults.UCUNCUDERECEOBEZITE.toString();
        }
        return result;
    }
}
