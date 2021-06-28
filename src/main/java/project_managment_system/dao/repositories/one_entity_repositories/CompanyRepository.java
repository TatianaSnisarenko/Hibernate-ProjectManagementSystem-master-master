package project_managment_system.dao.repositories.one_entity_repositories;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import project_managment_system.dao.entity.CompanyDao;
import project_managment_system.dao.entity.ProjectDao;

import java.util.*;

public class CompanyRepository implements Repository<CompanyDao> {
    private SessionFactory sessionFactory;
    private final static Logger LOG = LoggerFactory.getLogger(CompanyRepository.class);

    public CompanyRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void deleteById(int idCompany) {
        CompanyDao companyDaoToDelete = findById(idCompany);
        if (Objects.nonNull(companyDaoToDelete)) {
            deleteByObject(companyDaoToDelete);
        }
    }

    @Override
    public void deleteByObject(CompanyDao companyDao) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.delete(companyDao);
            transaction.commit();
        } catch (Exception e) {
            LOG.error("deleteByObject. ", e);
            if (Objects.nonNull(transaction)) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void update(CompanyDao companyDao) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(companyDao);
            transaction.commit();
        } catch (Exception e) {
            LOG.error("update companyDao. ", e);
            if (Objects.nonNull(transaction)) {
                transaction.rollback();
            }
        }

    }

    @Override
    public void create(CompanyDao companyDao) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(companyDao);
            transaction.commit();
        } catch (Exception e) {
            LOG.error("save companyDao. ", e);
            if (Objects.nonNull(transaction)) {
                transaction.rollback();
            }
        }
    }

    @Override
    public CompanyDao findById(int companyId) {
        CompanyDao companyDao = null;
        try (Session session = sessionFactory.openSession()) {
            companyDao = session.get(CompanyDao.class, companyId);
        } catch (Exception e) {
            LOG.error("findById company. ", e);
        }
        return companyDao;
    }

    @Override
    public Set<CompanyDao> findAll() {
        Set<CompanyDao> companies = new TreeSet<>();
        try (Session session = sessionFactory.openSession()) {
            companies = new TreeSet<>(session.createQuery("FROM CompanyDao c", CompanyDao.class).list());
        } catch (Exception e) {
            LOG.error("findAll companies. ", e);
        }
        return companies;
    }
}
