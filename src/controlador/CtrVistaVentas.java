package controlador;

import modelo.DatosCompartidos;
import modelo.Factura;
import modelo.VistasVentas;
import vistas.VistaPrincipalAdmi;
import vistas.VistaRevisionVentasAdmi;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DatabaseMetaData;
import java.util.List;


public class CtrVistaVentas implements ActionListener
{
    VistasVentas vistasVentas = new VistasVentas();
    Factura cafactu = new Factura();
    VistaRevisionVentasAdmi vvistasventas;
    DefaultTableModel modelo;
    DefaultTableModel modeloDetalle;
    DatosCompartidos dc;

    public CtrVistaVentas(VistaRevisionVentasAdmi vrv, DatosCompartidos dc)
    {
        this.dc = dc;
        this.vvistasventas = vrv;
        this.modelo = new DefaultTableModel();
        this.modelo.addColumn("ID");
        this.modelo.addColumn("Apellido");
        this.modelo.addColumn("Nombre");
        this.modelo.addColumn("Total");
        this.vvistasventas.vista_sencilla.setModel(this.modelo);
        this.modeloDetalle = new DefaultTableModel();
        this.modeloDetalle.addColumn("Nombre Completo");
        this.modeloDetalle.addColumn("Fecha");
        this.modeloDetalle.addColumn("Producto");
        this.modeloDetalle.addColumn("Detalle");
        this.modeloDetalle.addColumn("Cantidad");
        this.modeloDetalle.addColumn("Valor venta");
        this.modeloDetalle.addColumn("Subtotal");
        this.modeloDetalle.addColumn("IVA");
        this.modeloDetalle.addColumn("Total");
        this.vvistasventas.vista_detallada.setModel(this.modeloDetalle);
        this.vvistasventas.listar_ventas.addActionListener(this);
        this.vvistasventas.seleccionarButton.addActionListener(this);
        this.vvistasventas.limpiarButton.addActionListener(this);
        this.vvistasventas.buscar_ventas.addActionListener(this);
        this.vvistasventas.regresarButton.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vvistasventas.listar_ventas)
            listar_sencilla();
        else if (e.getSource() == vvistasventas.seleccionarButton) {
            seleccionarCajero();
            listar_deetalle();
        }
        else if (e.getSource() == vvistasventas.limpiarButton)
            limpiar();
        else if (e.getSource() == vvistasventas.buscar_ventas)
            buscar_Cajero();
        else if (e.getSource() == vvistasventas.regresarButton) {
            VistaPrincipalAdmi vpa = new VistaPrincipalAdmi(dc);
            vpa.setVisible(true);
            vvistasventas.dispose();
        }
    }
    public void listar_sencilla()
    {
        List <Factura> lista = vistasVentas.listar_sencillo();
        Object[] objeto = new Object[4];
        modelo.setRowCount(0);
        for (int i = 0; i < lista.size(); i++)
        {
            objeto[0] = lista.get(i).getIdCajero();
            objeto[1] = lista.get(i).getApelllido();
            objeto[2] = lista.get(i).getNombre();
            objeto[3] = lista.get(i).getTotal();
            modelo.addRow(objeto);
        }
    }
    public void listar_deetalle()
    {
        List <Factura> lista = vistasVentas.listar_detallado(vvistasventas.codtexfield_vt.getText());
        Object[] objeto = new Object[9];
        modeloDetalle.setRowCount(0);
        for (int i = 0; i < lista.size(); i++)
        {
            objeto[0] = lista.get(i).getNombreCompleto();
            objeto[1] = lista.get(i).getFechaEmi();
            objeto[2] = lista.get(i).getProducto();
            objeto[3] = lista.get(i).getDetalle();
            objeto[4] = lista.get(i).getCantidad();
            objeto[5] = lista.get(i).getValorVenta();
            objeto[6] = lista.get(i).getTotaldetalle();
            objeto[7] = lista.get(i).getIva();
            objeto[8] = lista.get(i).getTotal();
            modeloDetalle.addRow(objeto);
        }
    }
    public void seleccionarCajero()
    {
        vvistasventas.codtexfield_vt.setEditable(false);
        int fila= vvistasventas.vista_sencilla.getSelectedRow();
        if (fila == -1)
            JOptionPane.showMessageDialog(null,"Debe seleccionar una fila");
        else
        {
            int id = Integer.parseInt(String.valueOf(vvistasventas.vista_sencilla.getValueAt(fila, 0)));
            String nombre = String.valueOf(vvistasventas.vista_sencilla.getValueAt(fila, 1));
            String apellido = String.valueOf(vvistasventas.vista_sencilla.getValueAt(fila, 2));
            vvistasventas.codtexfield_vt.setText(String.valueOf(id));
            vvistasventas.apellidotextfield_vt.setText(apellido);
            vvistasventas.nombretextfield_vt.setText(nombre);
        }
    }
    public void buscar_Cajero()
    {
        String id = vvistasventas.codtexfield_vt.getText();
        Factura factura = vistasVentas.buscar(id);
        Object[] objeto = new Object[4];
        modelo.setRowCount(0);
        objeto[0] = factura.getIdCajero();
        objeto[1] = factura.getApelllido();
        objeto[2] = factura.getNombre();
        objeto[3] = factura.getTotal();
        modelo.addRow(objeto);
        System.out.println("Ejecucion exitosa!!");
    }
    public void limpiar()
    {
        vvistasventas.codtexfield_vt.setText("");
        vvistasventas.apellidotextfield_vt.setText("");
        vvistasventas.nombretextfield_vt.setText("");
        vvistasventas.codtexfield_vt.setEditable(true);
    }
}
