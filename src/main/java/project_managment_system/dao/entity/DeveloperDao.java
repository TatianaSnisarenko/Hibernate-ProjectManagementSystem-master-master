package project_managment_system.dao.entity;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

@Entity
@Table(name="developers")
public class DeveloperDao implements Comparable<DeveloperDao>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_developer")
    private int idDeveloper;

    @Column(name="name")
    private String name;

    @Column(name = "age")
    private int age;

    @Column(name="sex")
    private String sex;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_company")
    private CompanyDao company;

    @Column(name="salary")
    private double salary;

    @ManyToMany(fetch = FetchType.EAGER,
            targetEntity = project_managment_system.dao.entity.ProjectDao.class)
    @JoinTable(name = "developers_projects",
            joinColumns = @JoinColumn(name = "id_developer"),
            inverseJoinColumns = @JoinColumn(name = "id_project"))
    private Set<ProjectDao> projects = new TreeSet<>();

    @ManyToMany(fetch = FetchType.EAGER,
            targetEntity = project_managment_system.dao.entity.SkillDao.class)
    @JoinTable(name = "developers_skills",
            joinColumns = @JoinColumn(name = "id_developer"),
            inverseJoinColumns = @JoinColumn(name = "id_skill"))
    private Set<SkillDao> skills = new TreeSet<>();

    public int getIdDeveloper() {
        return idDeveloper;
    }

    public void setIdDeveloper(int idDeveloper) {
        this.idDeveloper = idDeveloper;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public CompanyDao getCompany() {
        return company;
    }

    public void setCompany(CompanyDao company) {
        this.company = company;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Set<ProjectDao> getProjects() {
        return projects;
    }

    public void setProjects(Set<ProjectDao> projects) {
        this.projects = projects;
    }

    public Set<SkillDao> getSkills() {
        return skills;
    }

    public void setSkills(Set<SkillDao> skills) {
        this.skills = skills;
    }

    @Override
    public String toString() {
        return  name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeveloperDao that = (DeveloperDao) o;
        return Objects.equals(name, that.name) && Objects.equals(sex, that.sex);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, sex);
    }

    @Override
    public int compareTo(DeveloperDao developerDao) {
        return Integer.compare(this.getIdDeveloper(), developerDao.getIdDeveloper());
    }
}
