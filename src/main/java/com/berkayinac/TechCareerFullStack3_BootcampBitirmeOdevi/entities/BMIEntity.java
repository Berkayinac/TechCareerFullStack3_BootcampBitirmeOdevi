package com.berkayinac.TechCareerFullStack3_BootcampBitirmeOdevi.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Builder
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "body_mass_indexes")
public class BMIEntity extends BaseEntity implements Serializable {
    // Serileştirme
    public static final Long serialVersionUID=1L;

    @Column(name = "weight")
    private double weight;

    @Column(name = "height")
    private double height;

    @Column(name = "bodyMassIndex")
    private double bodyMassIndex;

    @Column(name = "result")
    private String result;

    @ManyToOne()
    @JoinColumn(name="user_id")
    private User user;

}
