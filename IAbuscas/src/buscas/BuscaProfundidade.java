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
import java.util.logging.Level;
import java.util.logging.Logger;
import mapa.Mapa;
import view.PercursoFrame;
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
    public void iniciarBusca() {
       System.out.println("tentando deixar visualização visivel=true");
       PercursoFrame.getInstance().setVisible(true);
       noAtual = mapa.getNoFromId(idInicio);
       
       evt = new Evento(this);
       evt.setMsg("Iniciando busca" + "... iniciando no nó " + Integer.toString(noAtual.getId()));
       evt.setEstado(TipoDeEvento.PROCURANDO);
       evt.setId(noAtual.getId());
       
       noAtual.quebrarConexoes();
        try {
            Controle.lidarComEvento(evt);
        } catch (InterruptedException ex) {
            Logger.getLogger(BuscaProfundidade.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        evt.setId(noAtual.getId());
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
        try {
            Controle.lidarComEvento(evt);
        } catch (InterruptedException ex) {
            Logger.getLogger(BuscaProfundidade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
