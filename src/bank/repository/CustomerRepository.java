package bank.repository;

import bank.database.DataConnection;
import bank.model.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepository {

    public List<Customer> getCustomerDAta(){

        String sql = "SELECT * FROM customer";
        List<Customer> customerList = new ArrayList<>();
        try(Connection con = DataConnection.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
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
}

// JDBC


