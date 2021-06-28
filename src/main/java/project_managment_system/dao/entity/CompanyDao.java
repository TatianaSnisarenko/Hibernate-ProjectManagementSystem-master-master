package project_managment_system.dao.entity;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

@Entity(name = "CompanyDao")
@Table(name="companies")
public class CompanyDao implements Comparable<CompanyDao>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_company")
    private int idCompany;

    @Column(name="name")
    private String name;

    @Column(name="city")
    private String city;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_company")
    Set<DeveloperDao> developers = new TreeSet<>();

    @ManyToMany(fetch = FetchType.EAGER,
    targetEntity = project_managment_system.dao.entity.ProjectDao.class)
    @JoinTable(name = "companies_projects",
    joinColumns = @JoinColumn(name = "id_company"),
    inverseJoinColumns = @JoinColumn(name = "id_project"))
    private Set<ProjectDao> projects = new TreeSet<>();

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

    public Set<DeveloperDao> getDevelopers() {
        return developers;
    }

    public void setDevelopers(Set<DeveloperDao> developers) {
        this.developers = developers;
    }

    public Set<ProjectDao> getProjects() {
        return projects;
    }

    public void setProjects(Set<ProjectDao> projects) {
        this.projects = projects;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompanyDao that = (CompanyDao) o;
        return Objects.equals(name, that.name) && Objects.equals(city, that.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, city);
    }

    @Override
    public int compareTo(CompanyDao companyDao) {
        return Integer.compare(this.getIdCompany(), companyDao.getIdCompany());
    }
}
