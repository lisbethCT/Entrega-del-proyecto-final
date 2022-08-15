
package com.mycompany.proyecto_ventas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ClientesDAO {
    PreparedStatement ps;
    ResultSet rs;
    Connection con;
    Conexion conectar = new Conexion();
    String [] nombres;
    Object datos[][];

    public Object[][] listar_tabla() {

        String instruccion = "select * from clientes";

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
                datos[x][2] = rs.getInt(3);
                datos[x][3] = rs.getString(4);
                datos[x][4] = rs.getString(5);
                x++;
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return datos;
    }//fin del metodo listar
    
    public String[] filtro(String nombre, int NIT, String correo, String genero) {
        String sql = "select * from clientes where " 
                + "nombre = '" + nombre + "'" 
                + " or NIT = '" + NIT + "'" 
                + " or correo = '" + correo + "'" 
                + " or genero = '" + genero + "';";
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
            
            nombres = new String [x];//crear un array con el numero x de columnas
            
             x = 0;
             
            while (rs.next()) {
                nombres[x]= rs.getString(2);
                x++;
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return nombres;
    }//FIN DEL METODO FILTRO
    
    public int filtro_nit(String nombre) {

        String sql = "select * from clientes where nombre = '" + nombre + "'" +" ;";
        try {
            con = conectar.Conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(3);
            }

        } catch (Exception e) {

        }

        return 0;
    }
    
    public void modificar(int codigo, String nombre, int NIT, String correo, String genero){
        String sql = "update clientes set "
                + "nombre = ?, "
                + "NIT = ?, "
                + "correo = ?, "
                + "genero = ? where codigo = ?";
        try{
            con = conectar.Conectar();
            ps= con.prepareStatement(sql);
            
            ps.setString(1, nombre);
            ps.setInt(2, NIT);
            ps.setString(3, correo);
            ps.setString(4, genero);
            ps.setInt(5, codigo);
            
            ps.executeUpdate();
            
        }catch(Exception e){
            System.out.println(e);
        }
    }//FIN DEL METODO MODIFICAR

    public void crear(int codigo, String nombre, int nit, String correo, String genero){
        String sql = "insert into clientes(codigo,nombre,NIT,correo,Genero) values (?,?,?,?,?)";
        try{
            con = conectar.Conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, codigo);
            ps.setString(2, nombre);
            ps.setInt(3, nit);
            ps.setString(4, correo);
            ps.setString(5,genero);
            ps.executeUpdate();
        }catch(Exception e){
            System.out.println(e);
        }
    }
      public void eliminar(int x) {
        String sql = "delete from sucursales where codigo =" + x;
        try {
            con = conectar.Conectar();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }//fin del metodo ELIMINAR
    
}
