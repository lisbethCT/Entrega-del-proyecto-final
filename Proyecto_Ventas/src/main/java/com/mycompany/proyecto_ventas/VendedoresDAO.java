
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

public class VendedoresDAO {
    PreparedStatement ps;
    ResultSet rs;
    Connection con;
    Conexion conectar = new Conexion();
    String nombres[];
    
    public int filtro(String nombre, String contra ){
    //en la tabla vendedores
    String sql = "select * from vendedores where nombre = "+"'"+nombre+"'"+" and password = "+"'"+contra+"'"+";";
        try {
            con = conectar.Conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            if (rs.next()) {                
                return rs.getInt(7);//Busca en la columna 7 si es rol 1 o 0 admin o no               
            }
            
        } catch (Exception e) {
           
        }
    
        return -1;
    }
    
  
    Object datos[][];

    public Object[][] listar_tabla() {

        String instruccion = "select * from vendedores";

        try {
            int x = 0;
            con = conectar.Conectar();
            ps = con.prepareStatement(instruccion);
            rs = ps.executeQuery();

            while (rs.next()) {
                x++;
            }
            datos = new Object[x][7];
            x = 0;
            con = conectar.Conectar();
            ps = con.prepareStatement(instruccion);
            rs = ps.executeQuery();

            while (rs.next()) {
                datos[x][0] = rs.getInt(1);
                datos[x][1] = rs.getString(2);
                datos[x][2] = rs.getInt(3);
                datos[x][3] = rs.getInt(4);
                datos[x][4] = rs.getString(5);
                datos[x][5] = rs.getString(6);
                datos[x][6] = rs.getInt(7);
                x++;
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return datos;
    }//fin del metodo listar


    public void crear(int codigo,  String nombre,  int caja,  int ventas, String genero,String pasword, int rol){

        String sql = "insert into vendedores(codigo, nombre, caja, ventas,genero, pasword,rol) values (?,?,?,?,?,?,?)";

        try {
            con = conectar.Conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, codigo);
            ps.setString(2, nombre);
            ps.setInt(3, caja);
            ps.setInt(4, ventas);
            ps.setString(5, genero);
            ps.setString(6, pasword);
            ps.setInt(7, rol);
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println(e);

        }
    }//fin del metodo crear

    public void modificar(int codigo, String nombre,int caja, int ventas, String genero,String pasword,int rol) {
        String sql = "update vendedores set nombre = ?, caja = ?, ventas = ?, genero = ?, pasword = ?, rol = ? where codigo = ?";
        try {
            con = conectar.Conectar();
            ps = con.prepareStatement(sql);

            ps.setInt(1, codigo);
            ps.setString(2, nombre);
            ps.setInt(3, caja);
            ps.setInt(4, ventas);
            ps.setString(5, genero);
            ps.setString(6, pasword);
            ps.setInt(7, rol);

            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println(e);
        }
    }//FIN DEL METODO MODIFICAR

    public void eliminar(int x) {
        String sql = "delete from vendedores where codigo =" + x;
        try {
            con = conectar.Conectar();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }//fin del metodo ELIMINAR

    public void pdf() throws FileNotFoundException, DocumentException {
        FileOutputStream gen = new FileOutputStream("Reporte-vendedores.pdf");
        Document documento = new Document();
        PdfWriter.getInstance(documento, gen);
        documento.open();
        Paragraph parrafo = new Paragraph("Reporte de vendedores Base de datos");
        parrafo.setAlignment(1);
        documento.add(parrafo);
        documento.add(new Paragraph("\n"));
        String sql = "select * from vendedores";
        try {
            con = conectar.Conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                documento.add(new Paragraph("Código: " + rs.getInt(1)));
                documento.add(new Paragraph("Nombre: " + rs.getString(2)));
                documento.add(new Paragraph("Caja: " + rs.getInt(3)));
                documento.add(new Paragraph("Ventas: " + rs.getInt(4)));
                documento.add(new Paragraph("Genero: " + rs.getString(5)));
                documento.add(new Paragraph("Pasword: " + rs.getString(6)));
                documento.add(new Paragraph("rol: " + rs.getInt(7)));
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
