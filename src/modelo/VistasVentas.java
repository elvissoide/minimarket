package modelo;

import vistas.VistaRevisionVentasAdmi;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.List;

public class VistasVentas
{
    ConexionMySQL conexion = new ConexionMySQL();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    String sql;
    public List listar_sencillo()
    {
        List<Factura> consulta = new ArrayList<>();
        sql = "SELECT cj.IdCaj, cj.ApeCaj ,cj.NomCaj, sum(TotFact) as total\n" +
                "FROM cajeros as cj, cabecerasfacturas as cf\n" +
                "WHERE cj.IdCaj = cf.IdCajFact\n" +
                "GROUP BY cj.IdCaj";
        try {

            con = conexion.crearConexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next())
            {
                Factura cabfact = new Factura();
                cabfact.setIdCajero(rs.getString(1));
                cabfact.setApelllido(rs.getString(2));
                cabfact.setNombre(rs.getString(3));
                cabfact.setTotal(rs.getDouble(4));
                consulta.add(cabfact);
            }
            con.close();
            ps.close();
            rs.close();
            System.out.println(consulta.toString());
            System.out.println("Conexion exitosa con la base de datos");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"ERROR en la conexión con la base de datos");
            e.printStackTrace();
        }
        return consulta;
    }
    public List listar_detallado(String id)
    {

        List<Factura> consulta = new ArrayList<>();
        sql = "SELECT cf.NomCliFact, cf.FecEmiFact, pr.NomProd, pr.DesProd ,df.CanDet, df.ValVenDet, df.TotDet ,cf.IvaFact, cf.TotFact\n" +
                "FROM cajeros as cj, cabecerasfacturas as cf, detallesfacturas as df, productos as pr\n" +
                "WHERE cj.IdCaj = cf.IdCajFact\n" +
                "AND cf.NumFact = df.NumFactDet\n" +
                "AND df.CodProdDet = pr.CodProd\n" +
                "AND cf.IdCajFact = " + id + ";";
        try
        {
            con = conexion.crearConexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next())
            {
                Factura factura = new Factura();
                factura.setNombreCompleto(rs.getString(1));
                factura.setFechaEmi(rs.getString(2));
                factura.setProducto(rs.getString(3));
                factura.setDetalle(rs.getString(4));
                factura.setCantidad(rs.getInt(5));
                factura.setValorVenta(rs.getDouble(6));
                factura.setTotaldetalle(rs.getDouble(7));
                factura.setIva(rs.getDouble(8));
                factura.setTotal(rs.getDouble(9));
                consulta.add(factura);
            }
            con.close();
            ps.close();
            rs.close();
            System.out.println(consulta.toString());
            System.out.println("Conexion exitosa con la base de datos");
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null,"ERROR en la conexión con la base de datos");
            e.printStackTrace();
        }
        return consulta;
    }
    public Factura buscar(String id)
    {
        Factura factura = new Factura();
        sql = "SELECT cj.IdCaj, cj.ApeCaj ,cj.NomCaj, sum(TotFact) as total\n" +
                "FROM cajeros as cj, cabecerasfacturas as cf\n" +
                "WHERE cj.IdCaj = cf.IdCajFact\n" +
                "AND cj.IdCaj = " + id + "\n" +
                "GROUP BY cj.IdCaj, cf.TotFact;";
        try
        {
            con = conexion.crearConexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            rs.next();
            factura.setIdCajero(rs.getString(1));
            factura.setApelllido(rs.getString(2));
            factura.setNombre(rs.getString(3));
            factura.setTotal(rs.getDouble(4));
            con.close();
            ps.close();
            rs.close();
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR en la conexión con la base de datos.");
            e.printStackTrace();
        }
        return factura;
    }
}
