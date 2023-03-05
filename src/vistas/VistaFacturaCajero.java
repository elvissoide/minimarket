package vistas;

import javax.swing.*;

public class VistaFacturaCajero extends JFrame{
    public JTable productosAdquiridostable;
    public JTable registrosProductostable;
    public JTextField nombretextField;
    public JTextField fechatextField;
    public JTextField idcajerotextField;
    public JTextField subtotaltextField;
    public JTextField ivatextField;
    public JTextField totaltextField;
    public JTextField nombreProductotextField;
    public JButton buscarButton;
    public JTextField ciructextFiel;
    public JButton imprimirFacturabutton;
    private JPanel panelFacturacion;
    public JButton seleccionarButton;
    public JTextField cantidadProductotextField;

    public VistaFacturaCajero() {
        setContentPane(panelFacturacion);
        setTitle("Facturación");
        setLocation(100,100);
        setSize(1300,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
