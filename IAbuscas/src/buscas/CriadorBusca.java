/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package buscas;

import java.util.Scanner;
import mapa.Mapa;
import mapa.No;

/**
 *
 * @author FREE
 */
public class CriadorBusca {
    public static void criarBusca(Mapa mapa)
    {
        Scanner scam = new Scanner(System.in);
        No inicio = null;
        int idInicio = -1;
        int idFim = -1;
        while (inicio==null)
        {
            System.out.println("Qual o nó inicio?");
            idInicio = scam.nextInt();
            
            inicio = encontrarNo(mapa,idInicio);
        }
        
        No fim = null;
        while (fim==null)
        {
            System.out.println("Qual o nó alvo?");
            idFim = scam.nextInt();
            
            fim = encontrarNo(mapa,idFim);
        }
       
        
        Busca busca = new BuscaProfundidade(idInicio,idFim,mapa);
        busca.iniciarBusca(inicio);
        
        //System.out.println("Qual o tipo de busca?");
    }
    
    private static No encontrarNo(Mapa mapa,int id)
    {
        int nLinhas = mapa.getLinhas();
        int nColunas = mapa.getColunas();
        for (int i=0;i<nLinhas;i++)
        {
            
            for (int j=0;j<nColunas;j++)
            {
                
                No atual = mapa.getMatriz()[i][j];
                if (atual.getId() == id)
                {
                    return atual;
                }
            }
            
        }
        System.out.println("nó invalido!");
        return(null);
    }
}
