/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package salvarcarregar;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import mapa.Mapa;
import mapa.Terreno;

/**
 *
 * @author FREE
 */
public class SalvarMapa {
    public static int salvar(File dirr,String nomeArquivo,Mapa mapa) throws FileNotFoundException
    {
        File novaFile = new File(dirr.getAbsolutePath(),nomeArquivo + ".IAmap");
        PrintWriter writer = new PrintWriter(novaFile);
        StringBuilder sb = new StringBuilder();
        
        sb.append(Integer.toString(mapa.getLinhas())+"\n");
        sb.append(Integer.toString(mapa.getColunas())+"\n");
        
        for (int i=0;i<mapa.getLinhas();i++)
        {
            for (int j=0;j<mapa.getColunas();j++)
            {
                Terreno terrenoLocal = mapa.getMatriz()[i][j].getTipo();
                sb.append(Integer.toString(terrenoLocal.getID())+" ");
            }
            sb.append("\n");
        }
        
        writer.write(sb.toString());
        writer.close();
        return(0);
    }
}
