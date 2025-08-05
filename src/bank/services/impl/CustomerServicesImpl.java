package bank.services.impl;

import bank.dto.CustomerResponse;
import bank.model.Customer;
import bank.repository.CustomerRepository;
import bank.services.CustomerServices;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class CustomerServicesImpl implements CustomerServices {

    private final CustomerRepository customerRepository;

    public CustomerServicesImpl() {
        this.customerRepository = new CustomerRepository();
    }

    @Override
    public List<CustomerResponse> getAllCustomers() {
        List<Customer> customers = customerRepository.getCustomerData();

        return customers.stream()
                .map(c -> new CustomerResponse(c.getFullName(), c.getEmail(), c.getGender()))
                .collect(Collectors.toList());
    }

    @Override
    public boolean createCustomer(Customer customer) {
        try {
            customerRepository.insertCustomer(customer);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Customer findCustomerByEmail(String email) {
        return customerRepository.getCustomerByEmail(email);
    }

    @Override
    public List<CustomerResponse> findCustomersByName(String name) {
        List<Customer> customers = customerRepository.getCustomerData();

        // filter by name containing case-insensitive substring
        return customers.stream()
                .filter(c -> c.getFullName() != null &&
                        c.getFullName().toLowerCase().contains(name.toLowerCase()))
                .map(c -> new CustomerResponse(c.getFullName(), c.getEmail(), c.getGender()))
                .collect(Collectors.toList());
    }

    @Override
    public boolean deleteCustomer(String uuid) {
        try {
            return customerRepository.deleteCustomerById(uuid);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<CustomerResponse> getCustomersSortedByName(boolean ascending) {
        List<Customer> customers = customerRepository.getCustomerData();

        Comparator<Customer> comparator = Comparator.comparing(Customer::getFullName,
                String.CASE_INSENSITIVE_ORDER);
        if (!ascending) {
            comparator = comparator.reversed();
        }

        return customers.stream()
                .sorted(comparator)
                .map(c -> new CustomerResponse(c.getFullName(), c.getEmail(), c.getGender()))
                .collect(Collectors.toList());
    }
}
