package vistas;
import javax.swing.*;

public class VistaLogin extends JFrame{
    public JPanel panelLogin;
    public JTextField usuariotextField;
    public JButton ingresarButton;
    public JComboBox rolComboBox;
    public JLabel imagenBuho;
    public JPasswordField contrasenaField;

    public VistaLogin() {
        setContentPane(panelLogin);
        pack();
        setTitle("Ingreso de credenciales");
        setLocation(600,250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

}
