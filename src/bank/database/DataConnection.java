package bank.database;

import bank.model.Customer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataConnection {

        private static Connection connection;
        private static String url = "jdbc:postgresql://localhost:5432/aba_bank_db";
        private static String user = "postgres";
        private static String password = "Raz@0205";
        private DataConnection(){};

        public static Connection getConnection(){

            try{
                if(connection == null || connection.isClosed()){
                    connection = DriverManager.getConnection(url, user, password);
                    }
                }catch (SQLException e){
                System.out.println(e.getStackTrace());
                }

            return connection;
        }


}
