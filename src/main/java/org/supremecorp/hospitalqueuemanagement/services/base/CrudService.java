package org.supremecorp.hospitalqueuemanagement.services.base;

import java.util.List;

public interface CrudService<T, ID> {
    List<T> findAll();
    T save(T t);
    T findById(ID id);
    void deleteByID(ID id);
    void delete(T t);
}
