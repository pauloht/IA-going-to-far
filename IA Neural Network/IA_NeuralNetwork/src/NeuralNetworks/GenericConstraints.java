/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NeuralNetworks;

import EncogExtra.PanelAndFileStatusReportable;
import java.io.File;
import org.encog.ConsoleStatusReportable;
import org.encog.StatusReportable;

/**
 *
 * @author JFPS
 */
public class GenericConstraints extends NetworkTest{
    private final PanelAndFileStatusReportable stRep;
    private final int crossK;
    private final double validationPercentage;
    
    
    public GenericConstraints(File glassDataSet, double validationPercentage, int crossValidationK, PanelAndFileStatusReportable stRep) {
        super(glassDataSet);
        
        this.crossK = crossValidationK;
        this.validationPercentage = validationPercentage;
        this.stRep = stRep;
    }

    @Override
    double getValidationPercentage() {
        return validationPercentage;
    }

    @Override
    PanelAndFileStatusReportable getReport() {
        return stRep;
    }

    @Override
    int crossValidationK() {
        return crossK;
    }


    
}
