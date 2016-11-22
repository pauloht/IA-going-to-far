/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import evento.Evento;
import evento.TipoDeEvento;
import java.awt.Color;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author FREE
 */
public class ViewThread implements Observer{
    private static ViewThread instancia = new ViewThread();
    private static int bufferID;
    private static int bufferRGB;
    public static ViewThread getInstancia()
    {
        return(instancia);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof Evento)
        {
            Evento evento = (Evento)arg;
            
            String msg = evento.getMsg();
            
            PercursoFrame.getInstance().changeMsg(msg);
            
            //PercursoFrame.getInstance().setVisible(!PercursoFrame.getInstance().isVisible());
            
            if (evento.getEstado()==TipoDeEvento.PROCURANDO || evento.getEstado()==TipoDeEvento.ACHOU)
            {
                int id = evento.getId();
                PercursoFrame.getInstance().changeCell(id);
            }
            if (evento.getEstado()==TipoDeEvento.LIMPAR)
            {
                PercursoFrame.getInstance().cleanBuffer();
            }
        }
    }
}
