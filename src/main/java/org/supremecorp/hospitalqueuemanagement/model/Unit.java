package org.supremecorp.hospitalqueuemanagement.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Unit extends BaseEntity {
    private String name;
    private String description;
    @ManyToOne
    private Hospital hospital;

    public Unit(String name, String description, Hospital hospital) {
        this.name = name;
        this.description = description;
        this.hospital = hospital;
    }
}
