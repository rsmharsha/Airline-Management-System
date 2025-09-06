package com.airlinemanagement;
import java.sql.*;

public class DBConnection {

    Connection c;
    Statement s;

    public DBConnection(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver"); // Registering the driver from external .jar file
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/airlinemanagementsystem", "root", "mHarsha^314");
            s = c.createStatement();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
