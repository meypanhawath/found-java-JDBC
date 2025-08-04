package bank;

import bank.model.Customer;
import bank.view.CustomerView;

import java.sql.*;
import java.util.UUID;

public class Main {

    public static void main(String[] args) {

        CustomerView customerView = new CustomerView();
        customerView.customerFeatures();


        Customer customer = new Customer(
                UUID.randomUUID().toString(),
                "KangKang",
                "Male",
                "kangkang@gmail.com",
                "041987662",
                false);

            String url = "jdbc:postgresql://localhost:5432/aba_bank_db";
            String user = "postgres";
            String password = "Raz@0205";
            String sql = """ 
                        INSERT INTO customer (uuid, full_name, gender, email, phone, is_deleted)
                        VALUES (?, ?, ?, ?, ?, ?)
                        """;

        try(Connection connection = DriverManager.getConnection(url, user, password)) {

            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setString(1, customer.getUUID());
            stmt.setString(2, customer.getFullName());
            stmt.setString(3, customer.getGender());
            stmt.setString(4, customer.getEmail());
            stmt.setString(5, customer.getPhone());
            stmt.setBoolean(6, customer.getDeleted());
            int updateInserted = stmt.executeUpdate();
            System.out.printf("Update: %d successfully.\n\n", updateInserted);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }


        String selectSQL = "SELECT * FROM customer";
        try(Connection connection = DriverManager.getConnection(url, user, password)){

            PreparedStatement stmt = connection.prepareStatement(selectSQL);
            ResultSet resultSet = stmt.executeQuery();

            System.out.println("=".repeat(130));
            System.out.printf("| %-40s | %-15s | %-6s | %-25s | %-12s | %-8s |\n", "UUID", "full_name", "gender", "email", "phone", "is_deleted");
            System.out.println("=".repeat(130));

            while (resultSet.next()){
                String uuid = resultSet.getString("uuid");
                String fullName = resultSet.getString("full_name");
                String gender = resultSet.getString("gender");
                String email = resultSet.getString("email");
                String phone = resultSet.getString("phone");
                Boolean isDeleted = resultSet.getBoolean("is_deleted");

                System.out.printf("| %-40s | %-15s | %-6s | %-25s | %-12s | %-8s |\n", uuid, fullName, gender, email, phone, isDeleted ? "Yes" : "No");
            }

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
