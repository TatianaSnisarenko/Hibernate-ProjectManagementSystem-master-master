package project_managment_system.dao.repositories.one_entity_repositories;

import org.hibernate.SessionFactory;
import project_managment_system.dao.entity.ProjectDao;

public class ProjectRepository extends UniversalRepository<ProjectDao> {

    public ProjectRepository(SessionFactory sessionFactory) {
        super(sessionFactory, ProjectDao.class);
    }
}
