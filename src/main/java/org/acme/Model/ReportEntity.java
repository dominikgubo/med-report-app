package org.acme.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.List;

@Entity
public class ReportEntity {
    @Id
    private Long id;
    private String name;
    private List<MedicalParameter> parametersList;
}
