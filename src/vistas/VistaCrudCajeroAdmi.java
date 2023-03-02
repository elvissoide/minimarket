package vistas;

import javax.swing.*;

public class VistaCrudCajeroAdmi extends JFrame{
    private JTextField idtextField;
    private JTextField nombretextField;
    private JTextField apellidotextField;
    private JTextField usuariotextField;
    private JTextField contrasenatextField;
    private JTextField correotextField;
    public JTable cajerostable;
    public JButton guardarButton;
    public JButton listarButton;
    public JButton seleccionarButton;
    public JButton actualizarButton;
    public JButton eliminarButton;
    public JButton buscarButton;
    private JPanel panelGestionCajeros;
    private JTextField direcciontextField;
    private JTextField telefonotextField;

    public VistaCrudCajeroAdmi(){
        setContentPane(panelGestionCajeros);
        pack();
        setTitle("Gestion de cajeros");
        setLocation(600,250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
