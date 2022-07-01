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

    @ManyToOne
    private Hospital hospital;

    public Unit(String id, String name) {
        super(id);
        this.name = name;
    }
}
