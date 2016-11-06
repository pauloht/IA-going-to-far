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
    
    HashSet<No> open;
    HashSet<No> closed; //Visitados
    
    private No fim;
    
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
        int linhas = mapa.getLinhas();
        int coluna = mapa.getColunas();
        int linInc=-1;
        int colInc=-1;
        
        int countId=1;
        for (int i=0; i<linhas; i++){
            for (int j=0; j<coluna; j++){
                if (countId == idAlvo){
                    linInc = i;
                    colInc = j;
                    break;
                }
                countId++;
            }
        }

        /*
        int div;
        int mod;
        for (int i=1; i<=linhas; i++){
            div = i/linhas;
            mod = i%linhas;
            if(div==1 && mod==0){
                linInc = i;
                break;
            }
        }
        
        for (int i=1; i<=coluna; i++){
            div = i/coluna;
            mod = i%coluna;
            if(div==1 && mod==0){
                colInc = i;
                break;
            }
        }
        */
        int[] coords = {linInc, colInc};
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
        int countId=1;
        for (int i=0; i<linhas; i++){
            for (int j=0; j<coluna; j++){
                int[] actualCoords = getCoordinatesFromNode(countId);
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

        evt = new Evento(this);
        evt.setMsg("Iniciando busca A*... Iniciando no nó " + Integer.toString(noAtual.getId()));
        evt.setEstado(TipoDeEvento.PROCURANDO);
        evt.setId(noAtual.getId());

        noAtual.quebrarConexoes();
        if (noAtual == null){
            System.err.println("Nó inicial nulo!");
        }
        
        try {
            Controle.lidarComEvento(evt);
        } catch (InterruptedException ex) {
            Logger.getLogger(BuscaProfundidade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void continuar() {
        closed.add(noAtual);
        open.remove(noAtual);
        List<No> vizinhos = getNeighbours(noAtual);
        parentNodes(vizinhos, noAtual);
        
        for (No vizinho : vizinhos){
            if (closed.contains(vizinho)){
                continue;
            }
            
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
        open.addAll(vizinhos);
        
        //NoAtual estiver na vizinhança do final, vai direto, else, menor custo
        if (atualNaVizinhacaDoFinal()){
            fim.setPai(noAtual);
            noAtual = fim;
            
            /*
            OPERAÇÕES DE FINALIZAÇÃO
            RETORNAR CAMINHO E O CARALEO 
            */
            
            evt.setId(noAtual.getId());
            evt.setEstado(TipoDeEvento.ACHOU);
            
        } else {
            noAtual = vizinhos.get(0);
            
            evt.setId(noAtual.getId());
            evt.setEstado(TipoDeEvento.PROCURANDO);
        }
        
        evt.setMsg("Visitando nó " + Integer.toString(noAtual.getId()) + "...\n Lista aberta: " + open.toString() + "\nLista fechada: " + closed.toString() + "\n");
        
        try {
            Controle.lidarComEvento(evt);
        } catch (InterruptedException ex) {
            Logger.getLogger(BuscaProfundidade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private boolean atualNaVizinhacaDoFinal(){
        HashSet<No> vizinhosDoAtual = new HashSet(getNeighbours(noAtual));
        
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
        
        for (int[] coord : neighbours){
            if ( (coord[0]>-1 && coord[0]<mapa.getLinhas()) && 
                 (coord[1]>-1 && coord[1]<mapa.getColunas())   ){
                lista.add(mat[coord[0]][coord[1]]);
            }
        }

        return lista;
    }
    
}
