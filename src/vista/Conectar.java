/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Gateway
 */
public class Conectar {
    
    Connection conectar=null;
    
    public Connection getConexion(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conectar=DriverManager.getConnection("jdbc:mysql://localhost:3306/accesorios","root","");
            System.out.println("Conecto a MySqL");
        } catch (Exception e) {
            System.out.println("Error");
            System.out.print(e.getMessage());
        }
        return conectar;
    }
    
}
