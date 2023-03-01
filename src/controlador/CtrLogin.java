package controlador;

import modelo.CajeroyAdministrador;
import vistas.VistaLogin;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CtrLogin implements ActionListener {
    CajeroyAdministrador cajero = new CajeroyAdministrador();
    CajeroyAdministrador administrador = new CajeroyAdministrador();
    VistaLogin vLogin = new VistaLogin();

    public CtrLogin(VistaLogin vl){
        this.vLogin = vl;
        this.vLogin.ingresarButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        JOptionPane.showMessageDialog(null,"Hola 1");
        if (e.getSource() == vLogin.ingresarButton){
            JOptionPane.showMessageDialog(null,"Hola 2");
        }
    }

}
