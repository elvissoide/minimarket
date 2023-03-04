package modelo;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CajeroCrud {
    ConexionMySQL conexion = new ConexionMySQL();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    String sql;

    public List listar(){
        List<CajeroyAdministrador> usuarios = new ArrayList<>();
        sql = "SELECT * FROM cajeros";
        try {
            con = conexion.crearConexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()){
                CajeroyAdministrador cya = new CajeroyAdministrador();
                cya.setId(rs.getInt(1));
                cya.setNombre(rs.getString(2));
                cya.setApellido(rs.getString(3));
                cya.setDireccion(rs.getString(4));
                cya.setCorreo(rs.getString(5));
                cya.setTelefono(rs.getString(6));
                cya.setUsuario(rs.getString(7));
                cya.setContrasena(rs.getString(8));
                usuarios.add(cya);
            }
            con.close();
            ps.close();
            rs.close();
            System.out.println(usuarios.toString());
            System.out.println("Conexion exitosa con la base de datos");
        } catch (Exception e){
            JOptionPane.showMessageDialog(null,"ERROR en la conexión con la base de datos");
            e.printStackTrace();
        }
        return usuarios;
    }

    public CajeroyAdministrador buscar(int id){
        CajeroyAdministrador cya = new CajeroyAdministrador();
        sql = "SELECT * FROM cajeros WHERE idCaj=" + id;
        try {
            con = conexion.crearConexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            rs.next();
            System.out.println("Ejecucion punto 1");
            cya.setId(rs.getInt(1));
            cya.setNombre(rs.getString(2));
            cya.setApellido(rs.getString(3));
            cya.setDireccion(rs.getString(4));
            cya.setCorreo(rs.getString(5));
            cya.setTelefono(rs.getString(6));
            cya.setUsuario(rs.getString(7));
            cya.setContrasena(rs.getString(8));
            con.close();
            ps.close();
            rs.close();
            System.out.println("Ejecucion punto 2");
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "ERROR en la conexión con la base de datos.");
            e.printStackTrace();
        }
        return cya;
    }
    public void eliminar (int id){
        sql = "DELETE FROM cajeros WHERE idCaj=" + id;
        try {
            con = conexion.crearConexion();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
            con.close();
            ps.close();
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "ERROR en la conexion con la base de datos");
            e.printStackTrace();
        }
    }
    public int agregar (CajeroyAdministrador cajero) {
        sql = "INSERT INTO cajeros (NomCaj, ApeCaj, DirCaj, CorCaj, TelCaj, UsuCaj, ConCaj)" +
                "VALUES (?,?,?,?,?,?,?)";
        try {
            con = conexion.crearConexion();
            ps = con.prepareStatement(sql);
            System.out.println("PUNTO 1");
            ps.setString(1,cajero.getNombre());
            ps.setString(2,cajero.getApellido());
            ps.setString(3,cajero.getDireccion());
            ps.setString(4,cajero.getCorreo());
            ps.setString(5,cajero.getTelefono());
            ps.setString(6,cajero.getUsuario());
            ps.setString(7,cajero.getContrasena());
            System.out.println("PUNTO 1");
            ps.executeUpdate();
            con.close();
            ps.close();
            return 1;
        } catch (Exception e){
            JOptionPane.showMessageDialog(null, "ERROR en la conexión con la base de datos.");
            e.printStackTrace();
            return 0;
        }
    }

}
