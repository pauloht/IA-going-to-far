/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia_neuralnetwork;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author JFPS
 */
public class IA_NeuralNetwork {

    private static final double TRAINING_PERCENTAGE = 0.7;
    private static final double VALIDATE_PRECENTAGE = 0.2;
    private static final double TESTING_PERCENTAGE  = 0.5;
    
    private static final File DATASET = new File("data/glass.csv");
    
    //private static double[][] inputTOTAL;
    //private static double[]  outputTOTAL;
    
    /**
     * Array utilizado como entrada na
     * etapa de treinamento.
     */
    private static double[][] inputTrain;
    /**
     * Array utilizado como comparação de
     * saída na etapa de treinamento.
     */
    private static double[]  outputTrain;
    
    /**
     * Array utilizado como entrada na
     * etapa de validação.
     */
    private static double[][] inputValidate;
    /**
     * Array utilizado como comparação de
     * saída na etapa de validação.
     */
    private static double[]  outputValidate;
    
    /**
     * Array utilizado como entrada na
     * etapa de teste.
     */
    private static double[][] inputTest;
    
    private static double[][] matrixCopyAllButLast(double[][]in){
        double[][] out = new double[in.length][in[0].length-1]; 
        
        for (int i = 0; i < in.length; i++) {
            System.arraycopy(in[i], 0, out[i], 0, in[0].length-1);
        }
        return out;
    }
    
    private static double[] getLastColumn(double[][] in){
        double[] lastColumnAsVector = new double[in.length];
        for(int i=0; i<in.length; i++){
            lastColumnAsVector[i] = in[i][in[i].length-1];
        }
        return lastColumnAsVector;
    }
    
    private static double[][] matrixCopy(double[][] src, int begin, int end){
        double[][] out = new double[src.length][src[0].length-1]; 
        
        for (int i = begin; i < end; i++) {
            System.arraycopy(src[i], 0, out[i], 0, src[0].length);
        }
        return out;
    }
    
    /**
     * Seta as variáveis {@link #inputTest}, {@link #outputTest},
     * {@link #inputTrain}, {@link #outputTest},
     * {@link #inputValidate} e {@link #outputValidate}.
     * 
     * @param inputTOTAL Dados de entrada em sua totalidade
     * @param outputTOTAL Dados de saída em sua totalidad
     */
    private static void setInputsAndOutputs(double[][] inputTOTAL, double[] outputTOTAL){
        final int MIN = 0;
        
        int lines     = (int) Math.round(TRAINING_PERCENTAGE*outputTOTAL.length);
        int max       = outputTOTAL.length - lines - 1; //- por numerar de 0
        int randomNum = ThreadLocalRandom.current().nextInt(MIN, max + 1);
        inputTrain    = matrixCopy(inputTOTAL, randomNum, randomNum+lines);
        System.arraycopy(inputTOTAL, randomNum, outputTrain, 0, lines);
        
        lines         = (int) Math.round(VALIDATE_PRECENTAGE*outputTOTAL.length);
        max           = outputTOTAL.length - lines - 1; //- por numerar de 0
        randomNum     = ThreadLocalRandom.current().nextInt(MIN, max + 1);
        inputValidate = matrixCopy(inputTOTAL, randomNum, randomNum+lines);
        System.arraycopy(inputTOTAL, randomNum, outputValidate, 0, lines);
        
        lines     = (int) Math.round(TESTING_PERCENTAGE*outputTOTAL.length);
        max       = outputTOTAL.length - lines - 1; //- por numerar de 0
        randomNum = ThreadLocalRandom.current().nextInt(MIN, max + 1);
        inputTest = matrixCopy(inputTOTAL, randomNum, randomNum+lines);
    }
    
    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {
        double[][] rawData = DataSetReader.reader(DATASET);
        
        NormalizeData normalize = new NormalizeData();
        rawData = normalize.normalizeMatrix(rawData);
        
        setInputsAndOutputs(matrixCopyAllButLast(rawData), getLastColumn(rawData));
        
        
        
        
        
    }
    
}
