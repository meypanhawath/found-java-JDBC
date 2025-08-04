package bank.repository;

import bank.database.DataConnection;
import bank.model.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class  CustomerRepository {

    private List<Customer> customers;

    public List<Customer> getCustomers() {
        return customers;
    }

    // Get All Customer Data
    public List<Customer> getCustomerData(){

        String sqlSelectTable = "SELECT * FROM customer";
        List<Customer> customerList = new ArrayList<>();
        try(Connection con = DataConnection.getConnection();
            PreparedStatement stmt = con.prepareStatement(sqlSelectTable);
            ResultSet rs = stmt.executeQuery()){

            while(rs.next()){
                Customer customer = new Customer();

                customer.setUUID(rs.getString("uuid"));
                customer.setFullName(rs.getString("full_name"));
                customer.setGender(rs.getString("gender"));
                customer.setEmail(rs.getString("email"));
                customer.setPhone(rs.getString("phone"));
                customer.setDeleted(rs.getBoolean("is_deleted"));

                customerList.add(customer);
            }

        }catch (SQLException e){
            System.out.println(e.getStackTrace());
        }

        return customerList;
    }

    // Find customer by email
    public Customer getCustomerByEmail(String email){
        Customer customer = new Customer();

        String sqlSearchByEmail = "SELECT * FROM customer WHERE email = ?";

        try(Connection con = DataConnection.getConnection();
            PreparedStatement stmt = con.prepareStatement(sqlSearchByEmail);
        ){

            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

        }catch (SQLException e){
            System.out.println(e.getStackTrace());
        }

        return customer;
    }
}

// JDBC


