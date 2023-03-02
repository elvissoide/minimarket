package controlador;



import modelo.Producto;
import modelo.ProductoCrud;
import vistas.VistaCrudProductosAdmi;

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
        this.vcrudproductos.informacionProductos.setModel(this.modelo);
        this.vcrudproductos.listarButton_GP.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vcrudproductos.listarButton_GP)
            listar();
    }

    public void listar()
    {
        List <Producto> lista = pcrud.listar();
        Object[] objeto = new Object[6];
        modelo.setRowCount(0);
        for (int i = 0; i < lista.size(); i++)
        {
            objeto[0] = lista.get(i).getId();
            objeto[1] = lista.get(i).getNombre();
            objeto[2] = lista.get(i).getDescripcion();
            objeto[3] = lista.get(i).getValorVenta();
            objeto[4] = lista.get(i).getStock();
            objeto[5] = lista.get(i).getProveedor();
            modelo.addRow(objeto);
        }
    }
}
