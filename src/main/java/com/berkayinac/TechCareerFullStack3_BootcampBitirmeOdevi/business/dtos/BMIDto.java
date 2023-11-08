package com.berkayinac.TechCareerFullStack3_BootcampBitirmeOdevi.business.dtos;

import com.berkayinac.TechCareerFullStack3_BootcampBitirmeOdevi.core.audit.AuditingAwareBaseDto;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BMIDto extends AuditingAwareBaseDto implements Serializable {
    // Serileştirme
    public static final Long serialVersionUID=1L;

    @NotNull(message = "{bmi.userid.validation.constraints.NotNull.message}")
    private Long userId;

    @NotNull(message = "{bmi.weight.validation.constraints.NotNull.message}")
    private double weight;

    @NotNull(message = "{bmi.height.validation.constraints.NotNull.message}")
    private double height;

    private double bodyMassIndex;

    private String result;

}
