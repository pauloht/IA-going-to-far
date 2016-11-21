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
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mapa.Mapa;
import mapa.No;
import view.PercursoFrame;

/**
 *
 * @author User
 */
public class AStar extends Busca{
    Evento evt = null;
    StringBuilder nosVisitados = null;
    
    HashSet<No> open   = new HashSet();
    HashSet<No> closed = new HashSet(); //Visitados
    
    private No fim;
    
    private boolean HALT=false;
    
    /**
     * Dado o id de um nodo, calcula-se
     * a posição no grid (Linha, coluna)
     * para o determinado nodo e o retorna
     * em um vetor de 2 posições.
     * <p>
     * Posição zero possui valor da linha,
     * e a posição um, da coluna.
     * @param idAlvo do nodo do qual calcula-se coordenadas.
     * @return       Coordenadas de um nodo.
     */
    private int[] getCoordinatesFromNode(int idAlvo){
        //System.out.println("ID = " + idAlvo);
        int linhas = mapa.getLinhas();
        int coluna = mapa.getColunas();
        int linInc=-1;
        int colInc=-1;
        int countId=0;
        
        //System.out.println("PARMA: " + idAlvo);
        OUT:
        for (int i=0; i<linhas; i++){
            for (int j=0; j<coluna; j++){
                if (countId == idAlvo){
                    //System.out.println("INSIDE");
                    linInc = i;
                    colInc = j;
                    break OUT;
                }
                countId++;
                //System.out.println("COUTN: " + countId);
            }
        }

        
        int[] coords = {linInc, colInc};
        //System.out.println("ARR: " + Arrays.toString(coords));
        //System.out.println("SAIDA : " + Arrays.toString(coords));
        return coords;
    }
    
    /**
     * Calcula a distância Manhattan entre
     * dois pontos em uma matriz.
     * <p>
     * Deve ser passado um vetor de duas
     * posições: A primeira de linha (X)
     * e a segunda de coluna (Y); para
     * o ponto de origem, depois o
     * objetivo.
     * @param from   Ponto originário.
     * @param target Ponto destino.
     * @return       Distância de Manhattan.
     */
    private int manhattanDistance(int[] from, int[] target){
        return Math.abs(from[0] - target[0]) +
               Math.abs(from[1] - target[1]);
    }
    
    /**
     * Pré-computa a matriz de valores heurísticos (Segundo
     * distância de Manhattan para um grid de tamanho igual
     * ao mapa de super.
     * <p>
     * Os valores são diretamente alterados na matriz:
     * {@link #heuristicas}.
     */
    private void setHeuristics(){
        int[] targetCoords = getCoordinatesFromNode(super.idAlvo);
        
        No[][] matrisNos = mapa.getMatriz();
        
        int linhas = mapa.getLinhas();
        int coluna = mapa.getColunas();
        int countId=0;
        for (int i=0; i<linhas; i++){
            for (int j=0; j<coluna; j++){
                int[] actualCoords = getCoordinatesFromNode(countId);
                //System.out.println("i = " + i + ", j = " + j + ", " + Arrays.toString(actualCoords));
                matrisNos[i][j].setHeuristicCost(
                        manhattanDistance(actualCoords, targetCoords));
                countId++;
            }
        }
    }
    
    public AStar(int inicio, int alvo, Mapa mapa) {
        super(inicio, alvo, mapa);       
        setHeuristics();
        
        noAtual = super.mapa.getNoFromId(inicio);
        fim     = super.mapa.getNoFromId(alvo);
    }

    @Override
    public void iniciarBusca() {
        PercursoFrame.getInstance().setVisible(true);
        nosVisitados = new StringBuilder();

        if (noAtual == null){
            System.err.println("Nó inicial nulo!");
            evt = new Evento(this);
            evt.setMsg("Iniciando busca A*...\n Nó nulo." + Integer.toString(noAtual.getId()));
            evt.setEstado(TipoDeEvento.ERRO);
            evt.setId(-1);
        } else if (noAtual.equals(fim)){
            evt = new Evento(this);
            evt.setMsg("Iniciando busca A*...\n Nós inicial e final são iguais... Caminho ''nulo''... " + Integer.toString(noAtual.getId()));
            evt.setEstado(TipoDeEvento.ACHOU);
            evt.setId(noAtual.getId());
            
            PercursoFrame.getInstance().setCaminhoFinal( Busca.gerarLista( noAtual ) );
            
            HALT=true;
        } else {
            evt = new Evento(this);
            evt.setMsg("Iniciando busca A*... Iniciando no nó " + Integer.toString(noAtual.getId()));
            evt.setEstado(TipoDeEvento.PROCURANDO);
            evt.setId(noAtual.getId());
        }
        
        noAtual.quebrarConexoes();
        
        try {
            Controle.lidarComEvento(evt);
        } catch (InterruptedException ex) {
            Logger.getLogger(BuscaProfundidade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void continuar() {
        if (HALT){
            evt.setEstado(TipoDeEvento.ACHOU);
            try {
                Controle.lidarComEvento(evt);
            } catch (InterruptedException ex) {
                Logger.getLogger(BuscaProfundidade.class.getName()).log(Level.SEVERE, null, ex);
            }
            return;
        } //Fail safe
        
        closed.add(noAtual);
        open.remove(noAtual);
        List<No> vizinhos = getNeighbours(noAtual);
        parentNodes(vizinhos, noAtual);
        
        System.out.println("------------------------REPORT-----------------------");
        System.out.println("No atual: " + noAtual.getId());
        System.out.println("NO ATUAL COST: " + noAtual.custoComHeuristica());
        System.out.println("Vizinhos: " + vizinhos.toString());
        System.out.println("No fim  : " + fim.getId());
        System.out.println("OPEN:     " + open.toString());
        System.out.println("Closed:   " + closed.toString());
        System.out.println("------------------------END REPORT-----------------------");
        
        for (No vizinho : vizinhos){
            if (open.contains(vizinho)){
                //Comparação
                //Valor do caminho do no atual + terreno do vizinho
                //Compara com valor caminho do vizinho
                
                if (noAtual.custoDoCaminho() + vizinho.getTipo().getCusto() <
                    vizinho.custoDoCaminho())
                    vizinho.setPai(noAtual);
            }  
        }
        Collections.sort(vizinhos);
        System.out.println("SORT");
        open.addAll(vizinhos);
        System.out.println("COL");
        //NoAtual estiver na vizinhança do final, vai direto, else, menor custo
        if (atualNaVizinhacaDoFinal()){
            fim.setPai(noAtual);
            noAtual = fim;
            
            evt.setId(noAtual.getId());
            evt.setEstado(TipoDeEvento.ACHOU);
        } else {
            List<No> tempo = new ArrayList(open);
            Collections.sort(tempo);
            noAtual = tempo.get(0);
            /*if (!vizinhos.isEmpty()){
                noAtual = vizinhos.get(0);
            } else {
                List<No> tempo = new ArrayList(open);
                Collections.sort(tempo);
                noAtual = tempo.get(0);
            }*/
            
            
            evt.setId(noAtual.getId());
            evt.setEstado(TipoDeEvento.PROCURANDO);
        }
        
        evt.setMsg("Visitando nó " + Integer.toString(noAtual.getId()) + "...\n Lista aberta: " + open.toString() + "\nLista fechada: " + closed.toString() + "\nCAMINHO: " + noAtual.caminhoAteEsseNo() + "\nCusto : " + Integer.toString( noAtual.custoDoCaminho() ) );
        PercursoFrame.getInstance().setCaminhoFinal( Busca.gerarLista(noAtual) );
        //System.out.println("No atual: " + noAtual.getId());
        //System.out.println("No fim  : " + fim.getId());
        //System.out.println("OPEN:     " + open.toString());
        //System.out.println("Closed:   " + closed.toString());
        System.out.println("BEF:");
        try {
            Controle.lidarComEvento(evt);
        } catch (InterruptedException ex) {
            Logger.getLogger(BuscaProfundidade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private boolean atualNaVizinhacaDoFinal(){
        HashSet<No> vizinhosDoAtual = new HashSet(getAllNeighbours(noAtual));
        
        return vizinhosDoAtual.contains(fim);
    }
    
    /**
     * Seta o pai de cada elemento na lista
     * como o passado no segundo parâmetro.
     * @param children Filhos.
     * @param parent   Nó pai de todos. Odin.
     */
    private void parentNodes (List<No> children, No parent){
        children.forEach((child) -> {
            child.setPai(parent);
        });
    }
    
    /**
     * Retorna os vizinhos de um determinado no.
     * @param atual No para obter vizinhos.
     * @return      Vetor de vizinhos do nó.
     */
    private List<No> getNeighbours(No atual){
        int[] atualCoords = getCoordinatesFromNode(atual.getId());
        int lin = atualCoords[0];
        int col = atualCoords[1];
        int[][] neighbours = {
            {lin-1, col  }, //North
            //{lin-1, col+1}, //North East
            {lin  , col+1}, //East
            //{lin+1, col+1}, //South East
            {lin+1, col  }, //South
            //{lin+1, col-1}, //South West
            {lin  , col-1}, //West
            //{lin-1, col-1}, //North West
        };
        
        List<No> lista = new ArrayList();
        
        No[][] mat = mapa.getMatriz();
        No local;
        for (int[] coord : neighbours){
            if ( (coord[0]>-1 && coord[0]<mapa.getLinhas()) && 
                 (coord[1]>-1 && coord[1]<mapa.getColunas())   ){
                local = mat[coord[0]][coord[1]];
                if (!closed.contains(local))
                    lista.add(local);
            }
        }

        return lista;
    }
    
    private List<No> getAllNeighbours(No atual){
                int[] atualCoords = getCoordinatesFromNode(atual.getId());
        int lin = atualCoords[0];
        int col = atualCoords[1];
        int[][] neighbours = {
            {lin-1, col  }, //North
            //{lin-1, col+1}, //North East
            {lin  , col+1}, //East
            //{lin+1, col+1}, //South East
            {lin+1, col  }, //South
            //{lin+1, col-1}, //South West
            {lin  , col-1}, //West
            //{lin-1, col-1}, //North West
        };
        
        List<No> lista = new ArrayList();
        
        No[][] mat = mapa.getMatriz();
        No local;
        for (int[] coord : neighbours){
            if ( (coord[0]>-1 && coord[0]<mapa.getLinhas()) && 
                 (coord[1]>-1 && coord[1]<mapa.getColunas())   ){
                local = mat[coord[0]][coord[1]];
                lista.add(local);
            }
        }

        return lista;
    }
    
}
