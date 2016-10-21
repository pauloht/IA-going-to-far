/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.dummy;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author FREE
 */
public class teste {
    public static void main(String args[])
    {
        JFrame frame = new JFrame();
        frame.setPreferredSize( Toolkit.getDefaultToolkit().getScreenSize());
        
        frame.setLayout(new GridBagLayout());
        
        JPanel panelV = new JPanel();
        panelV.setBackground(Color.red);
        
        GridBagConstraints g = new GridBagConstraints();
        
        g.weightx = 1.0f;
        g.weighty = 1.0f;
        
        g.fill = GridBagConstraints.BOTH;
        g.gridx = 0;
        g.gridy = 0;
        g.gridheight = 100;
        g.gridwidth = 100;
        
        frame.add(panelV,g);
        
        JPanel panelA = new JPanel();
        panelA.setBackground(Color.blue);
        
        g.gridx = 0;
        g.gridy = 100;
        frame.add(panelA,g);
        
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        
    }
}
