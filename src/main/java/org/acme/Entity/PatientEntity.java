package org.acme.Entity;

import io.smallrye.common.constraint.NotNull;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.acme.Validator.ValidBloodType;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
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
    private String sex;
    @NotNull
    private int age;
    private float weight;
}
