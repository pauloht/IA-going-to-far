/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpaneltests;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Paulo.Tenorio
 */
public class JPanelTests {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        JFrame frame = new JFrame("frame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(false);
        //frame.setLayout(null);
        
        Test test = new Test();
        test.setPreferredSize(new Dimension(300,300));
        test.setBackground(Color.red);
        test.setForeground(Color.yellow);
        frame.add(test);
        
        frame.pack();
        frame.setVisible(true);
        System.out.println(test.getSize());
        pauloDraw draw = new pauloDraw(test);
        draw.drawLine(test, 150.0, 0.0, 150.0, 299.0);
        
        test.repaint();
    }
    
}
