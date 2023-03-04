package vistas;

import javax.swing.*;

public class VistaCrudProductosAdmi extends JFrame {
    public JTextField idtextField_GP;
    public JTextField descripciontextField_GP;
    public JTextField stocktextField_GP;
    public JTextField valventatextField_GP;
    public JTextField proveedortextFiedl_GP;
    public JRadioButton SIRadioButton;
    public JRadioButton NORadioButton;
    public JTable informacionProductos;
    public JButton listarButton_GP;
    public JButton actualizarButton;
    public JButton eliminarButton;
    public JButton guardarButton;
    public JButton seleccionarProductos;
    public JPanel Tabla;
    public JTextField nombretextField_GP;
    private JPanel Productos;
    private JPanel Opciones;
    public JButton buscarButton_GP;
    private JPanel panelGestionProductos;
    private ButtonGroup Iva_group;

    public VistaCrudProductosAdmi()
    {
        setContentPane(panelGestionProductos);
        pack();
        setTitle("Gestion de Productos");
        setLocation(600,250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
