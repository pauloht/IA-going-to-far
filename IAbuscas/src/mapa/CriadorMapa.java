/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapa;

import java.util.Scanner;
import view.PercursoFrame;

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
        if (n_linhas < 0)
        {
            n_linhas = 1;
        }
        if (n_linhas > 50)
        {
            n_linhas = 50;
        }
        
        System.out.println("Numero de colunas:(min: 1 max 50)");
        int n_colunas = scam.nextInt();
        if (n_colunas < 0)
        {
            n_colunas = 1;
        }
        if (n_colunas > 50)
        {
            n_colunas = 50;
        }
        
        Terreno[][] tipo = new Terreno[n_linhas][n_colunas];
        int valor = 0;
        for (int i=0;i<n_linhas;i++)
        {
            
            for (int j=0;j<n_colunas;j++)
            {
                
                tipo[i][j] = Terreno.LAVA;
                
            }
            
        }
        Mapa retorno = new Mapa(n_linhas,n_colunas,tipo);
        
        PercursoFrame.setInstanceDim(retorno);
        
        return(retorno);
    }
}
