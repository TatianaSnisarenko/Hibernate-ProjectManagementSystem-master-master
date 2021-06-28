package project_managment_system.dao.repositories.one_entity_repositories;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import project_managment_system.dao.entity.SkillDao;
import project_managment_system.service.converters.SkillConverter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class SkillRepository implements Repository<SkillDao> {
    private SessionFactory sessionFactory;
    private final static Logger LOG = LoggerFactory.getLogger(SkillRepository.class);

    public SkillRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void deleteById(int idSkill) {
        SkillDao skillDaoToDelete = findById(idSkill);
        if (Objects.nonNull(skillDaoToDelete)) {
            deleteByObject(skillDaoToDelete);
        }
    }

    @Override
    public void deleteByObject(SkillDao skillDao) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.delete(skillDao);
            transaction.commit();
        } catch (Exception e) {
            LOG.error("deleteByObject. ", e);
            if (Objects.nonNull(transaction)) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void update(SkillDao skillDao) {
        Transaction transaction = null;
        try(Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(skillDao);
            transaction.commit();
        }catch (Exception e){
            LOG.error("update skillDao. ", e);
            if(Objects.nonNull(transaction)){
                transaction.rollback();
            }
        }
    }

    @Override
    public void create(SkillDao skillDao) {
        Transaction transaction = null;
        try(Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(skillDao);
            transaction.commit();
        }catch (Exception e){
            LOG.error("save skillDao. ", e);
            if(Objects.nonNull(transaction)){
                transaction.rollback();
            }
        }
    }

    @Override
    public SkillDao findById(int skillId) {
        SkillDao skillDao = null;
        try (Session session = sessionFactory.openSession()) {
            skillDao = session.get(SkillDao.class, skillId);
        } catch (Exception e) {
            LOG.error("findById skill. ", e);
        }
        return skillDao;
    }

    @Override
    public Set<SkillDao> findAll() {
        Set<SkillDao> skills = new TreeSet<>();
        try (Session session = sessionFactory.openSession()) {
            skills = new TreeSet<>(session.createQuery("FROM SkillDao s", SkillDao.class).list());
        } catch (Exception e) {
            LOG.error("findAll skills. ", e);
        }
        return skills;
    }

    public Set<SkillDao> allSkillsList() {
        Set<SkillDao> allSkillsList = new TreeSet<>();
        SkillDao javaJunior = new SkillDao();
        javaJunior.setLanguage("Java");
        javaJunior.setLevel("Junior");
        allSkillsList.add(javaJunior);
        SkillDao javaMiddle = new SkillDao();
        javaMiddle.setLanguage("Java");
        javaMiddle.setLevel("Middle");
        allSkillsList.add(javaMiddle);
        SkillDao javaSenior = new SkillDao();
        javaSenior.setLanguage("Java");
        javaSenior.setLevel("Senior");
        allSkillsList.add(javaSenior);
        SkillDao cPluPlusJunior = new SkillDao();
        cPluPlusJunior.setLanguage("C++");
        cPluPlusJunior.setLevel("Junior");
        allSkillsList.add(cPluPlusJunior);
        SkillDao cPluPlusMiddle = new SkillDao();
        cPluPlusMiddle.setLanguage("C++");
        cPluPlusMiddle.setLevel("Middle");
        allSkillsList.add(cPluPlusMiddle);
        SkillDao cPluPlusSenior = new SkillDao();
        cPluPlusSenior.setLanguage("C++");
        cPluPlusSenior.setLevel("Senior");
        allSkillsList.add(cPluPlusSenior);
        SkillDao cSharpJunior = new SkillDao();
        cSharpJunior.setLanguage("C#");
        cSharpJunior.setLevel("Junior");
        allSkillsList.add(cSharpJunior);
        SkillDao cSharpMiddle = new SkillDao();
        cSharpMiddle.setLanguage("C#");
        cSharpMiddle.setLevel("Middle");
        allSkillsList.add(cSharpMiddle);
        SkillDao cSharpSenior = new SkillDao();
        cSharpSenior.setLanguage("C#");
        cSharpSenior.setLevel("Senior");
        allSkillsList.add(cSharpSenior);
        SkillDao jsJunior = new SkillDao();
        jsJunior.setLanguage("JS");
        jsJunior.setLevel("Junior");
        allSkillsList.add(jsJunior);
        SkillDao jsMiddle = new SkillDao();
        jsMiddle.setLanguage("JS");
        jsMiddle.setLevel("Middle");
        allSkillsList.add(jsMiddle);
        SkillDao jsSenior = new SkillDao();
        jsSenior.setLanguage("JS");
        jsSenior.setLevel("Senior");
        allSkillsList.add(jsSenior);
        allSkillsList.forEach(this::create);
        return allSkillsList;
    }
}
