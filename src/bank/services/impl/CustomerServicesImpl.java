package bank.services.impl;

import bank.dto.CustomerResponse;
import bank.model.Customer;
import bank.repository.CustomerRepository;
import bank.services.CustomerServices;

import java.util.List;

public class CustomerServicesImpl implements CustomerServices {

    // 1. Define object
    private final CustomerRepository customerRepository;

    // 2. Inject object
    public CustomerServicesImpl(){
        customerRepository = new CustomerRepository();
    }

    @Override
    public List<CustomerResponse> getAllCustomers() {
    // All customer
        List<Customer> customers = customerRepository.getCustomers();

        // Full Form
//        List<CustomerResponse> customerResponsesList = customers
//                .stream()
//                .map((customer -> new CustomerResponse(
//                        customer.getFullName(),
//                        customer.getEmail(),
//                        customer.getGender()
//                ) )
//        ).toList();

    // Domain => DTO
        // Return Form
        return customers
                .stream()
                .map((customer -> new CustomerResponse(
                                customer.getFullName(),
                                customer.getEmail(),
                                customer.getGender()
                        ) )
                ).toList();
    }
}
