package org.supremecorp.hospitalqueuemanagement.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

@Entity
@Setter
@Getter
public class Appointment extends BaseEntity{
    private int identifier;
    private String patientName;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @ManyToOne
    private Unit unit;
}
