/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evento;

import buscas.Busca;

/**
 * 
 * @author FREE
 */
public class Evento {
    TipoDeEvento estado;
    
    /**
     * Busca que cria evento
     */
    Busca chamador;
    
    /**
     * Id da celula
     */
    int id;
    
    String msg;
    
    public Evento(Busca chamador)
    {
        this.chamador = chamador;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Busca getChamador() {
        return chamador;
    }

    public void setChamador(Busca chamador) {
        this.chamador = chamador;
    }

    public TipoDeEvento getEstado() {
        return estado;
    }

    public void setEstado(TipoDeEvento estado) {
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
}
