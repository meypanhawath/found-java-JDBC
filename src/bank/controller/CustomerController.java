package bank.controller;

import bank.dto.CustomerResponse;
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
}
