/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import evento.Evento;
import evento.TipoDeEvento;
import java.util.Observable;
import java.util.concurrent.TimeUnit;
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
        TimeUnit.SECONDS.sleep(1);
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
    }
}
