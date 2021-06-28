package project_managment_system.service.converters;

import project_managment_system.dao.entity.CustomerDao;
import project_managment_system.dto.CustomerTo;

import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class CustomerConverter {

    public static CustomerDao toCustomerDao(CustomerTo customerTo) {
        CustomerDao customerDao = new CustomerDao();
        customerDao.setIdCustomer(customerTo.getIdCustomer());
        customerDao.setName(customerTo.getName());
        customerDao.setCity(customerTo.getCity());
        customerDao.setProjects(customerTo.getProjects());
        return customerDao;
    }

    public static CustomerTo fromCustomerDao(CustomerDao customerDao) {
        CustomerTo customerTo = new CustomerTo();
        customerTo.setIdCustomer(customerDao.getIdCustomer());
        customerTo.setName(customerDao.getName());
        customerTo.setCity(customerDao.getCity());
        customerTo.setProjects(customerDao.getProjects());
        return customerTo;
    }

    public static Set<CustomerTo> allFromCustomerDao(Set<CustomerDao> customerDaos) {
        return customerDaos.stream()
                .map(CustomerConverter::fromCustomerDao)
                .collect(Collectors.toCollection(TreeSet::new));
    }
}
