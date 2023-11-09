package com.berkayinac.TechCareerFullStack3_BootcampBitirmeOdevi.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Builder
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User extends BaseEntity implements Serializable {
    // Serileştirme
    public static final Long serialVersionUID=1L;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name="email")
    private String email;

    @Column(name="password")
    private String password;

    @Column(name="registration_day")
    private Date registrationDay;

    @Column(name = "status")
    private boolean status;

    @Column(name = "role")
    private String role;

    @OneToMany(mappedBy = "user")
    private List<BMIEntity> bmiEntityList;

}
