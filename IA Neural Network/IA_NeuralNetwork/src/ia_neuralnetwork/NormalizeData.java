/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia_neuralnetwork;

import java.util.ArrayList;
import java.util.List;

/**
 * Normalização de dados.
 * Como os dados no exemplo 
 * são todos positivos usa-se
 * isso como artifício.
 * 
 * @author JFPS
 */
public class NormalizeData {
    private List<Double> maxArray;

    public NormalizeData() {
        maxArray = new ArrayList<>();
    }
    
    private double max(double[] array){
        double max = array[0];
        if (array.length == 1) return max;
        
        for (int i=1; i<array.length; i++){
            if (array[i] > max) max = array[i];
        }
        maxArray.add(max);
        return max;
    }
    
    /**
     * Normalização de matrizes.
     * Divide-se cada elemento de
     * cada coluna pelo máximo de
     * cada coluna.
     * 
     * @param data
     * @return 
     */
    public double[][] normalizeMatrix(double[][] data){
        maxArray = new ArrayList();
        
        for (int i=0; i<data.length; i++){
            double maxx = max(data[i]);
            for (int j=0; j<data[i].length; j++){
                data[i][j] = data[i][j]/maxx;
            }
        }
        
        return data;
    }
    
    /**
     * Normalização de arrays.
     * Divide-se cada elemento 
     * pelo máximo de cada coluna
     * em {@link #maxArray}.
     * 
     * @param data Uma entrada para a rede neural.
     * @return data normalizada.
     */
    private double[] normalizeInput(double[] data){
        if (maxArray == null || maxArray.isEmpty()) 
            throw new IllegalStateException(
            "Array de valores máximos não inicializado. "
                    + "Rede possivelmente não treinada.");
        
        for (int i=0; i<data.length; i++){
            data[i] = data[i]/maxArray.get(i);
        }
        return data;
    }
    
    
}
