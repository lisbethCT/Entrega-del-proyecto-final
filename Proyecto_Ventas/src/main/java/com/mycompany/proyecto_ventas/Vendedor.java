
package com.mycompany.proyecto_ventas;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Vendedor extends JFrame{
    
    JTabbedPane pestañas = new JTabbedPane();

    public void inicio() {

        setTitle("Ventas");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setBounds(50, 175, 900, 800);
        setVisible(true);

        Nueva_venta nv = new Nueva_venta();
        nv.ejecutar();

        ventas_realizadas vr = new ventas_realizadas();
        vr.ejecutar();
        
        pestañas.addTab("Nueva venta", nv.general);
        pestañas.addTab("Ventas realizadas", vr.ventas_general);
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

//    public static void main(String[] args) {
//        Vendedor v = new Vendedor();
//        v.inicio();
//    }
}
