package project_managment_system.dao.repositories.one_entity_repositories;

import org.hibernate.SessionFactory;
import project_managment_system.dao.entity.CustomerDao;

public class CustomerRepository extends UniversalRepository<CustomerDao> {

    public CustomerRepository(SessionFactory sessionFactory) {
        super(sessionFactory, CustomerDao.class);
    }
}
