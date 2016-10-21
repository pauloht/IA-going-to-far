/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package salvarcarregar;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import mapa.Mapa;
import mapa.Terreno;

/**
 *
 * @author FREE
 */
public class CarregarMapa {
    public static Mapa carregarMapa(File file)
    {
        try{
        FileReader fr = new FileReader(file);

        BufferedReader br = new BufferedReader(fr);
        String linha = "";
        
        linha = br.readLine();
        
        int nLinha = Integer.parseInt(linha);
       
        linha = br.readLine();
        
        int nColuna = Integer.parseInt(linha);
        
        Mapa retorno = new Mapa(nLinha,nColuna);
        
        String[] aux;
        
        for (int i=0;i<nLinha;i++)
        {
            linha = br.readLine();
            if (linha == null)
            {
                throw new FileCorrumpidaException("Linhas faltando");
            }
            aux = linha.split(" ");
            if (aux.length!=nColuna)
            {
                throw new FileCorrumpidaException("Numero de colunas incorreto");
            }
            for (int j=0;j<aux.length;j++)
            {
                retorno.getMatriz()[i][j].setTipo( Terreno.getTerrenoFromID(Integer.parseInt( aux[j] ) ) );
            }
        }
            return(retorno);
        }
        catch(IOException | NumberFormatException | FileCorrumpidaException e)
        {
            e.printStackTrace();
            return(null);
        }
    }
}
