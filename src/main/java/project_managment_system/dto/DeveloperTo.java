package project_managment_system.dto;

import project_managment_system.dao.entity.CompanyDao;
import project_managment_system.dao.entity.ProjectDao;
import project_managment_system.dao.entity.SkillDao;

import java.util.Objects;
import java.util.Set;

public class DeveloperTo implements Comparable<DeveloperTo>{
    private int idDeveloper;
    private String name;
    private int age;
    private String sex;
    private CompanyDao company;
    private double salary;
    private Set<SkillDao> skills;
    private Set<ProjectDao> projects;

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

    public Set<SkillDao> getSkills() {
        return skills;
    }

    public void setSkills(Set<SkillDao> skills) {
        this.skills = skills;
    }

    public Set<ProjectDao> getProjects() {
        return projects;
    }

    public void setProjects(Set<ProjectDao> projects) {
        this.projects = projects;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeveloperTo that = (DeveloperTo) o;
        return Objects.equals(name, that.name) && Objects.equals(sex, that.sex);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, sex);
    }

    @Override
    public int compareTo(DeveloperTo developerTo) {
        return Integer.compare(this.getIdDeveloper(), developerTo.getIdDeveloper());
    }
}
