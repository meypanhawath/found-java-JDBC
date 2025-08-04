package bank.services;

import bank.dto.CustomerResponse;

import java.util.List;

public interface CustomerServices {


    // Get all Customer

    List<CustomerResponse> getAllCustomers();
}
