package bank.services;

import bank.dto.CustomerResponse;
import bank.model.Customer;

import java.util.List;

public interface CustomerServices {

    List<CustomerResponse> getAllCustomers();

    boolean createCustomer(Customer customer);

    Customer findCustomerByEmail(String email);

    List<CustomerResponse> findCustomersByName(String name);

    boolean deleteCustomer(String uuid);

    List<CustomerResponse> getCustomersSortedByName(boolean ascending);
}
