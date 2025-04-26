package org.med.entity;

import io.smallrye.common.constraint.NotNull;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Optional;

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
    @JoinColumn(name = "patient")
    private PatientEntity patient;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "report")
    private List<MedicalParameterEntity> medicalParameterList;
    private String reportType;
    @Override
    public String toString() {
        return "ReportEntity{" +
                "id=" + id +
                ", patientId=" + patient.getId() +
                ", medicalParameterListSize=" + medicalParameterList.size() +
                ", reportType='" + reportType + '\'' +
                '}';
    }

    public Optional<String> getReportType() {
        return Optional.ofNullable(reportType);
    }
}
