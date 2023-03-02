package controlador;

import modelo.CajeroCrud;
import modelo.CajeroyAdministrador;
import vistas.VistaCrudCajeroAdmi;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CtrGestionCajeros implements ActionListener {
    CajeroCrud ccrud = new CajeroCrud();
    VistaCrudCajeroAdmi vcrudcajero; //Vista
    DefaultTableModel modelo;//Modelo para JTable
    CajeroyAdministrador cya = new CajeroyAdministrador();

    public CtrGestionCajeros(VistaCrudCajeroAdmi vcc){
        this.vcrudcajero = vcc;
        this.modelo = new DefaultTableModel();
        this.modelo.addColumn("ID");
        this.modelo.addColumn("Nombre");
        this.modelo.addColumn("Apellido");
        this.modelo.addColumn("Dirección");
        this.modelo.addColumn("Correo");
        this.modelo.addColumn("Teléfono");
        this.modelo.addColumn("Usuario");
        this.modelo.addColumn("Contraseña");
        this.vcrudcajero.cajerostable.setModel(this.modelo);
        this.vcrudcajero.listarButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if (e.getSource() == vcrudcajero.listarButton){
            listar();
        }
    }

    public void listar(){
        List <CajeroyAdministrador> lista = ccrud.listar();
        Object[] objeto = new Object[8];
        modelo.setRowCount(0);
        for (int i=0; i<lista.size();i++){
            objeto[0] = lista.get(i).getId();
            objeto[1] = lista.get(i).getNombre();
            objeto[2] = lista.get(i).getApellido();
            objeto[3] = lista.get(i).getDireccion();
            objeto[4] = lista.get(i).getCorreo();
            objeto[5] = lista.get(i).getTelefono();
            objeto[6] = lista.get(i).getUsuario();
            objeto[7] = lista.get(i).getContrasena();
            modelo.addRow(objeto);
        }
    }
}
