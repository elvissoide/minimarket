package modelo;

public class Factura
{
    // Cabecera factura
    private String nombreCompleto;
    private int numfact;
    private String idCajero;
    private String nombre;
    private String apelllido;
    private String fechaEmi;
    private double subfact;
    private double iva;
    private double total;
    // Detalle factura

    private int codigoDetalle;
    private int numeroDetalle;
    private int Cantidad;
    private double ValorVenta;
    private double Totaldetalle;

    // Producto
    private String producto;
    private String detalle;

    public Factura()
    {}

    public Factura(String nombreCompleto, int numfact, String idCajero,
                   String nombre, String apelllido, String fechaEmi, double subfact,
                   double iva, double total, int codigoDetalle, int numeroDetalle,
                   int cantidad, double valorVenta, double totaldetalle, String producto,
                   String detalle) {
        this.nombreCompleto = nombreCompleto;
        this.numfact = numfact;
        this.idCajero = idCajero;
        this.nombre = nombre;
        this.apelllido = apelllido;
        this.fechaEmi = fechaEmi;
        this.subfact = subfact;
        this.iva = iva;
        this.total = total;
        this.codigoDetalle = codigoDetalle;
        this.numeroDetalle = numeroDetalle;
        Cantidad = cantidad;
        ValorVenta = valorVenta;
        Totaldetalle = totaldetalle;
        this.producto = producto;
        this.detalle = detalle;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public int getCodigoDetalle() {
        return codigoDetalle;
    }

    public void setCodigoDetalle(int codigoDetalle) {
        this.codigoDetalle = codigoDetalle;
    }

    public int getNumeroDetalle() {
        return numeroDetalle;
    }

    public void setNumeroDetalle(int numeroDetalle) {
        this.numeroDetalle = numeroDetalle;
    }

    public int getCantidad() {
        return Cantidad;
    }

    public void setCantidad(int cantidad) {
        Cantidad = cantidad;
    }

    public double getValorVenta() {
        return ValorVenta;
    }

    public void setValorVenta(double valorVenta) {
        ValorVenta = valorVenta;
    }

    public double getTotaldetalle() {
        return Totaldetalle;
    }

    public void setTotaldetalle(double totaldetalle) {
        Totaldetalle = totaldetalle;
    }

    public int getNumfact() {
        return numfact;
    }

    public void setNumfact(int numfact) {
        this.numfact = numfact;
    }

    public String getIdCajero() {
        return idCajero;
    }

    public void setIdCajero(String idCajero) {
        this.idCajero = idCajero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFechaEmi() {
        return fechaEmi;
    }

    public void setFechaEmi(String fechaEmi) {
        this.fechaEmi = fechaEmi;
    }

    public double getSubfact() {
        return subfact;
    }

    public void setSubfact(double subfact) {
        this.subfact = subfact;
    }

    public double getIva() {
        return iva;
    }

    public void setIva(double iva) {
        this.iva = iva;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getApelllido() {
        return apelllido;
    }

    public void setApelllido(String apelllido) {
        this.apelllido = apelllido;
    }
}
