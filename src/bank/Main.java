package bank;

import bank.model.Customer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

public class Main {

    public static void main(String[] args) {

        Customer customer = new Customer(
                UUID.randomUUID().toString(),
                "Mey Panhawath",
                "Male",
                "panhawath18@gmail.com",
                "078654234",
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
            System.out.printf("Update: %d successfully.", updateInserted);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }
}
