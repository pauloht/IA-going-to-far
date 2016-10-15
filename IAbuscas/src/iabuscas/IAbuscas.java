/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iabuscas;

import mapa.CriadorMapa;
import mapa.Mapa;

/**
 *
 * @author FREE
 */
public class IAbuscas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Mapa teste = CriadorMapa.criarMapa();
        buscas.CriadorBusca.criarBusca(teste);
    }
    
}
