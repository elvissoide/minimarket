package modelo;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;

public class ConexionMySQL {
    Connection conexion;
    public Connection crearConexion(){
        String url = "jdbc:mysql://localhost:3306/minimarketearf";
        String usuario = "root";
        String contrasena = "Gatito1011";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(url, usuario, contrasena);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"ERROR DE CONEXION CON LA BASE DE DATOS");
            e.printStackTrace();
        }
        return conexion;
    }
}
