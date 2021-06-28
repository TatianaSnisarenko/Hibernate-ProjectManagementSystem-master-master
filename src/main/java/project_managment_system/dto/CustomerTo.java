package project_managment_system.dto;

import project_managment_system.dao.entity.ProjectDao;

import java.util.List;
import java.util.Objects;
import java.util.Set;

public class CustomerTo implements Comparable<CustomerTo>{
    private int idCustomer;
    private String name;
    private String city;
    private Set<ProjectDao> projects;

    public int getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerTo that = (CustomerTo) o;
        return Objects.equals(name, that.name) && Objects.equals(city, that.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, city);
    }

    @Override
    public int compareTo(CustomerTo customerTo) {
        return Integer.compare(this.getIdCustomer(), customerTo.getIdCustomer());
    }
}
