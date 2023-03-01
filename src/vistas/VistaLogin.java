package vistas;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VistaLogin extends JFrame{
    public JPanel panelLogin;
    public JTextField textField2;
    public JButton ingresarButton;
    public JComboBox cajeroAdminComboBox;
    public JLabel imagenBuho;
    public JPasswordField passwordField1;

    public VistaLogin() {
        setContentPane(panelLogin);
        pack();
        setTitle("Ingreso de credenciales");
        setLocation(600,250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ingresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

}
