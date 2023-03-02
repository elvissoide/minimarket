package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductoCrud {
    ConexionMySQL conexion = new ConexionMySQL();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    String sql;
    public List listar()
    {
        List<Producto> productos = new ArrayList<>();
        sql = "SELECT * FROM Productos";

        try {
            con = conexion.crearConexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while(rs.next())
            {
                Producto prod = new Producto();
                prod.setId(rs.getInt(1));
                prod.setNombre(rs.getString(2));
                prod.setDescripcion(rs.getString(3));
                prod.setStock(rs.getInt(    4));
                prod.setValorVenta((float) rs.getDouble(5));
                prod.setProveedor(rs.getString(7));
                prod.setIva(rs.getBoolean(6));
                productos.add(prod);
            }
            con.close();
            ps.close();
            rs.close();
            System.out.println(productos.toString());
            System.out.println("Conexion exitosa con la base de datos");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"ERROR en la conexión con la base de datos");
            e.printStackTrace();
        }
        return productos;
    }

}
