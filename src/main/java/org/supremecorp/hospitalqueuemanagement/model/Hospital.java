package org.supremecorp.hospitalqueuemanagement.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;

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

    @ElementCollection
    private List<String> texts = new ArrayList<>();

    public Hospital(String name, String location, String contact, String website, String email) {
        this.name = name;
        this.location = location;
        this.contact = contact;
        this.website = website;
        this.email = email;
    }
}
