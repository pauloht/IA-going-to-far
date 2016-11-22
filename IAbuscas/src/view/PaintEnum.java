/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

/**
 *
 * @author FREE
 */
public enum PaintEnum {
    LESTE,
    NORTE,
    SUL,
    OESTE;
    
    public boolean ehValido()
    {
        switch (this)
        {
            case LESTE : return true;
            case NORTE : return true;
            case SUL : return true;
            case OESTE : return true;
            default : return false;
        }
    }
}
