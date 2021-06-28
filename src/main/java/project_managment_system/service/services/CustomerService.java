package project_managment_system.service.services;


import project_managment_system.dao.entity.CustomerDao;
import project_managment_system.dao.repositories.one_entity_repositories.Repository;
import project_managment_system.dto.CustomerTo;
import project_managment_system.service.converters.CustomerConverter;

import java.util.Set;

public class CustomerService {
    private Repository<CustomerDao> repository;

    public CustomerService(Repository<CustomerDao> repository) {
        this.repository = repository;
    }

    public void create(CustomerTo customerTo) {
        repository.create(CustomerConverter.toCustomerDao(customerTo));
    }

    public CustomerTo findById(int customerId) {
        return CustomerConverter.fromCustomerDao(repository.findById(customerId));
    }

    public void update(CustomerTo customerTo) {
        repository.update(CustomerConverter.toCustomerDao(customerTo));
    }

    public void deleteById(int customerId) {
        repository.deleteById(customerId);
    }

    public Set<CustomerTo> findAll() {
        return CustomerConverter.allFromCustomerDao(repository.findAll());
    }
}
