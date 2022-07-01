package org.supremecorp.hospitalqueuemanagement.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Hospital extends BaseEntity{
    private String name;
    private String location;
    private String contact;
    private String website;
    private String email;

    public Hospital(String name, String location, String contact, String website, String email) {
        this.name = name;
        this.location = location;
        this.contact = contact;
        this.website = website;
        this.email = email;
    }
}
