
package com.mycompany.proyecto_ventas;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.text.ParseException;
import java.util.logging.*;
import javax.swing.*;

public class Vendedores_vista {

   
    JPanel pVendedores =new JPanel();
    JTable tabla = new JTable();
    JScrollPane sp = new JScrollPane();
    Object[][] datos;
    String modificarV="Modificar ",crearV="Crear ";
    String c1="Código",c2="Nombre",c3="Caja",c4="Ventas",c5="Genero",c6="Password",c7="Rol";
     
    private void botones(){
        pVendedores.setLayout(null);
        pVendedores.setBackground(Color.blue);
        
        JButton btnCrear = new JButton("Crear");
        btnCrear.setBounds(500,75,130,50);
        pVendedores.add(btnCrear);
        
        ActionListener funcion_crear = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                crear();
            }
        };
        
        btnCrear.addActionListener(funcion_crear);
        
        //Los demas botones
        JButton btnCargaM = new JButton("Carga Masiva");
        btnCargaM.setBounds(670,75,130,50);
        pVendedores.add(btnCargaM);
        
        ActionListener funcion_carga = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    carga_masiva();
                } catch (IOException ex) {
                    Logger.getLogger(Vendedores_vista.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(Vendedores_vista.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        
        btnCargaM.addActionListener(funcion_carga);
        
        JButton btnActualizar = new JButton("Actualizar");
        btnActualizar.setBounds(500,150,130,50);
        pVendedores.add(btnActualizar);
        
        ActionListener funcion_actualizar = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modificar();
            }
        };
        
        btnActualizar.addActionListener(funcion_actualizar);
        
        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(670,150,130,50);
        pVendedores.add(btnEliminar);
        
        
        
        ActionListener funcion_eliminar = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               eliminarFila();
            }
        };
        
        btnEliminar.addActionListener(funcion_eliminar);
        
        
        JButton btnExportar = new JButton("Exportar PDF");
        btnExportar.setBounds(500,225,300,50);
        pVendedores.add(btnExportar);
        
    }//fin del metodo botones
    
    private void eliminarFila (){
        //default icon, custom title 
        int n = JOptionPane.showConfirmDialog(
            null, 
            "Seguro que deseas eliminar el elemento seleccionado?", 
            "Eliminar ", 
            JOptionPane.YES_NO_OPTION); 

        if(n==JOptionPane.YES_OPTION){ 
            VendedoresDAO sd = new VendedoresDAO();
            sd.eliminar(Integer.parseInt(tabla.getValueAt(tabla.getSelectedRow(),0)+""));
        } 
        else {
        } 
    }
    
    //Ventana 
     private void modificar() {
        
        JFrame frame = new JFrame();
        frame.setTitle(modificarV);
        frame.setLocationRelativeTo(null);
        frame.setBounds(50, 185, 350, 450);
        frame.setVisible(true);

        JPanel p1 = new JPanel();
        p1.setLayout(null);
        frame.add(p1);
        
        Font ft = new Font("Britannic",Font.BOLD,20);
        
        JLabel tituloP = new JLabel(modificarV,SwingConstants.CENTER);
        tituloP.setBounds(50, 10, 230, 50);
        tituloP.setFont(ft);
        p1.add(tituloP);

        JLabel l1 = new JLabel(c1);
        l1.setBounds(50, 80, 80, 30);
        p1.add(l1);

        JTextField t1 = new JTextField();
        t1.setBounds(150, 80, 130, 30);
        t1.setEnabled(false);
        t1.setText(tabla.getValueAt(tabla.getSelectedRow(), 0)+"");
        p1.add(t1);

        JLabel l2 = new JLabel(c2);
        l2.setBounds(50, 120, 80, 30);
        p1.add(l2);

        JTextField t2 = new JTextField();
        t2.setBounds(150, 120, 130, 30);
        t2.setText(tabla.getValueAt(tabla.getSelectedRow(), 1)+"");
        p1.add(t2);

        JLabel l3 = new JLabel(c3);
        l3.setBounds(50, 160, 80, 30);
        p1.add(l3);

        JTextField t3 = new JTextField();
        t3.setBounds(150, 160, 130, 30);
        t3.setText(tabla.getValueAt(tabla.getSelectedRow(), 2)+"");
        p1.add(t3);

        JLabel l4 = new JLabel(c4);
        l4.setBounds(50, 200, 80, 30);
        p1.add(l4);

        JTextField t4 = new JTextField();
        t4.setBounds(150, 200, 130, 30);
        t4.setText(tabla.getValueAt(tabla.getSelectedRow(), 3)+"");
        p1.add(t4);

        JLabel l5 = new JLabel(c5);
        l5.setBounds(50, 240, 80, 30);
        p1.add(l5);

        JTextField t5 = new JTextField();
        t5.setBounds(150, 240, 130, 30);
        t5.setText(tabla.getValueAt(tabla.getSelectedRow(), 4)+"");
        p1.add(t5);
        
        JLabel l6 = new JLabel(c6);
        l6.setBounds(50, 280, 80, 30);
        p1.add(l6);

        JTextField t6 = new JTextField();
        t6.setBounds(150, 280, 130, 30);
        t6.setText(tabla.getValueAt(tabla.getSelectedRow(), 5)+"");
        p1.add(t6);
        
        JLabel l7 = new JLabel(c7);
        l7.setBounds(50, 320, 80, 30);
        p1.add(l7);

        JTextField t7 = new JTextField();
        t7.setBounds(150, 320, 130, 30);
        t7.setText(tabla.getValueAt(tabla.getSelectedRow(), 6)+"");
        p1.add(t7);

        JButton b1 = new JButton(modificarV);
        b1.setBounds(50, 370, 230, 30);
        p1.add(b1);
        
        

        ActionListener guardar = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VendedoresDAO sd = new VendedoresDAO();
                sd.modificar(Integer.parseInt(t1.getText()),
                        t2.getText(),
                        Integer.parseInt(t3.getText()),
                        Integer.parseInt(t4.getText()),
                        t5.getText(),
                        t6.getText(),
                        Integer.parseInt(t7.getText()));
                frame.setVisible(false);
            }
        };

        b1.addActionListener(guardar);

    }//fin de mod
    
     private void crear() {
        
        JFrame frame = new JFrame();
        frame.setTitle(crearV);
        frame.setLocationRelativeTo(null);
        frame.setBounds(50, 185, 350, 450);
        frame.setVisible(true);

        JPanel p1 = new JPanel();
        p1.setLayout(null);
        frame.add(p1);
        
        Font ft = new Font("Britannic",Font.BOLD,20);
        
        JLabel tituloP = new JLabel(crearV,SwingConstants.CENTER);
        tituloP.setBounds(50, 10, 230, 50);
        tituloP.setFont(ft);
        p1.add(tituloP);

        JLabel l1 = new JLabel(c1);
        l1.setBounds(50, 80, 80, 30);
        p1.add(l1);

        JTextField t1 = new JTextField();
        t1.setBounds(150, 80, 130, 30);
        p1.add(t1);

        JLabel l2 = new JLabel(c2);
        l2.setBounds(50, 120, 80, 30);
        p1.add(l2);

        JTextField t2 = new JTextField();
        t2.setBounds(150, 120, 130, 30);
        p1.add(t2);

        JLabel l3 = new JLabel(c3);
        l3.setBounds(50, 160, 80, 30);
        p1.add(l3);

        JTextField t3 = new JTextField();
        t3.setBounds(150, 160, 130, 30);
        p1.add(t3);

        JLabel l4 = new JLabel(c4);
        l4.setBounds(50, 200, 80, 30);
        p1.add(l4);

        JTextField t4 = new JTextField();
        t4.setBounds(150, 200, 130, 30);
        p1.add(t4);

        JLabel l5 = new JLabel(c5);
        l5.setBounds(50, 240, 80, 30);
        p1.add(l5);
        
        JTextField t5 = new JTextField();
        t5.setBounds(150, 240, 130, 30);
        p1.add(t5);
        
        JLabel l6 = new JLabel(c6);
        l6.setBounds(50, 280, 80, 30);
        p1.add(l6);

        JTextField t6 = new JTextField();
        t6.setBounds(150, 280, 130, 30);
        p1.add(t6);
        
        JLabel l7 = new JLabel(c7);
        l7.setBounds(50, 320, 80, 30);
        p1.add(l7);

        JTextField t7 = new JTextField();
        t7.setBounds(150, 320, 130, 30);
        p1.add(t7);

        JButton b1 = new JButton(crearV);
        b1.setBounds(50, 370, 230, 30);
        p1.add(b1);
        
        

        ActionListener guardar = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VendedoresDAO sd = new VendedoresDAO();
                sd.crear(Integer.parseInt(t1.getText()),
                        t2.getText(),
                        Integer.parseInt(t3.getText()),
                        Integer.parseInt(t4.getText()),
                        t5.getText(),
                        t6.getText(),
                        Integer.parseInt(t7.getText()));
                frame.setVisible(false);
            }
        };

        b1.addActionListener(guardar);

    }//fin de crear
     
    //Leer archivo
    private String leerarchivo() {

        JPanel c1 = new JPanel();
        JFileChooser fc = new JFileChooser();
        int op = fc.showOpenDialog(c1);
        String content = "";
        if (op == JFileChooser.APPROVE_OPTION) {

            File pRuta = fc.getSelectedFile();
            String ruta = pRuta.getAbsolutePath();
            File archivo = null;
            FileReader fr = null;
            BufferedReader br = null;

            try {
                archivo = new File(ruta);
                fr = new FileReader(archivo);
                br = new BufferedReader(fr);
                String linea = "";

                while ((linea = br.readLine()) != null) {

                    content += linea + "\n";
                }
                return content;

            } catch (FileNotFoundException ex) {
                String resp = (String) JOptionPane.showInputDialog(null, "No se encontro el archivo");
            } catch (IOException ex) {
                String resp = (String) JOptionPane.showInputDialog(null, "No se pudo abrir el archivo");
            } finally {
                try {
                    if (null != fr) {
                        fr.close();
                    }
                } catch (Exception e2) {
                    String resp = (String) JOptionPane.showInputDialog(null, "No se encontro el archivo");
                    return "";
                }

            }
            return content;

        }
        return null;

    }
    
    //Carga Masiva
    private void carga_masiva() throws FileNotFoundException, IOException, ParseException {
	
        String archivo_retorno = leerarchivo();

        JsonParser parse = new JsonParser();
        JsonArray matriz = parse.parse(archivo_retorno).getAsJsonArray();

        for (int i = 0; i < matriz.size(); i++) {
                JsonObject objeto = matriz.get(i).getAsJsonObject();
                VendedoresDAO vd = new VendedoresDAO();

                vd.crear(objeto.get("codigo").getAsInt(), 
                         objeto.get("nombre").getAsString(), 
                         objeto.get("caja").getAsInt(), 
                         objeto.get("ventas").getAsInt(), 
                         objeto.get("genero").getAsString(),
                         objeto.get("password").getAsString(), 
                         objeto.get("rol").getAsInt()
                        );
              
        }
        
    }
    
    private void tablaMetodo(){
        String [] columnas ={c1,c2,c3,c4,c5,c6,c7};
        
        VendedoresDAO sd = new VendedoresDAO();
        datos=sd.listar_tabla();
        
        tabla = new JTable(datos,columnas);
        
        sp = new JScrollPane(tabla);
        sp.setSize(460, 400);
        sp.setLocation(10,10);
        sp.setVisible(true);
        pVendedores.add(sp);
        
        
        
    }
    
    
    public void ejecutar(){
        botones();
        tablaMetodo();
    }
    
}
