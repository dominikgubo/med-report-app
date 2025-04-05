package org.acme.Entity;

import io.smallrye.common.constraint.NotNull;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "report")
public class ReportEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    private enum sex {
        MALE,
        FEMALE
    };
    @NotNull
    private int age;
    @OneToOne
    private ReportDataEntity data;
}
