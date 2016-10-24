/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import buscas.Busca;
import evento.Evento;
import evento.TipoDeEvento;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import javax.swing.Timer;
import view.PercursoFrame;
import view.ViewThread;

/**
 *
 * @author FREE
 */
public class Controle extends Observable{
    private static Controle instancia;
    private static Busca buscabuffer = null;
    
    public Controle(Busca busca)
    {
        buscabuffer = busca;
        instancia = this;
        addObserver(ViewThread.getInstancia());
    }
    
    public void proximoPasso()
    {
        if (buscabuffer!=null)
        {
            buscabuffer.continuar();
        }
    }
    
    public static void lidarComEvento(Evento evt) throws InterruptedException
    {
        int delay = 0;
        if (PercursoFrame.autostatus)
        {
            delay = PercursoFrame.microsegundosdelay;
        }
        final Timer timer = new Timer(delay, null);
        timer.addActionListener(new ActionListener() {
                        int contador = 0;
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (evt.getChamador()!=null)
                            {
                                instancia.setChanged();
                                instancia.notifyObservers(evt);
                                //System.out.println(evt.getMsg());
                                if (evt.getEstado()==TipoDeEvento.PROCURANDO)
                                {
                                    if (PercursoFrame.autostatus)
                                    {
                                        evt.getChamador().continuar();
                                    }
                                }
                                else
                                {
                                    PercursoFrame.buscaTerminou();
                                    //System.out.println("fim busca!");
                                }
                            }
                            timer.stop();
                        }
                    });
        timer.start();
    }

    public static Controle getInstancia() {
        return instancia;
    }
    
    
}
