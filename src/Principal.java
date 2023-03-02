import controlador.CtrGestionCajeros;
import controlador.CtrLogin;
import vistas.VistaCrudCajeroAdmi;
import vistas.VistaLogin;

public class Principal {
    public static void main(String[] args) {
        VistaCrudCajeroAdmi vcc = new VistaCrudCajeroAdmi();
        new CtrGestionCajeros(vcc);
        vcc.setVisible(true);
        /*
        VistaLogin vl = new VistaLogin();
        new CtrLogin(vl);
        vl.setVisible(true);
         */
    }
}
