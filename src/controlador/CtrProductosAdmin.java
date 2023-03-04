package controlador;



import modelo.Producto;
import modelo.ProductoCrud;
import vistas.VistaCrudProductosAdmi;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CtrProductosAdmin implements ActionListener
{
    ProductoCrud pcrud = new ProductoCrud();
    VistaCrudProductosAdmi vcrudproductos;
    DefaultTableModel modelo;
    Producto prod = new Producto();

    public CtrProductosAdmin(VistaCrudProductosAdmi vcp)
    {
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
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vcrudproductos.listarButton_GP) {
            listar();
        }
        else if (e.getSource() == vcrudproductos.buscarButton_GP)
        {
            //Ayuda en consola
            System.out.println("Boton activado");
            busqueda_productos();
        }
        else if (e.getSource() == vcrudproductos.seleccionarProductos)
        {
            vcrudproductos.idtextField_GP.setEnabled(false);
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
}
