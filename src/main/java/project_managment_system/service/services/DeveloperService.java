package project_managment_system.service.services;

import project_managment_system.dao.entity.DeveloperDao;
import project_managment_system.dao.repositories.one_entity_repositories.Repository;
import project_managment_system.dto.DeveloperTo;
import project_managment_system.service.converters.DeveloperConverter;

import java.util.Set;

public class DeveloperService {
    private Repository<DeveloperDao> developerRepository;

    public DeveloperService(Repository<DeveloperDao> developerRepository) {
        this.developerRepository = developerRepository;
    }

    public void create(DeveloperTo developerTo) {
        developerRepository.create(DeveloperConverter.toDeveloperDao(developerTo));
    }

    public DeveloperTo findById(int developerId) {
        return DeveloperConverter.fromDeveloperDao(developerRepository.findById(developerId));
    }

    public void update(DeveloperTo developerTo) {
        developerRepository.update(DeveloperConverter.toDeveloperDao(developerTo));
    }


    public void deleteById(int developerId) {
        developerRepository.deleteById(developerId);
    }

    public Set<DeveloperTo> findAll() {
        return DeveloperConverter.allFromDeveloperDao(developerRepository.findAll());
    }
}
