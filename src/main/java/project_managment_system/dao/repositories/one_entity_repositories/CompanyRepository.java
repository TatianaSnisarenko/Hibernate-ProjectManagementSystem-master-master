package project_managment_system.dao.repositories.one_entity_repositories;

import org.hibernate.SessionFactory;
import project_managment_system.dao.entity.CompanyDao;

public class CompanyRepository extends UniversalRepository<CompanyDao> {

    public CompanyRepository(SessionFactory sessionFactory) {
        super(sessionFactory, CompanyDao.class);

    }
}
