package project_managment_system.service.services;

import project_managment_system.dao.entity.SkillDao;
import project_managment_system.dao.repositories.one_entity_repositories.Repository;
import project_managment_system.dto.SkillTo;
import project_managment_system.service.converters.SkillConverter;

import java.util.Set;

public class SkillService {
    private Repository<SkillDao> repository;

    public SkillService(Repository<SkillDao> repository) {
        this.repository = repository;
    }

    public void create(SkillTo skillTo) {
        repository.create(SkillConverter.toSkillDao(skillTo));
    }

    public SkillTo findById(int companyId) {
        return SkillConverter.fromSkillDao(repository.findById(companyId));
    }

    public void update(SkillTo SkillTo) {
        repository.update(SkillConverter.toSkillDao(SkillTo));
    }

    public void deleteById(int companyId) {
        repository.deleteById(companyId);
    }

    public Set<SkillTo> findAll() {
        return SkillConverter.allFromSkillDao(repository.findAll());
    }

}
