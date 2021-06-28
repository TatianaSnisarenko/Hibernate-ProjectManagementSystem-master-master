package project_managment_system.dao.repositories.one_entity_repositories;

import org.hibernate.SessionFactory;
import project_managment_system.dao.entity.DeveloperDao;

public class DeveloperRepository extends UniversalRepository<DeveloperDao> {
    public DeveloperRepository(SessionFactory sessionFactory) {
        super(sessionFactory, DeveloperDao.class);
    }
}
