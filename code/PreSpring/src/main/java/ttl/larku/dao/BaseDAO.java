package ttl.larku.dao;

import java.util.List;
import java.util.Map;

import ttl.larku.domain.Student;

/**
 * GREAT use of generics which allows us
 * to implement that can create different types
 * of DAO that all share the same signatures.
 *
 * JPA, MYSQL, inMemory can all implement student
 * and course BaseDAO.
 * @param <T>
 */
public interface BaseDAO<T> {

    void update(T updateObject);

    void delete(T student);

    T create(T newObject);

    T get(int id);

    List<T> getAll();

}