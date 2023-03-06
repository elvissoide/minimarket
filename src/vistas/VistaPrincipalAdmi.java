package vistas;

import controlador.CtrGestionCajeros;
import controlador.CtrLogin;
import controlador.CtrProductosAdmin;
import controlador.CtrVistaVentas;
import modelo.DatosCompartidos;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VistaPrincipalAdmi extends JFrame{
    private JPanel panelPrincipalAdmi;
    private JLabel nombreAdministrador;
    private JLabel correoAdministrador;
    private JButton ingresarProductosButton;
    private JButton revisarVentasButton;
    private JButton agregarCajeroButton;
    private JButton regresarButton;
    private DatosCompartidos dc;
    public VistaPrincipalAdmi(DatosCompartidos dc){
        setContentPane(panelPrincipalAdmi);
        pack();
        setTitle("Rol administrador");
        setLocation(600,250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
