import controlador.CtrGestionCajeros;
import controlador.CtrLogin;
import controlador.CtrProductosAdmin;
import vistas.VistaCrudCajeroAdmi;
import vistas.VistaCrudProductosAdmi;
import vistas.VistaLogin;

public class Principal {
    public static void main(String[] args) {

        VistaCrudProductosAdmi vcp = new VistaCrudProductosAdmi();
        new CtrProductosAdmin(vcp);
        vcp.setVisible(true);

        /*
        VistaCrudCajeroAdmi vcc = new VistaCrudCajeroAdmi();
        new CtrGestionCajeros(vcc);
        vcc.setVisible(true);
         */
        /*
        VistaLogin vl = new VistaLogin();
        new CtrLogin(vl);
        vl.setVisible(true);
         */
    }
}
