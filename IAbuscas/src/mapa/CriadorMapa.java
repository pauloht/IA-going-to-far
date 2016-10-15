/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapa;

import java.util.Scanner;

/**
 *
 * @author FREE
 */
public class CriadorMapa {
    public static Mapa criarMapa()
    {
        Scanner scam = new Scanner(System.in);
        System.out.println("Numero de linhas:(min: 1 max 50)");
        int n_linhas = scam.nextInt();
        System.out.println("Numero de colunas:(min: 1 max 50)");
        int n_colunas = scam.nextInt();
        Terreno[][] tipo = new Terreno[n_linhas][n_colunas];
        int valor = 0;
        for (int i=0;i<n_linhas;i++)
        {
            
            for (int j=0;j<n_colunas;j++)
            {
                
                tipo[i][j] = Terreno.PLANO;
                
            }
            
        }
        Mapa retorno = new Mapa(n_linhas,n_colunas,tipo);
        return(retorno);
    }
}
