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
        this.vcrudcajero.buscarButton.addActionListener(this);
        this.vcrudcajero.eliminarButton.addActionListener(this);
        this.vcrudcajero.agregarButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if (e.getSource() == vcrudcajero.listarButton){
            ctrlistar();
        }else if (e.getSource() == vcrudcajero.buscarButton) {
            System.out.println("Boton buscar presionado");
            ctrbuscar();
        }
        else if (e.getSource() == vcrudcajero.eliminarButton) {
            System.out.println("Boton eliminar presionado");
            ctreliminar();
        }else if (e.getSource() == vcrudcajero.agregarButton) {
            System.out.println("Boton agregar presionado");
            ctragregar();
        }
    }

    public void ctrlistar(){
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

    public void ctrbuscar(){
        String id = vcrudcajero.idtextField.getText();
        CajeroyAdministrador cya = ccrud.buscar(Integer.parseInt(id));
        Object[] objeto = new Object[8];
        modelo.setRowCount(0);
        objeto[0] = cya.getId();
        objeto[1] = cya.getNombre();
        objeto[2] = cya.getApellido();
        objeto[3] = cya.getDireccion();
        objeto[4] = cya.getCorreo();
        objeto[5] = cya.getTelefono();
        objeto[6] = cya.getUsuario();
        objeto[7] = cya.getContrasena();
        modelo.addRow(objeto);
        System.out.println("Ejecucion exitosa");
    }
    public void ctreliminar(){
        int fila = vcrudcajero.cajerostable.getSelectedRow();
        if (fila == -1){
            JOptionPane.showMessageDialog(null,
                    "Por favor, seleccione una fila.");
        } else {
            int id = (int) vcrudcajero.cajerostable.getValueAt(fila,0);
            ccrud.eliminar(id);
            JOptionPane.showMessageDialog(null,
                    "Usuario con id " + id + "eliminado exitosamente");
        }
    }
    public void ctragregar(){
        //String id = vcrudcajero.idtextField.getText();
        String nombre = vcrudcajero.nombretextField.getText();
        String apellido = vcrudcajero.apellidotextField.getText();
        String direccion = vcrudcajero.direcciontextField.getText();
        String correo = vcrudcajero.correotextField.getText();
        String telefono = vcrudcajero.telefonotextField.getText();
        String usuario = vcrudcajero.usuariotextField.getText();
        String contrasena = vcrudcajero.contrasenatextField.getText();
        cya.setNombre(nombre);
        cya.setApellido(apellido);
        cya.setDireccion(direccion);
        cya.setCorreo(correo);
        cya.setTelefono(telefono);
        cya.setUsuario(usuario);
        cya.setContrasena(contrasena);
        if (ccrud.agregar(cya) == 1){
            JOptionPane.showMessageDialog(null, "Usuario agregado con éxito.");
        }else {
            JOptionPane.showMessageDialog(null, "Error, no se pudo agregar el usuario.");
        }
    }

}
