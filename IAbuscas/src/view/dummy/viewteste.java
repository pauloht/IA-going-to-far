/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.dummy;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author FREE
 */
public class viewteste {
    public static void main(String args[])
    {
        Dimension dimMax = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension dim = new Dimension(new Double(dimMax.width*0.8).intValue(),new Double(dimMax.height*0.8).intValue());
        
        JFrame framePrincipal = new JFrame();
        
        JPanel panelPrincipal = new JPanel();
        framePrincipal.add(panelPrincipal);
        
        panelPrincipal.setBackground(Color.RED);
        panelPrincipal.setPreferredSize(dim);
        panelPrincipal.setLayout(new GridLayout(50,50));
        JPanel aux;
        for (int i=0;i<2500;i++)
        {
            aux = new JPanel();
            
            switch (i%2)
            {
                case 0 :
                    if (i%100 < 50)
                    {
                        aux.setBackground(Color.black);
                    }
                    else
                    {
                        aux.setBackground(Color.blue);
                    }
                    break;
                case 1 :
                    if (i%100 < 50)
                    {
                        aux.setBackground(Color.white);
                    }
                    else
                    {
                        aux.setBackground(Color.green);
                    }
                    break;
                default :
                    throw new IllegalArgumentException();
            }
            panelPrincipal.add(aux);
        }
        
        framePrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        framePrincipal.pack();
        framePrincipal.setVisible(true);
    }
}
