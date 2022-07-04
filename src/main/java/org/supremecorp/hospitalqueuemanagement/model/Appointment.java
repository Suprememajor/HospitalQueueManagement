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
    private String identifier = String.valueOf(System.currentTimeMillis());
    private String patientName;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String time;
    private String date;
    private boolean confirmed = false;

    @ManyToOne
    private Unit unit;
}
