package vistas;

import javax.swing.*;

public class VistaCrudCajeroAdmi extends JFrame{
    public JTextField idtextField;
    public JTextField nombretextField;
    public JTextField apellidotextField;
    public JTextField usuariotextField;
    public JTextField contrasenatextField;
    public JTextField correotextField;
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
