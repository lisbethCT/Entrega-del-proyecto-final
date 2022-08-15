
package com.mycompany.proyecto_ventas;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

class ProductosDAO {
    
    PreparedStatement ps;
    ResultSet rs;
    Connection con;
    Conexion conectar = new Conexion();
    Object resultado [] = new Object[3];
    Object datos[][];
    
     public Object[][] listar_tabla() {

        String instruccion = "select * from productos";

        try {
            int x = 0;
            con = conectar.Conectar();
            ps = con.prepareStatement(instruccion);
            rs = ps.executeQuery();

            while (rs.next()) {
                x++;
            }
            datos = new Object[x][5];
            x = 0;
            con = conectar.Conectar();
            ps = con.prepareStatement(instruccion);
            rs = ps.executeQuery();

            while (rs.next()) {
                datos[x][0] = rs.getInt(1);
                datos[x][1] = rs.getString(2);
                datos[x][2] = rs.getString(3);
                datos[x][3] = rs.getString(4);
                datos[x][4] = rs.getInt(5);
                x++;
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return datos;
    }//fin del metodo listar
    
    public Object[] filtro(int codigo) {

        String sql = "select * from productos where codigo = " + "'" + codigo + "'" + ";";
        try {
            con = conectar.Conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            if (rs.next()) {
                resultado[0] = rs.getInt(1);
                resultado[1] = rs.getString(2);
                resultado[2] = rs.getDouble(5);

                return resultado;
            }

        } catch (Exception e) {

        }

        return null;
    }
    
    public void crear(int codigo, String nombre, String direccion, int cantidad, double precio) {

        String sql = "insert into productos(codigo, nombre, direccion, cantidad, precio) values (?,?,?,?,?)";

        try {
            con = conectar.Conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, codigo);
            ps.setString(2, nombre);
            ps.setString(3, direccion);
            ps.setInt(4, cantidad);
            ps.setDouble(5, precio);
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println(e);

        }
    }//fin del metodo crear

    public void modificar(int codigo, String nombre, String direccion, int cantidad, double precio) {
        String sql = "update productos set nombre = ?, direccion = ?, cantidad = ?, precio = ? where codigo = ?";
        try {
            con = conectar.Conectar();
            ps = con.prepareStatement(sql);

            ps.setInt(1, codigo);
            ps.setString(2, nombre);
            ps.setString(3, direccion);
            ps.setInt(4, cantidad);
            ps.setDouble(5, precio);

            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println(e);
        }
    }//FIN DEL METODO MODIFICAR

    public void eliminar(int x) {
        String sql = "delete from productos where codigo =" + x;
        try {
            con = conectar.Conectar();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }//fin del metodo ELIMINAR

    public void pdf() throws FileNotFoundException, DocumentException {
        FileOutputStream gen = new FileOutputStream("Reporte-Sucursales.pdf");
        Document documento = new Document();
        PdfWriter.getInstance(documento, gen);
        documento.open();
        Paragraph parrafo = new Paragraph("Reporte de Sucursales Base de datos");
        parrafo.setAlignment(1);
        documento.add(parrafo);
        documento.add(new Paragraph("\n"));
        String sql = "select * from productos";
        try {
            con = conectar.Conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                documento.add(new Paragraph("Código: " + rs.getInt(1)));
                documento.add(new Paragraph("Nombre: " + rs.getString(2)));
                documento.add(new Paragraph("Dirección: " + rs.getString(3)));
                documento.add(new Paragraph("Cantidad: " + rs.getInt(4)));
                documento.add(new Paragraph("Precio: " + rs.getDouble(5)));
                documento.add(new Paragraph("\n\n"));
            }
        } catch (Exception e) {
        }
        documento.close();
        JOptionPane.showMessageDialog(null, "El archivo se creo correctamente");
        try {
            File sucursales_doc = new File("Reporte-Sucursales.pdf");
            Desktop.getDesktop().open(sucursales_doc);
        } catch (Exception e) {
        }

    }
    
}
