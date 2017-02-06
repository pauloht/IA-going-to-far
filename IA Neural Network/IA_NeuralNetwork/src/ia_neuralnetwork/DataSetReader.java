/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia_neuralnetwork;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author JFPS
 */
public class DataSetReader {
    private static final int OFFSET=1;
    private static final int NO_COLUMNS=11;
    
    public static double[][] reader(File dataset) throws FileNotFoundException{
        //Hardcoded, son.
        ArrayList<double[]> list = new ArrayList();
        
        //String csvFile = dataset; //"/data/glass.csv";
        
        BufferedReader br = new BufferedReader(new FileReader(dataset));;
        String line = "";
        String separator = ",";

        try {
            while ((line = br.readLine()) != null) {
                String[] split = line.split(separator);
                double[] tempArray = new double[NO_COLUMNS-OFFSET];
                for (int i=0+OFFSET; i<NO_COLUMNS; i++){
                    tempArray[i-OFFSET] = Double.valueOf(split[i-OFFSET]);
                }
                list.add(tempArray);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        double[][] dataMatrix = new double[9][list.size()];
        for (int i=0; i<list.size(); i++){
            dataMatrix[i] = list.get(i);
        }
        
        return dataMatrix;
    }
}
