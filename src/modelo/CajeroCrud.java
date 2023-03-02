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

}
