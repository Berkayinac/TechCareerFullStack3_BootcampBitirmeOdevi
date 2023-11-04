package com.berkayinac.TechCareerFullStack3_BootcampBitirmeOdevi.business.dtos;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    public static final Long serialVersionUID=1L;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Date registrationDay;
    private boolean status;


}
