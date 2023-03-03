package controlador;

import modelo.CajeroyAdministrador;
import vistas.VistaLogin;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CtrLogin implements ActionListener{
    CajeroyAdministrador cajero = new CajeroyAdministrador();
    CajeroyAdministrador administrador = new CajeroyAdministrador();
    VistaLogin vLogin;

    public CtrLogin(VistaLogin vl){
        this.vLogin = vl;
        this.vLogin.ingresarButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if (e.getSource() == vLogin.ingresarButton){
            //CODIGO PARA VALIDAR CAJERO O ADMINISTRADOR

        }
    }

}
