package org.supremecorp.hospitalqueuemanagement.services.base;

import java.io.IOException;
import java.util.List;

public interface CrudService<T, ID> {
    List<T> listAll();
    T save(T t) throws IOException;
    T findById(ID id);
    void deleteById(ID id);
    void delete(T t);
}
