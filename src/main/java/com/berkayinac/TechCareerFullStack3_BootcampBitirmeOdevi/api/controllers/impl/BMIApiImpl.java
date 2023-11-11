package com.berkayinac.TechCareerFullStack3_BootcampBitirmeOdevi.api.controllers.impl;

import com.berkayinac.TechCareerFullStack3_BootcampBitirmeOdevi.api.controllers.IBMIApi;
import com.berkayinac.TechCareerFullStack3_BootcampBitirmeOdevi.business.dtos.BMIDto;
import com.berkayinac.TechCareerFullStack3_BootcampBitirmeOdevi.business.services.BMIService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/bmi/api/v1.0.0")
public class BMIApiImpl implements IBMIApi<BMIDto> {

    private BMIService bmiService;

    @Override
    @GetMapping("/getall")
    public ResponseEntity<List<BMIDto>> getAll() {
        return ResponseEntity.ok().body(this.bmiService.getAll());
    }

    @Override
    @GetMapping("/getbyid")
    public ResponseEntity<BMIDto> getById(@RequestParam Long id) {
        return ResponseEntity.ok().body((BMIDto) this.bmiService.getById(id));
    }

    @Override
    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid @RequestBody BMIDto bmiDto) {
        return ResponseEntity.ok(this.bmiService.add(bmiDto));
    }

    @Override
    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@Valid @RequestBody BMIDto bmiDto) {
        return ResponseEntity.ok(this.bmiService.delete(bmiDto));
    }

    @Override
    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestParam Long id, @Valid @RequestBody BMIDto bmiDto) {
        return ResponseEntity.ok(this.bmiService.update(id,bmiDto));
    }

    @Override
    @PostMapping("/calculate")
    public ResponseEntity<?> calculate(@Valid @RequestBody BMIDto bmiDto) {
        return ResponseEntity.ok(this.bmiService.calculate(bmiDto));
    }

    @Override
    @DeleteMapping("/deleteall")
    public ResponseEntity<?> deleteAllByUserId(@Valid @RequestBody BMIDto bmiDto) {
        return ResponseEntity.ok(this.bmiService.deleteAllByUserId(bmiDto));
    }
}
