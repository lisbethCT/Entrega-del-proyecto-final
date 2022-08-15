
package com.mycompany.proyecto_ventas;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class VentasDAO {


    PreparedStatement ps;
    ResultSet rs;
    Connection con;
    Conexion conectar = new Conexion();
    Object datos[][];

    public void insertar(int no_factura, int nit, String nombre, String fecha, double total) {

        String sql = "insert into compras(No_Factura,NIT,Nombre,Fecha,Total) values (?,?,?,?,?)";
        try {
            con = conectar.Conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, no_factura);
            ps.setInt(2, nit);
            ps.setString(3, nombre);
            ps.setString(4, fecha);
            ps.setDouble(5, total);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
    
    public int filtro() {
        String sql = "select * from compras;";
        int x = 1;
        try {

            con = conectar.Conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                x++;
            }

        } catch (Exception e) {

        }

        return x;

    }

    public Object[][] listar_tabla() {
        String instruccion = "select * from compras";
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
                datos[x][1] = rs.getInt(2);
                datos[x][2] = rs.getString(3);
                datos[x][3] = rs.getString(4);
                datos[x][4] = rs.getInt(5);
                x++;
            }

        } catch (Exception e) {
        }

        return datos;

    }
    
    public Object[][] filtro_ventas(int factura, int nit , String nombre, String fecha) {
        String sql = "select * from compras where "
                + "No_Factura = '" + factura + "' or "
                + "NIT = '" + nit + "' or "
                + "Nombre = '" + nombre + "' or "
                + "Fecha = '" + fecha + "' ;";
        try {
            con = conectar.Conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            int x =0;
            while (rs.next()) {
                x++;
            }
            //Inicializando variables de conexion nuevamente
            con = conectar.Conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            datos = new Object[x][5];//crear un array con el numero x de columnas
            
             x = 0;
             
            while (rs.next()) {
                datos[x][0]= rs.getInt(1);
                datos[x][1]= rs.getInt(2);
                datos[x][2]= rs.getString(3);
                datos[x][3]= rs.getString(4);
                datos[x][4]= rs.getInt(5);//aunque en el filtro no pida el total es indiferente si lo coloco aqui
                x++;
            }
            
            if (datos[0][0]==null) {//si esta nulo que returne el null
                return null;
            } else {
                return datos;
            }
            
            
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
        
    }//FIN DEL METODO FILTRO

//    public static void main(String[] args) {
//        VentasDAO vd = new VentasDAO();
//        Object [][] res=vd.filtro_ventas(2, 0, null, null);
//        
//        for (int i = 0; i < res.length; i++) {
//            for (int j = 0; j < res[i].length; j++) {
//                System.out.println(res[i][j]);
//            }
//        }
//                
//    }
    
    
}
