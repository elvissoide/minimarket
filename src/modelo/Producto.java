package modelo;

public class Producto {
    private int id;
    private String nombre;
    private String descripcion;
    private int stock;
    private double valorVenta;
    private boolean iva;
    private String proveedor;

    public Producto()
    {}

    public Producto(int id, String nombre, String descripcion,
                    int stock, double valorVenta, boolean iva, String proveedor)
    {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.stock = stock;
        this. valorVenta = valorVenta;
        this.iva = iva;
        this.proveedor = proveedor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getValorVenta() {
        return valorVenta;
    }

    public void setValorVenta(Double valorVenta) {
        this.valorVenta = valorVenta;
    }

    public boolean isIva() {
        return iva;
    }

    public void setIva(boolean iva) {
        this.iva = iva;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }
}
