package org.med.Entity;

import io.smallrye.common.constraint.NotNull;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.med.Validator.ValidBloodType;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "patient")
public class PatientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @ValidBloodType
    private String bloodType;
    @NotNull
    private char sex;
    @NotNull
    private int age;

    private float weight;
}
