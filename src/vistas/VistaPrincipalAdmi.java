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

        this.dc = dc;
        nombreAdministrador.setText(dc.getNombre());
        correoAdministrador.setText(dc.getCorreo());
        System.out.println(dc.getNombre());
        System.out.println(dc.getCorreo());
        ingresarProductosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VistaCrudProductosAdmi vistapp = new VistaCrudProductosAdmi();
                new CtrProductosAdmin(vistapp, dc);
                vistapp.setVisible(true);
                dispose();
            }
        });
        revisarVentasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VistaRevisionVentasAdmi vistav = new VistaRevisionVentasAdmi();
                new CtrVistaVentas(vistav,dc);
                vistav.setVisible(true);
                dispose();
            }
        });
        agregarCajeroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VistaCrudCajeroAdmi vistacc = new VistaCrudCajeroAdmi();
                new CtrGestionCajeros(vistacc, dc);
                vistacc.setVisible(true);
                dispose();
            }
        });
        regresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DatosCompartidos dc = new DatosCompartidos();
                VistaLogin vl = new VistaLogin();
                new CtrLogin(vl, dc);
                vl.setVisible(true);
                dispose();
            }
        });
    }
}
