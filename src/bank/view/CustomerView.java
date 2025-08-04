package bank.view;

import bank.controller.CustomerController;
import bank.dto.CustomerResponse;
import bank.model.Customer;

import java.util.List;
import java.util.Scanner;

public class CustomerView {

    private final CustomerController customerController;


    public CustomerView(){
        customerController = new CustomerController();
    }

    public void customerFeatures() {

        System.out.println("=".repeat(30));
        System.out.println("| 1. View All Customer        |");
        System.out.println("| 2. Search Customer by ID    |");
        System.out.println("| 3. Search Customer by Email |");
        System.out.println("=".repeat(30));

        System.out.print("Enter option: ");
        int option = new Scanner(System.in).nextInt();

        switch (option){

            case 1 -> {
                List<CustomerResponse> allCustomers = customerController.getAllCustomers();
                allCustomers.forEach(System.out::println);
            }
        }
    }
}
