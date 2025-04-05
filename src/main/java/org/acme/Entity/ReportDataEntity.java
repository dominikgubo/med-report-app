package org.acme.Entity;

import io.smallrye.common.constraint.NotNull;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashMap;
@Entity
@Table(name = "reportdata")
public class ReportDataEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @NotNull
    String reportName;
    @NotNull
    LocalDate reportTime;
    HashMap<String, String> dataMap;

}
