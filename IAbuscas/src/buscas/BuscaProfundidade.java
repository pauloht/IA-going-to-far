/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buscas;
import evento.Evento;
import mapa.No;
import controle.Controle;
import evento.TipoDeEvento;
import mapa.Mapa;
/**
 *
 * @author FREE
 */
public class BuscaProfundidade extends Busca{
    Evento evt;

    public BuscaProfundidade(int inicio, int alvo,Mapa mapa) {
        super(inicio, alvo, mapa);
    }
    
    @Override
    public void iniciarBusca(No inicio) {
       evt = new Evento(this);
       evt.setMsg("Iniciando busca" + "... iniciando no nó " + Integer.toString(inicio.getId()));
       evt.setEstado(TipoDeEvento.PROCURANDO);
       noAtual = inicio;
       noAtual.quebrarConexoes();
       Controle.lidarComEvento(evt);
    }
    
    @Override
    public void continuar() {
        boolean backtrack = false;
        boolean fim = false;
        noAtual.setVisitado(true);
        if (noAtual.getId() == idAlvo)
        {
            fim = true;
        }
        else if (noAtual.getNorte()!=null)
        {
            noAtual.getNorte().setPai(noAtual);
            noAtual = noAtual.getNorte();
            noAtual.quebrarConexoes();
        }
        else if (noAtual.getLeste()!=null)
        {
            noAtual.getLeste().setPai(noAtual);
            noAtual = noAtual.getLeste();
            noAtual.quebrarConexoes();
        }
        else if (noAtual.getSul()!=null)
        {
            noAtual.getSul().setPai(noAtual);
            noAtual = noAtual.getSul();
            noAtual.quebrarConexoes();
        }
        else if (noAtual.getOeste()!=null)
        {
            noAtual.getOeste().setPai(noAtual);
            noAtual = noAtual.getOeste();
            noAtual.quebrarConexoes();
        }
        else
        {
            backtrack = true;
            noAtual = noAtual.getPai();
        }
        if (fim)
        {
            evt.setEstado(TipoDeEvento.ACHOU);
            evt.setMsg("Busca encontrou alvo! Caminho : " + noAtual.caminhoAteEsseNo() + "\nNos Visitados : " + Integer.toString( this.nosVisitados() ) + "\nCusto : " + Integer.toString( noAtual.custoDoCaminho() ));
        }
        else if (!backtrack)
        {
            evt.setMsg("Visitando nó " + Integer.toString(noAtual.getId()));
        }
        else
        {
            evt.setMsg("Realizando backtracking para nó " + Integer.toString(noAtual.getId()));
        }
        Controle.lidarComEvento(evt);
    }
    
}
