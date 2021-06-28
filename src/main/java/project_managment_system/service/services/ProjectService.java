package project_managment_system.service.services;

import project_managment_system.dao.entity.ProjectDao;
import project_managment_system.dao.repositories.one_entity_repositories.Repository;
import project_managment_system.dto.ProjectTo;
import project_managment_system.service.converters.ProjectConverter;

import java.util.Set;

public class ProjectService {
    private Repository<ProjectDao> projectRepository;

    public ProjectService(Repository<ProjectDao> projectRepository) {
        this.projectRepository = projectRepository;
    }

    public void create(ProjectTo projectTo) {
        projectRepository.create( ProjectConverter.toProjectDao(projectTo));
    }

    public ProjectTo findById(int projectId) {
        return ProjectConverter.fromProjectDao(projectRepository.findById(projectId));
    }

    public void update(ProjectTo projectTo) {

        projectRepository.update(ProjectConverter.toProjectDao(projectTo));
    }

    public void deletedById(int projectId) {
       projectRepository.deleteById(projectId);
    }

    public Set<ProjectTo> findAll() {
        return ProjectConverter.allFromProjectDao(projectRepository.findAll());
    }

}
