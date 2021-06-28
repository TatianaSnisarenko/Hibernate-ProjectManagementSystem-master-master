package project_managment_system.dao.repositories.one_entity_repositories;

import java.util.Set;

public interface Repository<T> {

    T findById(int id);

    void create(T entity);

    void update(T entity);

    void deleteById(int id);

    void deleteByObject(T entity);

    Set<T> findAll();
}
