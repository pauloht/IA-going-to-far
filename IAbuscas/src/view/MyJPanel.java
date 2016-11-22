/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author FREE
 */
public class MyJPanel extends JPanel{
    public PaintEnum paiPintura;
    public PaintEnum filhoPintura;
    
    @Override
    public void paint(Graphics g)
    {
        super.paint(g);
        
        if (paiPintura!=null&&paiPintura.ehValido())
        {
            int altura = this.getSize().height;
            int largura = this.getSize().width;
            
            int centroy = altura/2;
            int centrox = largura/2;
            
            int posy2=-1;
            int posx2=-1;
            
            switch ( paiPintura )
            {
                case LESTE :
                    posy2 = centroy;
                    posx2 = largura;
                    break;
                case SUL :
                    posy2 = altura;
                    posx2 = centrox;
                    break;
                case NORTE :
                    posy2 = 0;
                    posx2 = centrox;
                    break;
                case OESTE :
                    posy2 = centroy;
                    posx2 = 0;
                    break;
                default :
                    throw new IllegalArgumentException("Default nao aceito!");
            }
            
            if (posy2!=-1&&posx2!=-1)
            {
                g.drawLine(centrox, centroy, posx2, posy2);
            }
            
        }
        
        if (filhoPintura!=null&&filhoPintura.ehValido())
        {
            int altura = this.getSize().height;
            int largura = this.getSize().width;
            
            int centroy = altura/2;
            int centrox = largura/2;
            
            int posy2=-1;
            int posx2=-1;
            
            switch ( filhoPintura )
            {
                case LESTE :
                    posy2 = centroy;
                    posx2 = largura;
                    break;
                case SUL :
                    posy2 = altura;
                    posx2 = centrox;
                    break;
                case NORTE :
                    posy2 = 0;
                    posx2 = centrox;
                    break;
                case OESTE :
                    posy2 = centroy;
                    posx2 = 0;
                    break;
                default :
                    throw new IllegalArgumentException("Default nao aceito!");
            }
            
            if (posy2!=-1&&posx2!=-1)
            {
                g.drawLine(centrox, centroy, posx2, posy2);
            }
            
        }
    }
}
