/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapa;

/**
 *
 * @author FREE
 */
public class Mapa {
    No[][] matriz;
    int linhas;
    int colunas;
    
    Mapa(int linhas,int colunas,Terreno[][] tipo)
    {
        this.linhas = linhas;
        this.colunas = colunas;
        matriz = new No[linhas][colunas];
        for (int i=0;i<linhas;i++)
        {
            for (int j=0;j<colunas;j++)
            {
                
                matriz[i][j] = new No(tipo[i][j], i*colunas + j);
                
            }
        }
        
        for (int i=0;i<linhas;i++)
        {
            for (int j=0;j<colunas;j++)
            {
                
                No local = matriz[i][j];
                
                if ( i>0 )//tem norte
                {
                    local.setNorte( matriz[i-1][j] );
                }
                if ( i<linhas-1 )//tem sul
                {
                    local.setSul( matriz[i+1][j]);
                }
                if ( j>0 )//tem oeste
                {
                    local.setOeste( matriz[i][j-1]);
                }
                if ( j<colunas-1 )//tem leste
                {
                    local.setLeste( matriz[i][j+1]);
                }
                
            }
        }
    }

    public void printMe(){
        for (int i=0;i<linhas;i++)
        {
            
            for (int j=0;j<colunas;j++)
            {
                No atual = matriz[i][j];
                System.out.println("ID atual : " + atual.getId());
                System.out.print("Norte : ");
                if (atual.getNorte()==null)
                {
                    System.out.println("NULL");
                }
                else
                {
                    System.out.println(atual.getNorte().getId());
                }
                
                System.out.print("Leste : ");
                if (atual.getLeste()==null)
                {
                    System.out.println("NULL");
                }
                else
                {
                    System.out.println(atual.getLeste().getId());
                }
                
                System.out.print("Sul : ");
                if (atual.getSul()==null)
                {
                    System.out.println("NULL");
                }
                else
                {
                    System.out.println(atual.getSul().getId());
                }
                
                System.out.print("Oeste : ");
                if (atual.getOeste() == null)
                {
                    System.out.println("NULL");
                }
                else
                {
                    System.out.println(atual.getOeste().getId());
                }
                
                System.out.println("\n");
                
            }
            
        }
    }

    public No[][] getMatriz() {
        return matriz;
    }

    public int getLinhas() {
        return linhas;
    }

    public int getColunas() {
        return colunas;
    }
    
    public int nosVisitados()
    {
        int contador = 0;
        for (int i=0;i<linhas;i++)
        {
            
            for (int j=0;j<colunas;j++)
            {
                
                No atual = matriz[i][j];
                if (atual.isVisitado())
                {
                    contador++;
                }
            }
            
        }
        return(contador);
    }
    
    
}
