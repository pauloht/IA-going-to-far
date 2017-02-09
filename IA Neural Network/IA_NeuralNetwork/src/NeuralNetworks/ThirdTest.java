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
public final class ThirdTest extends NetworkTest{
    private final PanelAndFileStatusReportable stRep;
       
    public ThirdTest(File data, PanelAndFileStatusReportable stRep) {
        super(data);
        this.stRep = stRep;
    }

    @Override
    double getValidationPercentage() {
        return 0.2;
    }

    @Override
    PanelAndFileStatusReportable getReport() {
        return stRep;
    }

    @Override
    int crossValidationK() {
        return 3;
    }
   
    
}
