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
    StringBuilder nosVisitados = null;
    boolean backtrack = false;

    public BuscaProfundidade(int inicio, int alvo,Mapa mapa) {
        super(inicio, alvo, mapa);
    }
    
    @Override
    public void iniciarBusca() {
       //System.out.println("tentando deixar visualização visivel=true");
       PercursoFrame.getInstance().setVisible(true);
       noAtual = mapa.getNoFromId(idInicio);
       
       nosVisitados = new StringBuilder();
       
       evt = new Evento(this);
       evt.setMsg("Iniciando busca em profundidade" + "... iniciando no nó " + Integer.toString(noAtual.getId()));
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
        boolean tempbacktrack;
        tempbacktrack = backtrack;
        if (backtrack)
        {
            evt.setMsg("Realizando backtracking para nó " + Integer.toString(noAtual.getId()));
        }
        backtrack = false;
        boolean fim = false;
        No proximoNo = null;
        if (!noAtual.isVisitado())
        {
            if (nosVisitados.length() > 0)
            {
                nosVisitados.append(',');
            }
            nosVisitados.append(Integer.toString( noAtual.getId() ));
        }
        noAtual.setVisitado(true);
        if (noAtual.getId() == idAlvo)
        {
            fim = true;
        }
        else if (noAtual.getNorte()!=null)
        {
            noAtual.getNorte().setPai(noAtual);
            proximoNo = noAtual.getNorte();
            proximoNo.quebrarConexoes();
        }
        else if (noAtual.getLeste()!=null)
        {
            noAtual.getLeste().setPai(noAtual);
            proximoNo = noAtual.getLeste();
            proximoNo.quebrarConexoes();
        }
        else if (noAtual.getSul()!=null)
        {
            noAtual.getSul().setPai(noAtual);
            proximoNo = noAtual.getSul();
            proximoNo.quebrarConexoes();
        }
        else if (noAtual.getOeste()!=null)
        {
            noAtual.getOeste().setPai(noAtual);
            proximoNo = noAtual.getOeste();
            proximoNo.quebrarConexoes();
        }
        else
        {
            backtrack = true;
            proximoNo = noAtual.getPai();
        }
        evt.setId(noAtual.getId());
        if (!tempbacktrack)
        {
            if (noAtual.getId() == idAlvo)
            {
                fim = true;
            }
            if (fim)
            {
                evt.setEstado(TipoDeEvento.ACHOU);
                evt.setMsg("Visitando nó " + Integer.toString(noAtual.getId()) + " ... Busca encontrou alvo! Caminho : " + noAtual.caminhoAteEsseNo() + "\nNos Visitados : " + Integer.toString( this.nosVisitados() ) + "\nCusto : " + Integer.toString( noAtual.custoDoCaminho() ));
            }
            else
            {
                evt.setMsg("Visitando nó " + Integer.toString(noAtual.getId()));
            }
        }
        evt.setMsg(evt.getMsg() + "\nNos visitados : " + nosVisitados.toString());
        
        noAtual = proximoNo;
        
        try {
            Controle.lidarComEvento(evt);
        } catch (InterruptedException ex) {
            Logger.getLogger(BuscaProfundidade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
