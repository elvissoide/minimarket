package modelo;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ValidacionUsuarios {
    ConexionMySQL conexion = new ConexionMySQL();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    String sql;
    public String[] validarAdministrador (String administrador){
        String[] credenciales = new String[4];
        administrador = "\'" + administrador + "\'";
        sql = "SELECT UsuAdm, ConAdm, CONCAT(NomAdm,' ',ApeAdm), CorAdm FROM administradores WHERE UsuAdm LIKE "
                + administrador;
        try {
            con = conexion.crearConexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            //rs.next();

            if (rs.next()){
                credenciales[0] = rs.getString(1);
                credenciales[1] = rs.getString(2);
                credenciales[2] = rs.getString(3);
                System.out.println(rs.getString(3));
                credenciales[3] = rs.getString(4);
                System.out.println(rs.getString(4));
            } else {
                credenciales[0] = null;
                credenciales[1] = null;
            }

            con.close();
            ps.close();
            rs.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"ERROR en la conexión con la base de datos");
            e.printStackTrace();
        }
        return credenciales;
    }
    public String[] validarCajero (String cajero){
        String[] credenciales = new String[3];
        cajero = "\'" + cajero + "\'";
        sql = "SELECT UsuCaj, ConCaj, IdCaj FROM cajeros WHERE UsuCaj LIKE " + cajero;
        try {
            con = conexion.crearConexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            //rs.next();

            if (rs.next()){
                credenciales[0] = rs.getString(1);
                credenciales[1] = rs.getString(2);
                credenciales[2] = rs.getString(3);
            } else {
                credenciales[0] = null;
                credenciales[1] = null;
                credenciales[2] = null;
            }

            con.close();
            ps.close();
            rs.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"ERROR en la conexión con la base de datos");
            e.printStackTrace();
        }
        return credenciales;
    }
}