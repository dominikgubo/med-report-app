package org.med.Entity;

import io.smallrye.common.constraint.NotNull;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "medical_parameter")
public class MedicalParameterEntity {
    @Id
    private String parameterName;
    @ManyToOne
    @JoinColumn(name = "report_id")
    @NotNull
    private ReportEntity reportId;
    @NotNull @NotEmpty
    private String parameterValue;
    private String referenceValueRange;

}
