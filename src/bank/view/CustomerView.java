package bank.view;

import bank.controller.CustomerController;
import bank.dto.CustomerResponse;
import bank.model.Customer;

import java.util.List;
import java.util.Scanner;

public class CustomerView {

    private final CustomerController customerController;
    private final Scanner scanner;

    public CustomerView() {
        customerController = new CustomerController();
        scanner = new Scanner(System.in);
    }

    public void customerFeatures() {
        while (true) {
            System.out.println("\n" + "=".repeat(40));
            System.out.println("| 1. View All Customers      |");
            System.out.println("| 2. Search Customers        |");
            System.out.println("| 3. Add Customer            |");
            System.out.println("| 4. Sort Customers          |");
            System.out.println("| 5. Delete Customer         |");
            System.out.println("| 0. Quit the Application    |");
            System.out.println("=".repeat(40));
            System.out.print("Enter option: ");

            int option = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (option) {
                case 1 -> showAllCustomers();
                case 2 -> searchCustomers();
                case 3 -> createCustomer();
                case 4 -> sortCustomers();
                case 5 -> deleteCustomer();
                case 0 -> {

                    System.out.println("Exiting the Application...");
                    System.exit(0);
                    return;
                }
                default -> System.out.println("❌ Invalid option.");
            }
        }
    }

    private void showAllCustomers() {
        System.out.println("\n--- All Customers ---");

        List<CustomerResponse> customers = customerController.getAllCustomers();

        if (customers.isEmpty()) {
            System.out.println("📭 No customer found.");
            return;
        }

        System.out.printf("| %-36s | %-20s | %-10s |\n", "Full Name", "Email", "Gender");
        System.out.println("-".repeat(75));

        for (CustomerResponse c : customers) {
            System.out.printf("| %-36s | %-20s | %-10s |\n",
                    c.fullName(), c.email(), c.gender());
        }
    }

    private void searchCustomers() {
        System.out.println("\n--- Search Customers ---");
        System.out.println("1. By Name");
        System.out.println("2. By Email");
        System.out.print("Choose search type: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1 -> searchCustomerByName();
            case 2 -> searchCustomerByEmail();
            default -> System.out.println("❌ Invalid choice.");
        }
    }

    private void searchCustomerByName() {
        System.out.print("Enter name to search: ");
        String name = scanner.nextLine();

        List<CustomerResponse> matched = customerController.findCustomersByName(name);
        if (matched.isEmpty()) {
            System.out.println("❌ No customers found with name containing \"" + name + "\"");
        } else {
            System.out.println("--- Search Results ---");
            System.out.printf("| %-36s | %-20s | %-10s |\n", "Full Name", "Email", "Gender");
            System.out.println("-".repeat(75));
            for (CustomerResponse c : matched) {
                System.out.printf("| %-36s | %-20s | %-10s |\n",
                        c.fullName(), c.email(), c.gender());
            }
        }
    }

    private void searchCustomerByEmail() {
        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        Customer customer = customerController.findCustomerByEmail(email);
        if (customer == null) {
            System.out.println("❌ No customer found with this email.");
        } else {
            showCustomerDetails(customer);
        }
    }

    private void createCustomer() {
        System.out.print("Enter Full Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Gender: ");
        String gender = scanner.nextLine();

        System.out.print("Enter Email: ");
        String email = scanner.nextLine();

        System.out.print("Enter Phone: ");
        String phone = scanner.nextLine();

        Customer customer = new Customer();
        customer.setUUID(java.util.UUID.randomUUID().toString());
        customer.setFullName(name);
        customer.setGender(gender);
        customer.setEmail(email);
        customer.setPhone(phone);
        customer.setDeleted(false);

        boolean isCreated = customerController.createCustomer(customer);
        if (isCreated) {
            System.out.println("✅ Customer created successfully!");
        } else {
            System.out.println("❌ Failed to create customer.");
        }
    }

    private void deleteCustomer() {
        System.out.print("Enter Customer UUID to delete: ");
        String uuid = scanner.nextLine();

        boolean success = customerController.deleteCustomer(uuid);
        if (success) {
            System.out.println("✅ Customer deleted successfully.");
        } else {
            System.out.println("❌ Customer not found or delete failed.");
        }
    }

    private void sortCustomers() {
        System.out.println("\n--- Sort Customers ---");
        System.out.println("1. By Name Ascending");
        System.out.println("2. By Name Descending");
        System.out.print("Choose sorting option: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        List<CustomerResponse> sortedList = null;
        switch (choice) {
            case 1 -> sortedList = customerController.getCustomersSortedByName(true);
            case 2 -> sortedList = customerController.getCustomersSortedByName(false);
            default -> System.out.println("❌ Invalid choice.");
        }

        if (sortedList != null && !sortedList.isEmpty()) {
            System.out.println("--- Sorted Customers ---");
            System.out.printf("| %-36s | %-20s | %-10s |\n", "Full Name", "Email", "Gender");
            System.out.println("-".repeat(75));
            for (CustomerResponse c : sortedList) {
                System.out.printf("| %-36s | %-20s | %-10s |\n",
                        c.fullName(), c.email(), c.gender());
            }
        } else {
            System.out.println("📭 No customers to display.");
        }
    }

    private void showCustomerDetails(Customer customer) {
        System.out.println("✅ Customer Found:");
        System.out.println("Full Name: " + customer.getFullName());
        System.out.println("Gender   : " + customer.getGender());
        System.out.println("Email    : " + customer.getEmail());
        System.out.println("Phone    : " + customer.getPhone());
    }
}
