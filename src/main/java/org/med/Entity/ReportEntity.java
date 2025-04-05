package org.med.Entity;

import io.smallrye.common.constraint.NotNull;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "report")
public class ReportEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @ManyToOne
    private PatientEntity patient;
    @OneToMany(fetch = FetchType.EAGER)
    private List<MedicalParameterEntity> medicalParameterList;
}
