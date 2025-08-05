package bank.controller;

import bank.dto.CustomerResponse;
import bank.model.Customer;
import bank.services.CustomerServices;
import bank.services.impl.CustomerServicesImpl;

import java.util.List;

public class CustomerController {

    private final CustomerServices customerServices;

    public CustomerController() {
        customerServices = new CustomerServicesImpl();
    }

    public List<CustomerResponse> getAllCustomers() {
        return customerServices.getAllCustomers();
    }

    public Customer findCustomerByEmail(String email) {
        return customerServices.findCustomerByEmail(email);
    }

    public List<CustomerResponse> findCustomersByName(String name) {
        return customerServices.findCustomersByName(name);
    }

    public boolean createCustomer(Customer customer) {
        return customerServices.createCustomer(customer);
    }

    public boolean deleteCustomer(String uuid) {
        return customerServices.deleteCustomer(uuid);
    }

    public List<CustomerResponse> getCustomersSortedByName(boolean ascending) {
        return customerServices.getCustomersSortedByName(ascending);
    }
}
