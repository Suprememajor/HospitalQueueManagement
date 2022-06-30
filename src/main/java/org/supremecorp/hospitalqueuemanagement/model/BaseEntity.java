package org.supremecorp.hospitalqueuemanagement.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.LocalDate;

@MappedSuperclass
@Getter
@Setter
public class BaseEntity implements Serializable {
    @Id
    private String id;

    private LocalDate localDate = LocalDate.now();
}