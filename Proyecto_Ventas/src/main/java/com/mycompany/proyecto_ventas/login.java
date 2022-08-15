
package com.mycompany.proyecto_ventas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class login extends JFrame{
    JPanel p1=new JPanel();
    JTextField tFNombre =new JTextField();
    //JTextField t2 =new JTextField();
    JPasswordField password = new JPasswordField();
    
    public void inicio(){
        setTitle ("Login");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setBounds(50,175,400,400);
        setVisible(true);
        p1.setLayout(null);
        add(p1);
        
        JLabel l1 = new JLabel("Nombre: ");
        l1.setBounds(50, 75, 80, 30);
        p1.add(l1);
        
        tFNombre.setBounds(150, 79, 200, 30);
        p1.add(tFNombre);
        
        JLabel l2 = new JLabel("Contraseña: ");
        l2.setBounds(50, 175, 80, 30);
        p1.add(l2);
        
        password.setBounds(150, 179, 200, 30);
        p1.add(password);
        //t2.setBounds(x, y, WIDTH, HEIGHT);
        
        JButton bIngresar = new JButton("Ingresar");
        bIngresar.setBounds(150, 250, 100, 50);
        p1.add(bIngresar);
        
        ActionListener verificar = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                VendedoresDAO vd = new VendedoresDAO();
                int respuesta = vd.filtro(tFNombre.getText(), password.getText());

                switch (respuesta) {
                    case 1:
                        Administrador ad = new Administrador();
                        ad.ejecutar();
                        dispose();
                        break;
                    case 0:
                        JOptionPane.showMessageDialog(null, "es vendedor");
                        Vendedor vn = new Vendedor();
                        vn.inicio();
                        dispose();
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Datos incorrectos");
                        break;
                }

            }
        };

        bIngresar.addActionListener(verificar);
        
        
        
    }
    
    public void ejecutar(){
        inicio();
    }
    
}
