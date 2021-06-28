package project_managment_system.dao.repositories.one_entity_repositories;

import org.hibernate.SessionFactory;
import project_managment_system.dao.entity.SkillDao;

public class SkillRepository extends UniversalRepository<SkillDao> {

    public SkillRepository(SessionFactory sessionFactory) {
        super(sessionFactory, SkillDao.class);
    }
}
