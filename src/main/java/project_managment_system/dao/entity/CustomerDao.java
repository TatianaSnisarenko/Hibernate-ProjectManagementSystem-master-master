package project_managment_system.dao.entity;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

@Entity
@Table(name="customers")
public class CustomerDao implements Comparable<CustomerDao>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_customer")
    private int idCustomer;

    @Column(name="name")
    private String name;

    @Column(name="city")
    private String city;

    @ManyToMany(fetch = FetchType.EAGER,
            targetEntity = project_managment_system.dao.entity.ProjectDao.class)
    @JoinTable(name = "customers_projects",
    joinColumns = @JoinColumn(name = "id_customer"),
    inverseJoinColumns = @JoinColumn(name = "id_project"))
    private Set<ProjectDao> projects = new TreeSet<>();

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
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerDao that = (CustomerDao) o;
        return Objects.equals(name, that.name) && Objects.equals(city, that.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, city);
    }

    @Override
    public int compareTo(CustomerDao customerDao) {
        return Integer.compare(this.getIdCustomer(), customerDao.getIdCustomer());
    }
}

