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
    //ID, custo, Color
    INVALIDO  (-1, Integer.MAX_VALUE, Color.MAGENTA),
    PLANO     ( 1,                 1, Color.GREEN),
    MONTANHOSO( 2,                 2, new Color(156, 93, 82)),
    PANTANO   ( 3,                 4, Color.BLUE),
    LAVA      ( 4,                 8, Color.RED);
    
    private final int ID;
    private final Color cor;
    private final int custo;
    private Terreno(int ID, int custo, Color cor) {
        this.ID = ID;
        this.cor = cor;
        this.custo = custo;
    }
    
    public int getCusto(){
        if (this.equals(INVALIDO))
            throw new IllegalArgumentException("Caso invalido");
        else
            return custo;
    }
    
    public Color getColor(){
        if (this.equals(INVALIDO))
            throw new IllegalArgumentException("Caso invalido");
        else
            return cor;
    }
    
    public int getID(){
        if (this.equals(INVALIDO))
            throw new IllegalArgumentException("Caso invalido");
        else
            return ID;
    }
    
    public static Terreno getTerrenoFromColor(Color color){
        for (Terreno terreno : Terreno.values()){
            if (terreno.cor.equals(color)){
                return terreno;
            }
        }
        
        throw new IllegalArgumentException("Cor invalida!");
    }
    
    public static Terreno getTerrenoFromID(int id) {  
        for (Terreno terreno : Terreno.values()){
            if (terreno.ID == id){
                return terreno;
            }
        }
        throw new IllegalArgumentException("ID invalido!");
    }
}
 /// Como estava antes, para caso eu explodir algo...
/*
    INVALIDO  ,
    PLANO     ,
    MONTANHOSO,
    PANTANO   ,
    LAVA      ;


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
*/
