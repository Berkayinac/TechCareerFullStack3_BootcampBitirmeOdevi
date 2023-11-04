package com.berkayinac.TechCareerFullStack3_BootcampBitirmeOdevi.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Builder
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Users")
public class User extends BaseEntity implements Serializable {
    // Serileştirme
    public static final Long serialVersionUID=1L;

    @Column(name = "FirstName")
    private String firstName;

    @Column(name = "LastName")
    private String lastName;

    @Column(name="Email")
    private String email;

    @Column(name="Password")
    private String password;

    @Column(name="RegistrationDay")
    private Date registrationDay;

    @Column(name = "status")
    private boolean status;

}
