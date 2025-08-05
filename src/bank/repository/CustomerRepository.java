package bank.repository;

import bank.database.DataConnection;
import bank.model.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepository {

    // Get All Customer Data
    public List<Customer> getCustomerData() {
        String sqlSelectTable = "SELECT * FROM customer";
        List<Customer> customerList = new ArrayList<>();
        try (Connection con = DataConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(sqlSelectTable);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Customer customer = new Customer();

                customer.setUUID(rs.getString("uuid"));
                customer.setFullName(rs.getString("full_name"));
                customer.setGender(rs.getString("gender"));
                customer.setEmail(rs.getString("email"));
                customer.setPhone(rs.getString("phone"));
                customer.setDeleted(rs.getBoolean("is_deleted"));

                customerList.add(customer);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customerList;
    }

    // Find customer by email
    public Customer getCustomerByEmail(String email) {
        Customer customer = null;

        String sqlSearchByEmail = "SELECT * FROM customer WHERE email = ?";

        try (Connection con = DataConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(sqlSearchByEmail)) {

            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                customer = new Customer();
                customer.setUUID(rs.getString("uuid"));
                customer.setFullName(rs.getString("full_name"));
                customer.setGender(rs.getString("gender"));
                customer.setEmail(rs.getString("email"));
                customer.setPhone(rs.getString("phone"));
                customer.setDeleted(rs.getBoolean("is_deleted"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customer;
    }

    // Find customer by UUID
    public Customer getCustomerById(String id) {
        Customer customer = null;

        String sqlSearchById = "SELECT * FROM customer WHERE uuid = ?";

        try (Connection con = DataConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(sqlSearchById)) {

            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                customer = new Customer();
                customer.setUUID(rs.getString("uuid"));
                customer.setFullName(rs.getString("full_name"));
                customer.setGender(rs.getString("gender"));
                customer.setEmail(rs.getString("email"));
                customer.setPhone(rs.getString("phone"));
                customer.setDeleted(rs.getBoolean("is_deleted"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customer;
    }

    // Add new customer
    public void insertCustomer(Customer customer) {
        String sql = """
            INSERT INTO customer (uuid, full_name, gender, email, phone, is_deleted)
            VALUES (?, ?, ?, ?, ?, ?)
        """;

        try (Connection con = DataConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, customer.getUUID());
            stmt.setString(2, customer.getFullName());
            stmt.setString(3, customer.getGender());
            stmt.setString(4, customer.getEmail());
            stmt.setString(5, customer.getPhone());
            stmt.setBoolean(6, customer.getDeleted());

            int row = stmt.executeUpdate();
            System.out.printf("✅ Inserted %d customer(s).\n", row);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete Customer by UUID
    public boolean deleteCustomerById(String uuid) {
        String sql = "DELETE FROM customer WHERE uuid = ?";
        try (Connection con = DataConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, uuid);
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
