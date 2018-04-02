package ua.training;

import java.io.Console;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MainClass {

    public static void main(String[] args)
    {

        try{
//            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver_loading_success");
            //у MySQL обязательно есть системная база,
            //к ней и будем создавать соединение.
            String url = "jdbc:mysql://localhost/trainingdb";
            String name = "admin";
            String password = "admin";
            try {
                Connection con = DriverManager.getConnection(url, name, password);
                System.out.println("Connected");
                con.close();
                System.out.println("Disconnected");
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Welcome");
//        new Console(new LibraryManagerImpl()).run();
    }
}