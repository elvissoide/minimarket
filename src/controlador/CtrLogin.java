package controlador;

import modelo.CajeroyAdministrador;
import modelo.DatosCompartidos;
import modelo.ValidacionUsuarios;
import vistas.VistaFacturaCajero;
import vistas.VistaLogin;
import vistas.VistaPrincipalAdmi;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CtrLogin implements ActionListener{
    ValidacionUsuarios validaciondb = new ValidacionUsuarios();
    VistaLogin vLogin;
    DatosCompartidos dc;

    public CtrLogin(VistaLogin vl, DatosCompartidos dc){
        this.vLogin = vl;
        this.dc = dc;
        //Botones
        this.vLogin.ingresarButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if (e.getSource() == vLogin.ingresarButton){
            //CODIGO PARA VALIDAR SI ES CAJERO O ADMINISTRADOR
            System.out.println("Boton ingresar presionado");
            ctrValidacionUsusario();
        }
    }
    private void ctrValidacionUsusario(){
        String rol = (String) vLogin.rolComboBox.getSelectedItem();
        String usuario = vLogin.usuariotextField.getText();
        char[] contra = vLogin.contrasenaField.getPassword();
        String contrasena = new String(contra);

        if (rol.equals("ADMIN")){
            String[] credenciales = validaciondb.validarAdministrador(usuario);
            dc.setNombre(credenciales[2]);
            dc.setCorreo(credenciales[3]);

            if (usuario.equals(credenciales[0]) && contrasena.equals(credenciales[1])){
                VistaPrincipalAdmi vistapa = new VistaPrincipalAdmi(dc);
                vistapa.setVisible(true);
                vLogin.dispose();
            } else{
                JOptionPane.showMessageDialog(null,"Acceso denegado a administrador");
            }

        } else if (rol.equals("CAJERO")){
            String[] credenciales = validaciondb.validarCajero(usuario);
            if (usuario.equals(credenciales[0]) && contrasena.equals(credenciales[1])){
                VistaFacturaCajero vistafc = new VistaFacturaCajero();
                dc.setId(credenciales[2]);
                new CtrFacturacion(vistafc, dc);
                vistafc.setVisible(true);
                vLogin.dispose();
            } else {
                JOptionPane.showMessageDialog(null,"Acceso denegado a cajero");
            }
        }
    }

}