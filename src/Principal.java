import controlador.CtrGestionCajeros;
import controlador.CtrLogin;
import controlador.CtrProductosAdmin;
import modelo.DatosCompartidos;
import vistas.VistaCrudCajeroAdmi;
import vistas.VistaCrudProductosAdmi;
import vistas.VistaFacturaCajero;
import vistas.VistaLogin;
import controlador.*;

public class Principal {
    public static void main(String[] args) {
        DatosCompartidos dc = new DatosCompartidos();
        VistaLogin vl = new VistaLogin();
        new CtrLogin(vl, dc);
        vl.setVisible(true);
    }
}