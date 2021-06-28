package project_managment_system.service.converters;

import project_managment_system.dao.entity.ProjectDao;
import project_managment_system.dto.ProjectTo;

import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class ProjectConverter {
    public static ProjectDao toProjectDao(ProjectTo projectTo) {
        ProjectDao projectDao = new ProjectDao();
        projectDao.setIdProject(projectTo.getIdProject());
        projectDao.setName(projectTo.getName());
        projectDao.setDescription(projectTo.getDescription());
        projectDao.setCost(projectTo.getCost());
        projectDao.setDate(projectTo.getDate());
        projectDao.setCompanies(projectTo.getCompanies());
        projectDao.setCustomers(projectTo.getCustomers());
        projectDao.setDevelopers(projectTo.getDevelopers());
        return projectDao;
    }

    public static ProjectTo fromProjectDao(ProjectDao projectDao) {
        ProjectTo projectTo = new ProjectTo();
        projectTo.setIdProject(projectDao.getIdProject());
        projectTo.setName(projectDao.getName());
        projectTo.setDescription(projectDao.getDescription());
        projectTo.setCost(projectDao.getCost());
        projectTo.setDate(projectDao.getDate());
        projectTo.setCompanies(projectDao.getCompanies());
        projectTo.setCustomers(projectDao.getCustomers());
        projectTo.setDevelopers(projectDao.getDevelopers());
        return projectTo;
    }

    public static Set<ProjectTo> allFromProjectDao(Set<ProjectDao> projectDaos) {
        return projectDaos.stream()
                .map(ProjectConverter::fromProjectDao)
                .collect(Collectors.toCollection(TreeSet::new));
    }
}
