package com.berkayinac.TechCareerFullStack3_BootcampBitirmeOdevi.business.dtos;

import com.berkayinac.TechCareerFullStack3_BootcampBitirmeOdevi.core.annotation.AnnotationUniqueEmailAddress;
import com.berkayinac.TechCareerFullStack3_BootcampBitirmeOdevi.core.audit.AuditingAwareBaseDto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserLoginDto extends AuditingAwareBaseDto implements Serializable {
    public static final Long serialVersionUID=1L;
    @Email
    @NotEmpty(message = "{user.email.validation.constraints.NotNull.message}")
    private String email;

    @NotEmpty(message = "{user.password.validation.constraints.NotNull.message}")
    private String password;
}
