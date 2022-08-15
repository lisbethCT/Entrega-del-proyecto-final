
package com.mycompany.proyecto_ventas;

import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;


public class Administrador extends JFrame {
    JTabbedPane pestañas =new JTabbedPane();

    
    private void inicio(){
        setTitle ("Administrador");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setBounds(50,20,850,500);
        setVisible(true);
        
        pestañas.setFont(new Font("arial",Font.BOLD,14));
        
        
        
        Sucursales_vistas sv=new Sucursales_vistas();
        sv.ejecutar();
        Productos_vista pv = new Productos_vista();
        pv.ejecutar();
        Vendedores_vista vv = new Vendedores_vista();
        vv.ejecutar();
        Clientes_vista cv = new Clientes_vista();
        cv.ejecutar();
        
        
        pestañas.addTab("Sucursaless ", sv.pSucursales);
        pestañas.addTab("Productos ", pv.pProductos);
        pestañas.addTab("Vendedores ", vv.pVendedores);
        pestañas.addTab("Clientes ", cv.pClientes);
        
        add(pestañas);
        
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
            String[] a={"Salir","Cerrar Sesion","Cancelar"} ;
            int b=JOptionPane.showOptionDialog(null,"¿Que deseas hacer?","Administrador",JOptionPane.DEFAULT_OPTION,JOptionPane.QUESTION_MESSAGE,null,a,a[0]);
                switch (b) {
                    case 0:
                        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        break;
                    case 1:
                        login lv= new login();
                        lv.ejecutar();
                        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        break;
                    case 2:
                        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                        break;
                    default:
                        break;
                }
            };
        });
                
    }
    
    public void ejecutar(){
        inicio();
    }
 
    
}
