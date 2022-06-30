package org.supremecorp.hospitalqueuemanagement.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Setter
@Getter
public class Hospital extends BaseEntity{
    private String name;
    private String location;
    private String contact;
    private String website;
    private String email;
}
