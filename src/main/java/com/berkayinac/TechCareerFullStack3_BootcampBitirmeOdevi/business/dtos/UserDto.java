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
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto extends AuditingAwareBaseDto implements Serializable {
    public static final Long serialVersionUID=1L;

    @NotEmpty(message = "{user.firstname.validation.constraints.NotNull.message}")
    private String firstName;

    @NotEmpty(message = "{user.lastname.validation.constraints.NotNull.message}")
    private String lastName;

    @Email
    @NotEmpty(message = "{user.email.validation.constraints.NotNull.message}")
    @AnnotationUniqueEmailAddress
    private String email;

    @NotEmpty(message = "{user.password.validation.constraints.NotNull.message}")
    private String password;


    private Date registrationDay;

    @Builder.Default
    private boolean status = false;


}
