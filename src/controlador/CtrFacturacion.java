package controlador;
import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfPCell;
import modelo.Factura;
// Importar librerias necesarias para escribir el PDF
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import modelo.DatosCompartidos;
import modelo.Producto;
import modelo.ProductoFactura;
import vistas.VistaFacturaCajero;
import vistas.VistaLogin;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class CtrFacturacion implements ActionListener {
    VistaFacturaCajero vistafc;
    DefaultTableModel modeloFactura;
    DefaultTableModel modeloProductos;
    ProductoFactura prfact = new ProductoFactura();
    DatosCompartidos dc;

    public CtrFacturacion(VistaFacturaCajero vfc, DatosCompartidos dc){
        this.vistafc = vfc;
        this.dc = dc;
        //Tabla de facturacion
        this.modeloFactura = new DefaultTableModel();
        this.modeloFactura.addColumn("Codigo");
        this.modeloFactura.addColumn("Descripcion");
        this.modeloFactura.addColumn("Cantidad");
        this.modeloFactura.addColumn("Precio");
        this.modeloFactura.addColumn("Total");
        this.modeloFactura.addColumn("IVA");
        this.vistafc.productosAdquiridostable.setModel(modeloFactura);
        //Tabla de busqueda de productos
        this.modeloProductos = new DefaultTableModel();
        this.modeloProductos.addColumn("Codigo");
        this.modeloProductos.addColumn("Descripcion");
        this.modeloProductos.addColumn("Stock");
        this.modeloProductos.addColumn("Precio");
        this.vistafc.registrosProductostable.setModel(modeloProductos);
        //this.vistafc.productosAdquiridostable.getColumnModel().getColumn(1).setMinWidth(100);
        //Botones
        this.vistafc.buscarButton.addActionListener(this);
        this.vistafc.seleccionarButton.addActionListener(this);
        this.vistafc.imprimirFacturabutton.addActionListener(this);
        this.vistafc.regresarButton.addActionListener(this);
        this.vistafc.nuevaFacturaButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e){


        if (e.getSource() == vistafc.buscarButton){
            System.out.println("Boton buscar presionado");
            ctrlistarProductosRegistrados();


        } else if (e.getSource() == vistafc.seleccionarButton) {
            System.out.println("Boton seleccionar presionado");
            int fila = vistafc.registrosProductostable.getSelectedRow();
            vistafc.subtotaltextField.setEnabled(false);
            vistafc.ivatextField.setEnabled(false);
            vistafc.totaltextField.setEnabled(false);
            vistafc.idcajerotextField.setEnabled(false);
            //Borrar esta parte
            vistafc.ciructextFiel.setText("1750734129");
            vistafc.nombretextField.setText("Elvis Guanoluisa");
            //vistafc.idcajerotextField.setText("1");
            vistafc.idcajerotextField.setText(dc.getId());

            if (fila == -1 || vistafc.cantidadProductotextField.getText().equals("")) {
                JOptionPane.showMessageDialog(null,
                        "Debe seleccionar una fila e ingresar una cantidad deseada");

            } else {
                int idProducto = (int) vistafc.registrosProductostable.getValueAt(fila,0);
                int cantidadProducto = Integer.parseInt(vistafc.cantidadProductotextField.getText());
                ctrlistarProductosComprados(prfact.agregarProductoComprado(idProducto, cantidadProducto));
                ctrcalcularValores(fila);
            }

        } else if (e.getSource() == vistafc.imprimirFacturabutton) {
            JOptionPane.showMessageDialog(null,
                    "Imprimiendo Factura...");
            System.out.println("Boton imprimir facturado presionado");
            ctractualizarRegistros();
        } else if (e.getSource() == vistafc.regresarButton) {
            DatosCompartidos dc = new DatosCompartidos();
            VistaLogin vl = new VistaLogin();
            new CtrLogin(vl, dc);
            vl.setVisible(true);
            vistafc.dispose();
        } else if (e.getSource() == vistafc.nuevaFacturaButton) {
            System.out.println("Limpieza de registros");
            modeloFactura.setRowCount(0);
            vistafc.fechatextField.setText("");
            vistafc.ciructextFiel.setText("");
            vistafc.nombretextField.setText("");

        }
    }


    public void ctrlistarProductosRegistrados(){
        String nombre = vistafc.nombreProductotextField.getText();
        List<Producto> lista = prfact.listarProductosEncontrados(nombre);
        Object[] objeto = new Object[4];
        modeloProductos.setRowCount(0);
        for (int i=0; i<lista.size(); i++){
            objeto[0] = lista.get(i).getId();
            objeto[1] = lista.get(i).getDescripcion();
            objeto[2] = lista.get(i).getStock();
            objeto[3] = lista.get(i).getValorVenta();
            modeloProductos.addRow(objeto);
        }
        modeloProductos.setColumnIdentifiers(new String[]{"ID", "Descripcion", "Stock", "Precio"});
        TableColumnModel columnModel = vistafc.registrosProductostable.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(16);
        columnModel.getColumn(1).setPreferredWidth(300);
        columnModel.getColumn(2).setPreferredWidth(50);
        columnModel.getColumn(3).setPreferredWidth(50);
    }
    public void ctrlistarProductosComprados(Object producto){
        modeloFactura.addRow((Object[]) producto);
        modeloFactura.setColumnIdentifiers(new String[]{"ID", "Descripcion", "Cantidad", "Precio","Total","Iva"});
        TableColumnModel columnModel = vistafc.productosAdquiridostable.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(16);
        columnModel.getColumn(1).setPreferredWidth(300);
        columnModel.getColumn(2).setPreferredWidth(50);
        columnModel.getColumn(3).setPreferredWidth(50);
        columnModel.getColumn(4).setPreferredWidth(50);
        columnModel.getColumn(5).setPreferredWidth(50);
    }
    public void ctrcalcularValores(int fila){
        double iva = 0.0;
        double subtotal = 0.0;
        double total = 0.0;
        double ivaProducto;
        double subtotalProducto;
        int cantidadFilas = vistafc.productosAdquiridostable.getRowCount();
        LocalDate fechaActual = LocalDate.now();
        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String fechaFormateada = fechaActual.format(formatoFecha);

        for (int i=0; i<cantidadFilas; i++){
            System.out.println("RESULTADOS");
            ivaProducto = Double.parseDouble(vistafc.productosAdquiridostable.getValueAt(i,5).toString());
            System.out.println(ivaProducto);
            subtotalProducto = Double.parseDouble(vistafc.productosAdquiridostable.getValueAt(i,4).toString());
            System.out.println(subtotalProducto);
            iva += ivaProducto;
            subtotal += subtotalProducto;
            total = iva + subtotal;
        }
        iva = Math.round(iva*100.0)/100.0;
        subtotal = Math.round(subtotal*100.0)/100.0;
        total = Math.round(total*100.0)/100.0;
        vistafc.ivatextField.setText(String.valueOf(iva));
        vistafc.subtotaltextField.setText(String.valueOf(subtotal));
        vistafc.totaltextField.setText(String.valueOf(total));
        vistafc.fechatextField.setEnabled(false);
        vistafc.fechatextField.setText(fechaFormateada);
    }
    public void ctractualizarRegistros(){
        // BORRAR ESTA LINEA

        Object[] valores = new Object[6];
        valores[0] = vistafc.idcajerotextField.getText();
        valores[1] = vistafc.nombretextField.getText();
        valores[2] = vistafc.fechatextField.getText();
        valores[3] = vistafc.subtotaltextField.getText();
        valores[4] = vistafc.ivatextField.getText();
        valores[5] = vistafc.totaltextField.getText();
        prfact.insertarRegistroCabecera(valores);

        int numeroFactura = prfact.obtenerNumFac();
        if (numeroFactura == -1){
            JOptionPane.showMessageDialog(null,
                    "ERROR, no se encuentra el numero de factura.");
        } else {
            System.out.println("Valor: " + numeroFactura);
        }

        int cantidadFilas = vistafc.productosAdquiridostable.getRowCount();
        for (int i=0; i < cantidadFilas; i++){
            String idp = vistafc.productosAdquiridostable.getValueAt(i, 0).toString();
            String cantidad = vistafc.productosAdquiridostable.getValueAt(i, 2).toString();
            String precio = vistafc.productosAdquiridostable.getValueAt(i, 3).toString();
            String total = vistafc.productosAdquiridostable.getValueAt(i, 4).toString();
            prfact.insertarRegistrosDetalles(numeroFactura, idp, cantidad, precio, total);
        }

    }
    public void ctrGenerarDocumento() {
        // Creacion del documento para la creacion del PDF
        Document documento = new Document();

        try
        {
            //Formaetar el PDF
            // Creacer una Imagen desde la propia liberia para exportar PDF's
            Image header = Image.getInstance("src/img/buho.png");
            // Tamaño
            header.scaleAbsolute(200, 150);
            // Alinacion de la imagen
            header.setAlignment(Chunk.ALIGN_CENTER);

            // Objeto para parrafos propio de la liberia
            Paragraph parrafos = new Paragraph();
            Paragraph datos = new Paragraph();
            // Alineacion
            parrafos.setAlignment(Paragraph.ALIGN_CENTER);
            parrafos.add("El Buho \n" +
                    "MINIMARKET\n\n");
            // Fuente
            parrafos.setFont(FontFactory.getFont("Tahoma", 20, Font.BOLD, BaseColor.BLACK));

            // Ruta donde se guardara el documento
            String ruta = System.getProperty("user.home");
            // Ruta absoulta
            PdfWriter.getInstance(documento, new FileOutputStream(ruta + "/Downloads/reporte_prueba.pdf" ));
            documento.open();
            // Creacion de las tablas para gurdar los datos
            PdfPTable tabla = new PdfPTable(4);
            PdfPTable tablaPago = new PdfPTable(3);
            // Nombre de las columnas
            tabla.addCell("Cantidad");
            tabla.addCell("Descripcion");
            tabla.addCell("Precio Unitario");
            tabla.addCell("Valor Total");
            tablaPago.addCell("Subtotal");
            tablaPago.addCell("IVA");
            tablaPago.addCell("Total");

            // Contador de las columnas de la tabla donde se encuentran los registros
            // de las compras.
            int filas = modeloFactura.getRowCount();
            for (int i = 0; i < filas; i++)
            {
                String cantidad = String.valueOf(vistafc.productosAdquiridostable.getValueAt(i, 2));
                String detalle = String.valueOf(vistafc.productosAdquiridostable.getValueAt(i, 1));
                Double precioUnit = Double.parseDouble(String.valueOf(vistafc.productosAdquiridostable.getValueAt(i, 3)));
                Double total = Double.parseDouble(String.valueOf(vistafc.productosAdquiridostable.getValueAt(i, 4)));
                // Añadir los datos a las celdas correspondientes
                tabla.addCell(String.valueOf(cantidad));
                tabla.addCell(detalle);
                tabla.addCell(String.valueOf(precioUnit));
                tabla.addCell(String.valueOf(total));
            }
            // Añadir los datos a las celdas correspondientes
            // Obtiene los datos desde los componentes de la vista
            datos.setAlignment(Paragraph.ALIGN_LEFT);
            datos.add("Sr: " + vistafc.nombretextField.getText() + "\n");
            datos.add("R.U.C/C.I: " + vistafc.ciructextFiel.getText() + "\n");
            datos.add("Fecha: " + vistafc.fechatextField.getText() + "\n\n");
            datos.setFont(FontFactory.getFont("Tahoma", 18, Font.NORMAL, BaseColor.DARK_GRAY));

            tablaPago.addCell(vistafc.subtotaltextField.getText());
            tablaPago.addCell(vistafc.ivatextField.getText());
            tablaPago.addCell(vistafc.totaltextField.getText());
            // Agregar todos los objetos al documento
            documento.add(header);
            documento.add(parrafos);
            documento.add(datos);
            documento.add(tabla);
            documento.add(tablaPago);
            documento.close();

        } catch (DocumentException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}