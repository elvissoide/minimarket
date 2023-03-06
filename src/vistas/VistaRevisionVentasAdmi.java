package vistas;

import javax.swing.*;

public class VistaRevisionVentasAdmi extends JFrame{
    public JPanel principal_vista;
    public JTable vista_sencilla;
    public JTextField codtexfield_vt;
    public JTextField nombretextfield_vt;
    public JTextField apellidotextfield_vt;
    private JPanel tablas;
    public JTable vista_detallada;
    private JPanel Cajero;
    private JPanel Opciones;
    public JButton buscar_ventas;
    public JButton listar_ventas;
    public JButton seleccionarButton;
    public JButton limpiarButton;
    public JButton regresarButton;

    public VistaRevisionVentasAdmi()
    {
        setContentPane(principal_vista);
        pack();
        setTitle("Vista de ventas");
        setLocation(600,200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
