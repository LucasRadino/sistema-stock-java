package com.stockapp.db;

import java.sql.Connection;
import java.sql.DriverManager;


public class ConexionDB {

    public static Connection conectar() {
        try {
            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/stockdb",
                "root",
                "Holasoygerman123"
                
            );

            
            
            return conn; // 👈 ESTO ES LO IMPORTANTE

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

