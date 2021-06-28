package project_managment_system.dao.repositories.one_entity_repositories;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import project_managment_system.dao.entity.ProjectDao;

import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class ProjectRepository implements Repository<ProjectDao> {
    private SessionFactory sessionFactory;
    private final static Logger LOG = LoggerFactory.getLogger(ProjectRepository.class);

    public ProjectRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void deleteById(int idProject) {
        ProjectDao projectDaoToDelete = findById(idProject);
        if (Objects.nonNull(projectDaoToDelete)) {
            deleteByObject(projectDaoToDelete);
        }
    }

    @Override
    public void deleteByObject(ProjectDao projectDao) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.delete(projectDao);
            transaction.commit();
        } catch (Exception e) {
            LOG.error("deleteByObject. ", e);
            if (Objects.nonNull(transaction)) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void update(ProjectDao projectDao) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(projectDao);
            transaction.commit();
        } catch (Exception e) {
            LOG.error("update projectDao. ", e);
            if (Objects.nonNull(transaction)) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void create(ProjectDao projectDao) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(projectDao);
            transaction.commit();
        } catch (Exception e) {
            LOG.error("save projectDao. ", e);
            if (Objects.nonNull(transaction)) {
                transaction.rollback();
            }
        }
    }

    @Override
    public ProjectDao findById(int projectId) {
        ProjectDao projectDao = null;
        try (Session session = sessionFactory.openSession()) {
            projectDao = session.get(ProjectDao.class, projectId);
        } catch (Exception e) {
            LOG.error("findById project. ", e);
        }
        return projectDao;
    }

    @Override
    public Set<ProjectDao> findAll() {
        Set<ProjectDao> projects = new TreeSet<>();
        try (Session session = sessionFactory.openSession()) {
            projects = new TreeSet<>(session.createQuery("FROM ProjectDao p", ProjectDao.class).list());
        } catch (Exception e) {
            LOG.error("findAll projects. ", e);
        }
        return projects;
    }
}
