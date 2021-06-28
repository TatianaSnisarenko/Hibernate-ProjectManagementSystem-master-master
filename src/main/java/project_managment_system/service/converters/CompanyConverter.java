package project_managment_system.service.converters;

import project_managment_system.dao.entity.CompanyDao;
import project_managment_system.dto.CompanyTo;

import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class CompanyConverter {

    public static CompanyDao toCompanyDao(CompanyTo companyTo) {
        CompanyDao companyDao = new CompanyDao();
        companyDao.setIdCompany(companyTo.getIdCompany());
        companyDao.setName(companyTo.getName());
        companyDao.setCity(companyTo.getCity());
        companyDao.setProjects(companyTo.getProjects());
        companyDao.setDevelopers(companyTo.getDevelopers());
        return companyDao;
    }

    public static CompanyTo fromCompanyDao(CompanyDao companyDao) {
        CompanyTo companyTo = new CompanyTo();
        companyTo.setIdCompany(companyDao.getIdCompany());
        companyTo.setName(companyDao.getName());
        companyTo.setCity(companyDao.getCity());
        companyTo.setProjects(companyDao.getProjects());
        companyTo.setDevelopers(companyDao.getDevelopers());
        return companyTo;
    }

    public static Set<CompanyTo> allFromCompanyDao(Set<CompanyDao> companyDaos) {
        return companyDaos.stream()
                .map(CompanyConverter::fromCompanyDao)
                .collect(Collectors.toCollection(TreeSet::new));
    }
}
