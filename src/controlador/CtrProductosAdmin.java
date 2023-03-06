package controlador;



import modelo.DatosCompartidos;
import modelo.Producto;
import modelo.ProductoCrud;
import vistas.VistaCrudProductosAdmi;
import vistas.VistaLogin;
import vistas.VistaPrincipalAdmi;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CtrProductosAdmin implements ActionListener
{
    ProductoCrud pcrud = new ProductoCrud();
    VistaCrudProductosAdmi vcrudproductos;
    DefaultTableModel modelo;
    Producto prod = new Producto();
    DatosCompartidos dc;
    public CtrProductosAdmin(VistaCrudProductosAdmi vcp, DatosCompartidos dc)
    {
        this.dc = dc;
        this.vcrudproductos = vcp;
        this.modelo = new DefaultTableModel();
        this.modelo.addColumn("ID");
        this.modelo.addColumn("Nombre");
        this.modelo.addColumn("Descripcion");
        this.modelo.addColumn("Stock");
        this.modelo.addColumn("Valor de Venta");
        this.modelo.addColumn("IVA");
        this.modelo.addColumn("Proveedor");
        this.vcrudproductos.informacionProductos.setModel(this.modelo);
        this.vcrudproductos.listarButton_GP.addActionListener(this);
        this.vcrudproductos.buscarButton_GP.addActionListener(this);
        this.vcrudproductos.seleccionarProductos.addActionListener(this);
        this.vcrudproductos.agregarButton_GP.addActionListener(this);
        this.vcrudproductos.actualizarButton_GP.addActionListener(this);
        this.vcrudproductos.eliminarButton_GP.addActionListener(this);
        this.vcrudproductos.limpiarButton_GP.addActionListener(this);
        this.vcrudproductos.regresarButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vcrudproductos.listarButton_GP)
            listar();
        else if (e.getSource() == vcrudproductos.buscarButton_GP)
            busqueda_productos();
        else if (e.getSource() == vcrudproductos.seleccionarProductos)
            seleccionar_producto();
        else if (e.getSource() == vcrudproductos.agregarButton_GP)
            agregar_prodcuto();
        else if (e.getSource() == vcrudproductos.actualizarButton_GP)
            actualizar_productos();
        else if (e.getSource() == vcrudproductos.eliminarButton_GP)
        {
            eliminar_producto();
            limpiar();
        }
        else if (e.getSource() == vcrudproductos.limpiarButton_GP)
            limpiar();
        else if (e.getSource() == vcrudproductos.regresarButton) {
            //DatosCompartidos dc = new DatosCompartidos();
            VistaPrincipalAdmi vpa = new VistaPrincipalAdmi(dc);
            vpa.setVisible(true);
            vcrudproductos.dispose();
        }
    }

    public void listar()
    {
        List <Producto> lista = pcrud.listar();
        Object[] objeto = new Object[7];
        modelo.setRowCount(0);
        for (int i = 0; i < lista.size(); i++)
        {
            objeto[0] = lista.get(i).getId();
            objeto[1] = lista.get(i).getNombre();
            objeto[2] = lista.get(i).getDescripcion();
            objeto[3] = lista.get(i).getValorVenta();
            objeto[4] = lista.get(i).getStock();
            objeto[5] = lista.get(i).isIva();
            objeto[6] = lista.get(i).getProveedor();
            modelo.addRow(objeto);
        }
    }
    public void busqueda_productos()
    {
        String id = vcrudproductos.idtextField_GP.getText();
        Producto prod = pcrud.buscar(Integer.parseInt(id));
        Object[] objeto = new Object[7];
        modelo.setRowCount(0);
        objeto[0] = prod.getId();
        objeto[1] = prod.getNombre();
        objeto[2] = prod.getDescripcion();
        objeto[3] = prod.getValorVenta();
        objeto[4] = prod.getStock();
        objeto[5] = prod.isIva();
        objeto[6] = prod.getProveedor();
        modelo.addRow(objeto);
        System.out.println("Ejecucion exitosa!!");
    }
    public void seleccionar_producto()
    {
        vcrudproductos.idtextField_GP.setEditable(false);
        int fila = vcrudproductos.informacionProductos.getSelectedRow();
        if (fila == -1)
            JOptionPane.showMessageDialog(null,"Debe seleccionar una fila");
        else
        {
            int id = Integer.parseInt(String.valueOf(vcrudproductos.informacionProductos.getValueAt(fila, 0)));
            String nombre = String.valueOf(vcrudproductos.informacionProductos.getValueAt(fila, 1));
            String descripcion = String.valueOf(vcrudproductos.informacionProductos.getValueAt(fila, 2));
            double valventa = Double.parseDouble(String.valueOf(vcrudproductos.informacionProductos.getValueAt(fila, 3)));
            int stock = Integer.parseInt(String.valueOf(vcrudproductos.informacionProductos.getValueAt(fila, 4)));
            boolean iva = Boolean.parseBoolean(String.valueOf(vcrudproductos.informacionProductos.getValueAt(fila, 5)));
            String proveedor = String.valueOf(vcrudproductos.informacionProductos.getValueAt(fila, 6));
            vcrudproductos.idtextField_GP.setText(String.valueOf(id));
            vcrudproductos.nombretextField_GP.setText(nombre);
            vcrudproductos.descripciontextField_GP.setText(descripcion);
            vcrudproductos.stocktextField_GP.setText(String.valueOf(stock));
            vcrudproductos.valventatextField_GP.setText(String.valueOf(valventa));
            vcrudproductos.proveedortextFiedl_GP.setText(proveedor);
            if (iva == true)
                vcrudproductos.SIRadioButton.setSelected(true);
            else
                vcrudproductos.NORadioButton.setSelected(true);
        }
    }
    public void agregar_prodcuto()
    {
        String id = vcrudproductos.idtextField_GP.getText();
        String nombre = vcrudproductos.nombretextField_GP.getText();
        String descripcion = vcrudproductos.descripciontextField_GP.getText();
        double valventa = Double.parseDouble(vcrudproductos.valventatextField_GP.getText());
        int stock = Integer.parseInt(vcrudproductos.stocktextField_GP.getText());
        boolean iva = false;
        if (vcrudproductos.SIRadioButton.isSelected())
            iva = true;
        else if (vcrudproductos.NORadioButton.isSelected())
            iva = false;
        String proveedor = vcrudproductos.descripciontextField_GP.getText();
        prod.setId(Integer.parseInt(id));
        prod.setNombre(nombre);
        prod.setDescripcion(descripcion);
        prod.setValorVenta(valventa);
        prod.setStock(stock);
        prod.setIva(iva);
        prod.setProveedor(proveedor);
        if (pcrud.agregar(prod) == 1)
            JOptionPane.showMessageDialog(null, "Producto agregado con éxito.");
        else
            JOptionPane.showMessageDialog(null, "Error, no se pudo agregar el porducto.");
    }
    public void actualizar_productos()
    {
        prod.setId(Integer.parseInt(vcrudproductos.idtextField_GP.getText()));
        prod.setNombre(vcrudproductos.nombretextField_GP.getText());
        prod.setDescripcion(vcrudproductos.descripciontextField_GP.getText());
        prod.setStock(Integer.parseInt(vcrudproductos.stocktextField_GP.getText()));
        prod.setValorVenta(Double.parseDouble(vcrudproductos.valventatextField_GP.getText()));
        boolean iva = false;
        if (vcrudproductos.SIRadioButton.isSelected())
            iva = true;
        else if (vcrudproductos.NORadioButton.isSelected())
            iva = false;
        prod.setIva(iva);
        prod.setProveedor(vcrudproductos.proveedortextFiedl_GP.getText());
        if (pcrud.actualizar(prod) == 1)
            JOptionPane.showMessageDialog(null, "Prodcuto actualizado con éxito");
         else
            JOptionPane.showMessageDialog(null, "Error, no se pudo actualizar el Producto");
    }
    public void eliminar_producto()
    {
        int fila = vcrudproductos.informacionProductos.getSelectedRow();
        if (fila == -1)
            JOptionPane.showMessageDialog(null,
                    "Por favor, seleccione una fila.");
        else
        {
            int id = (int) vcrudproductos.informacionProductos.getValueAt(fila, 0);
            pcrud.eliminar(id);
            JOptionPane.showMessageDialog(null,
                    "Producto con id " + id + " eliminado exitosamente");
        }
    }
    public void limpiar()
    {
        vcrudproductos.idtextField_GP.setText("");
        vcrudproductos.nombretextField_GP.setText("");
        vcrudproductos.descripciontextField_GP.setText("");
        vcrudproductos.valventatextField_GP.setText("");
        vcrudproductos.stocktextField_GP.setText("");
        if (vcrudproductos.SIRadioButton.isSelected())
            vcrudproductos.SIRadioButton.setSelected(false);
        else if (vcrudproductos.NORadioButton.isSelected())
            vcrudproductos.NORadioButton.setSelected(false);
        vcrudproductos.proveedortextFiedl_GP.setText("");
        vcrudproductos.idtextField_GP.setEditable(true);
    }
}
