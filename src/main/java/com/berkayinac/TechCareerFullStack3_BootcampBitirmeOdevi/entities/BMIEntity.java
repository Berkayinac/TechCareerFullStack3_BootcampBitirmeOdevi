package com.berkayinac.TechCareerFullStack3_BootcampBitirmeOdevi.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Builder
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "BodyMassIndexes")
public class BMIEntity extends BaseEntity implements Serializable {
    // Serileştirme
    public static final Long serialVersionUID=1L;

    @Column(name = "UserId")
    private Long userId;

    @Column(name = "Weight")
    private double weight;

    @Column(name = "Height")
    private double height;

    @Column(name = "BodyMassIndex")
    private double bodyMassIndex;

    @Column(name = "Result")
    private String result;
}
