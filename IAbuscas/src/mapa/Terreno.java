/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapa;

/**
 *
 * @author FREE
 */
public enum Terreno {
    INVALIDO,
    PLANO,
    MONTANHOSO,
    PANTANO,
    LAVA;
    
    int getCusto()
    {
        switch(this)
        {
            case INVALIDO :
                throw new IllegalArgumentException("Caso invalido");
            case PLANO :
                return(1);
            case MONTANHOSO :
                return(2);
            case PANTANO :
                return(4);
            case LAVA :
                return(8);
            default :
                throw new IllegalArgumentException("Caso invalido");
        }
    }
}
