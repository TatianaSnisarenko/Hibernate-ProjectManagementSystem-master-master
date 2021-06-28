package project_managment_system.dto;

import project_managment_system.dao.entity.CompanyDao;
import project_managment_system.dao.entity.CustomerDao;
import project_managment_system.dao.entity.DeveloperDao;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class ProjectTo implements Comparable<ProjectTo>{
    private int idProject;
    private String name;
    private String description;
    private double cost;
    private LocalDate date;
    private Set<DeveloperDao> developers;
    private Set<CompanyDao> companies;
    private Set<CustomerDao> customers;

    public int getIdProject() {
        return idProject;
    }

    public void setIdProject(int idProject) {
        this.idProject = idProject;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Set<DeveloperDao> getDevelopers() {
        return developers;
    }

    public void setDevelopers(Set<DeveloperDao> developers) {
        this.developers = developers;
    }

    public Set<CompanyDao> getCompanies() {
        return companies;
    }

    public void setCompanies(Set<CompanyDao> companies) {
        this.companies = companies;
    }

    public Set<CustomerDao> getCustomers() {
        return customers;
    }

    public void setCustomers(Set<CustomerDao> customers) {
        this.customers = customers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectTo projectTo = (ProjectTo) o;
        return Objects.equals(name, projectTo.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public int compareTo(ProjectTo projectTo) {
        return Integer.compare(this.getIdProject(), projectTo.getIdProject());
    }
}
