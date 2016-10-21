/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import evento.Evento;
import evento.TipoDeEvento;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import javax.swing.Timer;
import view.ViewThread;

/**
 *
 * @author FREE
 */
public class Controle extends Observable{
    private static Controle instancia = new Controle();
    
    public Controle()
    {
        addObserver(ViewThread.getInstancia());
    }
    
    public static void lidarComEvento(Evento evt) throws InterruptedException
    {
        final Timer timer = new Timer(1000, null);
        timer.addActionListener(new ActionListener() {
                        int contador = 0;
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (evt.getChamador()!=null)
                            {
                                instancia.setChanged();
                                instancia.notifyObservers(evt);
                                System.out.println(evt.getMsg());
                                if (evt.getEstado()==TipoDeEvento.PROCURANDO)
                                {
                                    evt.getChamador().continuar();
                                }
                                else
                                {
                                    System.out.println("fim busca!");
                                }
                            }
                            timer.stop();
                        }
                    });
        timer.start();
    }
}
