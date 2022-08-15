package com.mycompany.proyecto_ventas;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

class ventas_realizadas {

    JPanel ventas_general = new JPanel();
    JPanel filtro = new JPanel();
    JPanel panelResultados = new JPanel();
    
    JTable tabla= new JTable();
    JScrollPane sp= new JScrollPane();
    
    

    private void filtro() {
        ventas_general.setLayout(null);
        
        filtro.setBackground(Color.WHITE);
        filtro.setBounds(10, 10, 800, 250);
        filtro.setLayout(null);
        ventas_general.add(filtro);
        
        
        
        //cajas filtro
        JTextField factura = new JTextField();
        JTextField nit = new JTextField();
        JTextField nombre = new JTextField();
        JTextField fecha = new JTextField();

        JLabel l1 = new JLabel("No. Factura ");
        l1.setBounds(50, 50, 150, 30);
        
        JLabel l3 = new JLabel("Nombre");
        l3.setBounds(50, 100, 150, 30);
        
        JLabel l2 = new JLabel("NIT");
        l2.setBounds(400, 50, 150, 30);

        JLabel l4 = new JLabel("Fecha");
        l4.setBounds(400, 100, 150, 30);

        factura.setBounds(200, 50, 150, 30);
        nit.setBounds(550, 50, 160, 30);
        nombre.setBounds(200, 100, 150, 30);
        
        fecha.setBounds(550, 100, 160, 30);

        JButton b1 = new JButton("Buscar");
        b1.setBounds(200, 150, 500, 50);
        
        filtro.add(b1);
        filtro.add(l1);
        filtro.add(l2);
        filtro.add(l3);
        filtro.add(l4);
        
        filtro.add(factura);
        filtro.add(nit);
        filtro.add(nombre);
        filtro.add(fecha);
        
        ActionListener verificar;
        verificar = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                int factura_uso;
                if (factura.getText().trim().equals("")) {
                    factura_uso = 0;
                } else {
                    factura_uso = Integer.parseInt(factura.getText().trim());
                }
                
                int nit_uso;
                if (nit.getText().trim().equals("")) {
                    nit_uso =0;
                } else {
                    nit_uso = Integer.parseInt(nit.getText().trim());
                }
                
                String nombres_uso;
                if (nombre.getText().trim().equals("")) {
                    nombres_uso = null;
                } else {
                    nombres_uso = nombre.getText().trim();
                }
                
                String fecha_uso;
                if (fecha.getText().trim().equals("")) {
                    fecha_uso = null;
                } else {
                    fecha_uso = fecha.getText().trim();
                }
                
                VentasDAO vd = new VentasDAO();
                Object[][] resultado = vd.filtro_ventas(factura_uso, nit_uso, nombres_uso, fecha_uso);
                
                if (resultado==null) {//si no encuentra resultados
                    sp.setVisible(false);//apago la tabla para 
                    tabla();//mostrar toda la tabla
                } else {//si encuentra resultados filtrados
                    sp.setVisible(false);
                    resultados(resultado);
                }
            }
        };

        b1.addActionListener(verificar);
            
        
    }

    public void resultados(){
        ventas_general.setLayout(null);
        panelResultados.setBackground(Color.YELLOW);
        panelResultados.setBounds(10, 270, 800, 320);
        panelResultados.setLayout(null);
        ventas_general.add(panelResultados);   
    }
    
    private void tabla() {
        String[] columnas = {"No_Factura", "NIT", "Nombre", "Fecha", "Total"};
        VentasDAO vd = new VentasDAO();
        Object[][] filas = vd.listar_tabla();
        tabla = new JTable(filas, columnas);
        sp = new JScrollPane(tabla);
        sp.setBounds(20, 10,750, 300);
        panelResultados.add(sp);
    }
    
    private void resultados(Object [][] result) {
        String[] columnas = {"No_Factura", "NIT", "Nombre", "Fecha", "Total"};
        tabla = new JTable(result, columnas);
        sp = new JScrollPane(tabla);
        sp.setBounds(20, 10,750, 300);
        panelResultados.add(sp);
        
    }

  
    public void ejecutar() {
        filtro();
        resultados();
        tabla();
    }
}
