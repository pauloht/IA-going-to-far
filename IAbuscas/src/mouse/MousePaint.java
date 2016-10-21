/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mouse;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import mapa.Terreno;

/**
 *
 * @author FREE
 */
public class MousePaint implements MouseListener{
    private static boolean pressed = false;
    private static MousePaint instance = new MousePaint();
    private static Terreno paint = Terreno.PLANO;
    
    private MousePaint()
    {
        
    }
    
    public static void changePaint(Terreno newPaint)
    {
        paint = newPaint;
    }
    
    private static void eventoHandler(MouseEvent e)
    {
        if (e.getComponent().getName().equals("canvas"))
        {
            e.getComponent().setBackground(paint.getColor());
        }
    }
    
    public static MousePaint getInstance()
    {
        return(instance);
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        //System.out.println("mouse clicked");
        eventoHandler(e);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //System.out.println("mouse pressed");
        pressed = true;
        eventoHandler(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //System.out.println("mouse realeased");
        pressed = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //System.out.println("mouse entered");
        if (pressed)
        {
            eventoHandler(e);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //System.out.println("mouse exited");
    }
    
}
