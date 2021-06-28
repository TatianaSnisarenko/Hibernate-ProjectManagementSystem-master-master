package project_managment_system.dao.repositories.one_entity_repositories;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import project_managment_system.dao.entity.CustomerDao;

import java.util.*;

public class CustomerRepository implements Repository<CustomerDao> {
    private SessionFactory sessionFactory;

    private final static Logger LOG = LoggerFactory.getLogger(CustomerRepository.class);

    public CustomerRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void deleteById(int idCustomer) {
        CustomerDao customerDaoToDelete = findById(idCustomer);
        if(Objects.nonNull(customerDaoToDelete)){
            deleteByObject(customerDaoToDelete);
        }
    }

    @Override
    public void deleteByObject(CustomerDao customerDao) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.delete(customerDao);
            transaction.commit();
        } catch (Exception e) {
            LOG.error("deleteByObject. ", e);
            if (Objects.nonNull(transaction)) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void update(CustomerDao customerDao) {
        Transaction transaction = null;
        try(Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(customerDao);
            transaction.commit();
        }catch (Exception e){
            LOG.error("update customerDao. ", e);
            if(Objects.nonNull(transaction)){
                transaction.rollback();
            }
        }
    }

    @Override
    public void create(CustomerDao customerDao) {
        Transaction transaction = null;
        try(Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(customerDao);
            transaction.commit();
        }catch (Exception e){
            LOG.error("save customerDao. ", e);
            if(Objects.nonNull(transaction)){
                transaction.rollback();
            }
        }
    }

    @Override
    public CustomerDao findById(int customerId) {
        CustomerDao customerDao = null;
        try (Session session = sessionFactory.openSession()) {
            customerDao = session.get(CustomerDao.class, customerId);
        } catch (Exception e) {
            LOG.error("findById customer. ", e);
        }
        return customerDao;
    }

    @Override
    public Set<CustomerDao> findAll() {
        Set<CustomerDao> customers = new TreeSet<>();
        try (Session session = sessionFactory.openSession()) {
            customers = new TreeSet<>(session.createQuery("FROM CustomerDao c", CustomerDao.class).list());
        } catch (Exception e) {
            LOG.error("findAll customers. ", e);
        }
        return customers;
    }
}
