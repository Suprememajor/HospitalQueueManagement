package org.supremecorp.hospitalqueuemanagement.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Setter
@Getter
public class Admin extends BaseEntity{
    private String email;
    private String password;

    @ManyToOne
    private Hospital hospital;
}
