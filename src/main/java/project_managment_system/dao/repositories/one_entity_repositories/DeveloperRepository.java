package project_managment_system.dao.repositories.one_entity_repositories;

import com.zaxxer.hikari.HikariDataSource;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import project_managment_system.dao.entity.DeveloperDao;

import java.util.*;

public class DeveloperRepository implements Repository<DeveloperDao> {
    private SessionFactory sessionFactory;
    private final static Logger LOG = LoggerFactory.getLogger(DeveloperRepository.class);

    public DeveloperRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void deleteById(int idDeveloper) {
        DeveloperDao developerDaoToDelete = findById(idDeveloper);
        if (Objects.nonNull(developerDaoToDelete)) {
            deleteByObject(developerDaoToDelete);
        }
    }

    @Override
    public void deleteByObject(DeveloperDao developerDao) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.delete(developerDao);
            transaction.commit();
        } catch (Exception e) {
            if (Objects.nonNull(transaction)) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void update(DeveloperDao developerDao) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(developerDao);
            transaction.commit();
        } catch (Exception e) {
            LOG.error("update developerDao. ", e);
            if (Objects.nonNull(transaction)) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void create(DeveloperDao developerDao) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(developerDao);
            transaction.commit();
        } catch (Exception e) {
            LOG.error("save developerDao. ", e);
            if (Objects.nonNull(transaction)) {
                transaction.rollback();
            }
        }
    }

    @Override
    public DeveloperDao findById(int developerId) {
        DeveloperDao developerDao = null;
        try (Session session = sessionFactory.openSession()) {
            developerDao = session.get(DeveloperDao.class, developerId);
        } catch (Exception e) {
            LOG.error("findById developer. ", e);
        }
        return developerDao;
    }

    @Override
    public Set<DeveloperDao> findAll() {
        Set<DeveloperDao> developers = new TreeSet<>();
        try (Session session = sessionFactory.openSession()) {
            developers = new TreeSet<>(session.createQuery("FROM DeveloperDao d", DeveloperDao.class).list());
        } catch (Exception e) {
            LOG.error("findAll developers. ", e);
        }
        return developers;
    }
}
