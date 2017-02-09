/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NeuralNetworks;

import java.io.File;
import org.encog.ConsoleStatusReportable;
import org.encog.StatusReportable;

/**
 *
 * @author JFPS
 */
public class GenericConstraints extends NetworkTest{

    private final int crossK;
    private final double validationPercentage;
    
    
    public GenericConstraints(File glassDataSet, double validationPercentage, int crossValidationK) {
        super(glassDataSet);
        
        this.crossK = crossValidationK;
        this.validationPercentage = validationPercentage;
    }

    @Override
    double getValidationPercentage() {
        return validationPercentage;
    }

    @Override
    StatusReportable getReport() {
        return new ConsoleStatusReportable();
    }

    @Override
    int crossValidationK() {
        return crossK;
    }
    
}
