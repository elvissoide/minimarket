package vistas;

import javax.swing.*;

public class VistaCrudProductosAdmi extends JFrame {
    private JTextField idtextField_GP;
    private JTextField descripciontextField_GP;
    private JTextField stocktextField_GP;
    private JTextField valventatextField_GP;
    private JTextField proveedortextFiedl_GP;
    public JRadioButton SIRadioButton;
    public JRadioButton NORadioButton;
    public JTable informacionProductos;
    public JButton listarButton_GP;
    public JButton actualizarButton;
    public JButton eliminarButton;
    public JButton guardarButton;
    public JButton seleccionarButton;
    private JPanel Tabla;
    private JTextField nombretextField_GP;
    private JPanel Productos;
    private JPanel Opciones;
    private JButton buscarButton;
    private JPanel panelGestionProductos;

    public VistaCrudProductosAdmi()
    {
        setContentPane(panelGestionProductos);
        pack();
        setTitle("Gestion de Productos");
        setLocation(600,250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
