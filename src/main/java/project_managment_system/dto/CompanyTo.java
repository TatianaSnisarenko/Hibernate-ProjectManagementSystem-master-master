package project_managment_system.dto;

import project_managment_system.dao.entity.DeveloperDao;
import project_managment_system.dao.entity.ProjectDao;

import java.util.List;
import java.util.Objects;
import java.util.Set;

public class CompanyTo implements Comparable<CompanyTo>{

    private int idCompany;
    private String name;
    private String city;
    private Set<ProjectDao> projects;
    private Set<DeveloperDao> developers;

    public int getIdCompany() {
        return idCompany;
    }

    public void setIdCompany(int idCompany) {
        this.idCompany = idCompany;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Set<ProjectDao> getProjects() {
        return projects;
    }

    public void setProjects(Set<ProjectDao> projects) {
        this.projects = projects;
    }

    public Set<DeveloperDao> getDevelopers() {
        return developers;
    }

    public void setDevelopers(Set<DeveloperDao> developers) {
        this.developers = developers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompanyTo company = (CompanyTo) o;
        return Objects.equals(name, company.name) && Objects.equals(city, company.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, city);
    }

    @Override
    public int compareTo(CompanyTo companyTo) {
        return Integer.compare(this.getIdCompany(), companyTo.getIdCompany());
    }
}
