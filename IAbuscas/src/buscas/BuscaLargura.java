/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buscas;

import controle.Controle;
import evento.Evento;
import evento.TipoDeEvento;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mapa.Mapa;
import mapa.No;
import view.PercursoFrame;

/**
 *
 * @author FREE
 */
public class BuscaLargura extends Busca{
    Evento evt = null;
    List< No > lista = null;
    StringBuilder nosVisitados = null;

    public BuscaLargura(int inicio, int alvo, Mapa mapa) {
        super(inicio, alvo, mapa);
    }
    
    @Override
    public void iniciarBusca() {
       //System.out.println("tentando deixar visualização visivel=true");
       PercursoFrame.getInstance().setVisible(true);
       lista = new LinkedList<>();
       nosVisitados = new StringBuilder();
       
       noAtual = mapa.getNoFromId(idInicio);
       
       evt = new Evento(this);
       evt.setMsg("Iniciando busca em largura" + "... iniciando no nó " + Integer.toString(noAtual.getId()));
       evt.setEstado(TipoDeEvento.PROCURANDO);
       evt.setId(noAtual.getId());
       
       noAtual.quebrarConexoes();
       lista.add(noAtual);
       if (noAtual == null || lista.get(0) == null)
       {
           //System.out.println("em iniciar busca null!");
       }
        try {
            Controle.lidarComEvento(evt);
        } catch (InterruptedException ex) {
            Logger.getLogger(BuscaProfundidade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void continuar() {
        if (!lista.isEmpty())
        {
            noAtual = lista.get(0);
            lista.remove(0);
            if (noAtual.isVisitado())
            {
                continuar();
                return;
            }
            if (nosVisitados.length() > 0)
            {
                nosVisitados.append(',');
            }
            nosVisitados.append(Integer.toString( noAtual.getId() ));
            noAtual.setVisitado(true);
        }
        else
        {
            evt.setEstado(TipoDeEvento.ERRO);
            evt.setMsg("Erro... existe alvo? ele é valido?");
            try {
            Controle.lidarComEvento(evt);
            } catch (InterruptedException ex) {
                Logger.getLogger(BuscaProfundidade.class.getName()).log(Level.SEVERE, null, ex);
            }
            return;
        }
        evt.setId(noAtual.getId());
        evt.setEstado(TipoDeEvento.PROCURANDO);
        if (noAtual.getId() == idAlvo)
        {
            evt.setEstado(TipoDeEvento.ACHOU);
            evt.setMsg("Visitando nó " + Integer.toString(noAtual.getId()) + " ... Busca encontrou alvo! Caminho : " + noAtual.caminhoAteEsseNo() + "\nNos Visitados : " + Integer.toString( this.nosVisitados() ) + "\nCusto : " + Integer.toString( noAtual.custoDoCaminho() ));
            
            PercursoFrame.getInstance().setCaminhoFinal( Busca.gerarLista(noAtual) );
            
        }
        else
        {
            evt.setMsg("Visitando nó " + Integer.toString(noAtual.getId()));
            if (noAtual.getNorte()!= null && noAtual.getNorte().getPai()==null)
            {
                lista.add(noAtual.getNorte());
                noAtual.getNorte().setPai(noAtual);
                noAtual.getNorte().quebrarConexoes();
            }
            if (noAtual.getLeste() != null && noAtual.getLeste().getPai()==null)
            {
                lista.add(noAtual.getLeste());
                noAtual.getLeste().setPai(noAtual);
                noAtual.getLeste().quebrarConexoes();
            }
            if (noAtual.getSul() != null && noAtual.getSul().getPai()==null)
            {
                lista.add(noAtual.getSul());
                noAtual.getSul().setPai(noAtual);
                noAtual.getSul().quebrarConexoes();
            }
            if (noAtual.getOeste() != null && noAtual.getOeste().getPai()==null)
            {
                lista.add(noAtual.getOeste());
                noAtual.getOeste().setPai(noAtual);
                noAtual.getOeste().quebrarConexoes();
            }
        }
        evt.setMsg(evt.getMsg() + "\nNos a visitar : " + lista.toString() +"\nNos visitados : " + nosVisitados.toString());
        try {
            Controle.lidarComEvento(evt);
        } catch (InterruptedException ex) {
            Logger.getLogger(BuscaProfundidade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
