/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NeuralNetworks;

import EncogExtra.PanelAndFileStatusReportable;
import java.io.File;
import org.encog.StatusReportable;

/**
 *
 * @author JFPS
 */
public final class FirstTest extends NetworkTest{ 
    private final PanelAndFileStatusReportable stRep;
    
    public FirstTest(File data, PanelAndFileStatusReportable rep) {
        super(data);
        this.stRep = rep;
    }

    @Override
    double getValidationPercentage() {
        return 0.3;
    }

    @Override
    PanelAndFileStatusReportable getReport() {
        return stRep;
    }

    @Override
    int crossValidationK() {
        return 5;
    }   
}
