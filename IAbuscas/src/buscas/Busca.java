/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buscas;

import mapa.Mapa;
import mapa.No;

/**
 *
 * @author FREE
 */
public abstract class Busca {
    int idInicio = -1;
    int idAlvo = -1;
    
    No noAtual = null;
    
    Mapa mapa = null;

    public Busca(int inicio,int alvo,Mapa mapa)
    {
        idInicio = inicio;
        idAlvo = alvo;
        this.mapa = mapa;
    }
    
    public int getIdInicio() {
        return idInicio;
    }

    public void setIdInicio(int idInicio) {
        this.idInicio = idInicio;
    }

    public int getIdAlvo() {
        return idAlvo;
    }

    public void setIdAlvo(int idAlvo) {
        this.idAlvo = idAlvo;
    }
    
    public int nosVisitados()
    {
        if (mapa == null)
        {
            return (-1);
        }
        return (mapa.nosVisitados());
    }
    
    abstract public void iniciarBusca(No inicio);
    
    abstract public void continuar();
}
