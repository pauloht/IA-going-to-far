/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapa;

import java.awt.Color;

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
    
    public int getCusto()
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
    
    public Color getColor()
    {
        switch(this)
        {
            case INVALIDO :
                throw new IllegalArgumentException("Caso invalido");
            case PLANO :
                return(Color.GREEN);
            case MONTANHOSO :
                return(new Color(156, 93, 82));
            case PANTANO :
                return(Color.BLUE);
            case LAVA :
                return(Color.RED);
            default :
                throw new IllegalArgumentException("Caso invalido");
        }
    }
    
    public int getID()
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
                return(3);
            case LAVA :
                return(4);
            default :
                throw new IllegalArgumentException("Caso invalido");
        }
    }
    
    public static Terreno getTerrenoFromColor(Color color)
    {
        if (color.equals(Terreno.PLANO.getColor()))
        {
            return(Terreno.PLANO);
        }
        else if (color.equals( Terreno.MONTANHOSO.getColor() ))
        {
            return(Terreno.MONTANHOSO);
        }
        else if (color.equals(Terreno.PANTANO.getColor()))
        {
            return(Terreno.PANTANO);
        }
        else if (color.equals(Terreno.LAVA.getColor()))
        {
            return(Terreno.LAVA);
        }
        else
        {
            throw new IllegalArgumentException("Cor invalida!");
        }
    }
    
    public static Terreno getTerrenoFromID(int id)
    {
        if (id == Terreno.PLANO.getID())
        {
            return(Terreno.PLANO);
        }
        else if (id == Terreno.MONTANHOSO.getID())
        {
            return(Terreno.MONTANHOSO);
        }
        else if (id == Terreno.PANTANO.getID())
        {
            return(Terreno.PANTANO);
        }
        else if (id == Terreno.LAVA.getID())
        {
            return(Terreno.LAVA);
        }
        else
        {
            throw new IllegalArgumentException("ID invalido!");
        }
    }
}
