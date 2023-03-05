import controlador.CtrGestionCajeros;
import controlador.CtrLogin;
import controlador.CtrProductosAdmin;
import controlador.CtrVistaVentas;
import modelo.VistasVentas;
import vistas.VistaCrudCajeroAdmi;
import vistas.VistaCrudProductosAdmi;
import vistas.VistaLogin;
import vistas.VistaRevisionVentasAdmi;

public class Principal {
    public static void main(String[] args) {

        VistaRevisionVentasAdmi vv = new VistaRevisionVentasAdmi();
        new CtrVistaVentas(vv);
        vv.setVisible(true);

        /*
        VistaCrudProductosAdmi vcp = new VistaCrudProductosAdmi();
        new CtrProductosAdmin(vcp);
        vcp.setVisible(true);
         */
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
