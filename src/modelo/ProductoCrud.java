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
                prod.setStock(rs.getInt(4));
                prod.setValorVenta((rs.getDouble(5)));
                prod.setIva(rs.getBoolean(6));
                prod.setProveedor(rs.getString(7));
                productos.add(prod);
            }
            con.close();
            ps.close();
            rs.close();
            System.out.println("Conexion exitosa con la base de datos");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"ERROR en la conexión con la base de datos");
            e.printStackTrace();
        }
        return productos;
    }
    public Producto buscar(int id)
    {
        Producto prod = new Producto();
        sql = "SELECT * FROM Productos " +
                "WHERE CodProd = " + id;
        try
        {
            con = conexion.crearConexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            rs.next();
            prod.setId(rs.getInt(1));
            prod.setNombre(rs.getString(2));
            prod.setDescripcion(rs.getString(3));
            prod.setStock(rs.getInt(    4));
            prod.setValorVenta((rs.getDouble(5)));
            prod.setIva(rs.getBoolean(6));
            prod.setProveedor(rs.getString(7));
            con.close();
            ps.close();
            rs.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR en la conexión con la base de datos.");
            e.printStackTrace();
        }
        return prod;
    }
    public int agregar(Producto prod)
    {
        sql = "INSERT INTO Productos " +
                "(CodProd, NomProd, DesProd, StoProd, ValVentProd, IvaProd, RucProvProd)" +
                "VALUES (?,?,?,?,?,?,?)";
        try
        {
            con = conexion.crearConexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, String.valueOf(prod.getId()));
            ps.setString(2, prod.getNombre());
            ps.setString(3, prod.getDescripcion());
            ps.setInt(4, prod.getStock());
            ps.setDouble(5, prod.getValorVenta());
            ps.setBoolean(6, prod.isIva());
            ps.setString(7, prod.getProveedor());
            ps.executeUpdate();
            con.close();
            ps.close();
            return 1;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR en la conexión con la base de datos.");
            e.printStackTrace();
            return 0;
        }
    }
    public int actualizar(Producto producto)
    {
        sql = "UPDATE Productos " +
                "SET NomProd = ?, DesProd = ?, StoProd = ?, " +
                "ValVentProd = ?, IvaProd = ?, RucProvProd = ? " +
                "WHERE CodProd = ?";
        int r = 0;
        try
        {
            con = conexion.crearConexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, producto.getNombre());
            ps.setString(2, producto.getDescripcion());
            ps.setInt(3, producto.getStock());
            ps.setDouble(4, producto.getValorVenta());
            ps.setBoolean(5, producto.isIva());
            ps.setString(6, producto.getProveedor());
            ps.setString(7, String.valueOf(producto.getId()));
            r = ps.executeUpdate();
            if (r == 1)
                return  1;
            else
                return 0;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR en la conexión con la base de datos.");
            e.printStackTrace();
            return r;
        }
    }
    public void eliminar(int id)
    {
        sql = "DELETE FROM Productos " +
                " WHERE CodProd = " + id;
        try
        {
            con = conexion.crearConexion();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
            con.close();
            ps.close();
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, "ERROR en la conexion con la base de datos");
            e.printStackTrace();
        }
    }
}
