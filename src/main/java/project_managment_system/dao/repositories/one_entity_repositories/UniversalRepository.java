package project_managment_system.dao.repositories.one_entity_repositories;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class UniversalRepository<T> implements Repository<T> {
    private SessionFactory sessionFactory;
    private final static Logger LOG = LoggerFactory.getLogger(UniversalRepository.class);

    private Class<T> clazz;

    public UniversalRepository(SessionFactory sessionFactory, Class<T> clazz) {
        this.sessionFactory = sessionFactory;
        this.clazz = clazz;
    }

    @Override
    public T findById(int id) {
        T entity = null;
        try (Session session = sessionFactory.openSession()) {
            entity = session.get(clazz, id);
        } catch (Exception e) {
            LOG.error("findById . ", e);
        }
        return entity;
    }

    @Override
    public void create(T entity) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(entity);
            transaction.commit();
        } catch (Exception e) {
            LOG.error("save . ", e);
            if (Objects.nonNull(transaction)) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void update(T entity) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(entity);
            transaction.commit();
        } catch (Exception e) {
            LOG.error("update . ", e);
            if (Objects.nonNull(transaction)) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void deleteById(int id) {
        T entityToDelete = findById(id);
        if (Objects.nonNull(entityToDelete)) {
            deleteByObject(entityToDelete);
        }
    }

    @Override
    public void deleteByObject(T entity) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.delete(entity);
            transaction.commit();
        } catch (Exception e) {
            LOG.error("deleteByObject. ", e);
            if (Objects.nonNull(transaction)) {
                transaction.rollback();
            }
        }
    }

    @Override
    public Set<T> findAll() {
        Set<T> entities = new TreeSet<>();
        try (Session session = sessionFactory.openSession()) {
            entities = new TreeSet<>(session.createQuery("from " + clazz.getName(), clazz).list());
        } catch (Exception e) {
            LOG.error("findAll customers. ", e);
        }
        return entities;
    }
}
