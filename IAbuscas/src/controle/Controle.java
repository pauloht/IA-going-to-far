/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import evento.Evento;
import evento.TipoDeEvento;

/**
 *
 * @author FREE
 */
public class Controle {
    public static void lidarComEvento(Evento evt)
    {
        if (evt.getChamador()!=null)
        {
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
