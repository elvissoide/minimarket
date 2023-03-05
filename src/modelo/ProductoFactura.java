package modelo;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductoFactura {
    ConexionMySQL conexion = new ConexionMySQL();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    String sql;

    public List listarProductosEncontrados(String productoBuscado){
        List<Producto> productos = new ArrayList<>();
        sql = "SELECT CodProd, DesProd, StoProd, ValVentProd FROM productos " +
                "  WHERE NomProd LIKE '%" + productoBuscado + "%'";
        try {
            con = conexion.crearConexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()){
                Producto prod = new Producto();
                prod.setId(rs.getInt(1));
                prod.setDescripcion(rs.getString(2));
                prod.setStock(rs.getInt(3));
                prod.setValorVenta(rs.getDouble(4));
                productos.add(prod);
            }

            con.close();
            ps.close();
            rs.close();
            System.out.println("Conexion exitosa con la base de datos");
        } catch (Exception e){
            JOptionPane.showMessageDialog(null,"ERROR en la conexión con la base de datos");
            e.printStackTrace();
        }
        return productos;
    }
    public Object agregarProductoComprado (int id, int cantidad) {
        Object[] producto = new Object[6];
        double IVA = 0.12;
        sql = "SELECT CodProd, DesProd, ValVentProd, IvaProd FROM productos WHERE CodProd = " + id;
        try {
            con = conexion.crearConexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            rs.next();
            producto[0] = rs.getInt(1);
            producto[1] = rs.getString(2);
            producto[2] = cantidad;
            producto[3] = rs.getDouble(3);
            producto[4] = cantidad * (double)producto[3];
            //producto[4] = Math.round(((double) producto[4]*100.0)/100.0);
            if (rs.getInt(4) == 1){
                producto[5] = (double)producto[4] * IVA;
                //producto[5] = Math.round((double)producto[5]*100.0)/100.0;
            } else {
                producto[5] = 0;
            }
            con.close();
            ps.close();
            rs.close();
            System.out.println("Conexion exitosa con la base de datos");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"ERROR en la conexión con la base de datos");
            e.printStackTrace();
        }
        return producto;
    }
    public int insertarRegistroCabecera(Object[] datosCabecera) {
        String idCajero = datosCabecera[0].toString();
        String nombreCliente = "\'" + datosCabecera[1].toString() + "\'";
        String fechaCliente = "\'" + datosCabecera[2].toString() + "\'";
        String subtotal = datosCabecera[3].toString();
        String iva = datosCabecera[4].toString();
        String total = datosCabecera[5].toString();
        String valores = idCajero + "," + nombreCliente + "," + fechaCliente + "," + subtotal + "," + iva + "," +
                total + ");";
        sql = "INSERT INTO cabecerasFacturas (IdCajFact, NomCliFact, FecEmiFact, SubFact, IvaFact, TotFact) VALUES (" +
                valores;
        int r = 0;
        try {
            con = conexion.crearConexion();
            ps = con.prepareStatement(sql);
            r = ps.executeUpdate();
            con.close();
            ps.close();
            if (r == 1){
                return 1;
            } else {
                return 0;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"ERROR en la conexión con la base de datos");
            e.printStackTrace();
        }
        return r;
    }
    public int obtenerNumFac(){
        int numeroFactura = -1;
        sql = "SELECT MAX(NumFact) FROM cabecerasFacturas";
        try {
            con = conexion.crearConexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            rs.next();

            numeroFactura = rs.getInt(1);

            con.close();
            ps.close();
            rs.close();
        } catch (Exception e){
            JOptionPane.showMessageDialog(null,"ERROR en la conexión con la base de datos");
            e.printStackTrace();
        }
        return numeroFactura;
    }
    public int insertarRegistrosDetalles(int numFac, String codProd, String can, String valVen, String tot){
        String valores = String.valueOf(numFac) + "," + codProd + "," + can + "," + valVen + "," + tot;
        sql = "INSERT INTO detallesFacturas (NumFactDet, CodProdDet, CanDet, ValVenDet, TotDet) VALUES " +
                "(" + valores + ")";
        System.out.println(sql);
        int r = 0;
        try {
            con = conexion.crearConexion();
            ps = con.prepareStatement(sql);
            r = ps.executeUpdate();
            con.close();
            ps.close();
            if (r == 1){
                return 1;
            } else {
                return 0;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"ERROR en la conexión con la base de datos");
            e.printStackTrace();
        }
        return r;
    }
}
