package project_managment_system.dao.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

@Entity(name = "ProjectDao")
@Table(name = "projects")
public class ProjectDao implements Comparable<ProjectDao> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_project")
    private int idProject;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "cost")
    private double cost;

    @Column(name = "creation_date")
    private LocalDate date;

    @ManyToMany(fetch = FetchType.EAGER,
            targetEntity = project_managment_system.dao.entity.CompanyDao.class)
    @JoinTable(name = "companies_projects",
            joinColumns = @JoinColumn(name = "id_project"),
            inverseJoinColumns = @JoinColumn(name = "id_company"))
    private Set<CompanyDao> companies = new TreeSet<>();

    @ManyToMany(fetch = FetchType.EAGER,
            targetEntity = project_managment_system.dao.entity.CustomerDao.class)
    @JoinTable(name = "customers_projects",
            joinColumns = @JoinColumn(name = "id_project"),
            inverseJoinColumns = @JoinColumn(name = "id_customer"))
    private Set<CustomerDao> customers = new TreeSet<>();

    @ManyToMany(fetch = FetchType.EAGER,
            targetEntity = project_managment_system.dao.entity.DeveloperDao.class)
    @JoinTable(name = "developers_projects",
            joinColumns = @JoinColumn(name = "id_project"),
            inverseJoinColumns = @JoinColumn(name = "id_developer"))
    private Set<DeveloperDao> developers = new TreeSet<>();

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

    public Set<DeveloperDao> getDevelopers() {
        return developers;
    }

    public void setDevelopers(Set<DeveloperDao> developers) {
        this.developers = developers;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectDao that = (ProjectDao) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public int compareTo(ProjectDao projectDao) {
        return Integer.compare(this.getIdProject(), projectDao.getIdProject());
    }
}

