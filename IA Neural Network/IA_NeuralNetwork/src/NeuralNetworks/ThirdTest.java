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
public final class ThirdTest extends NetworkTest{
   
    public ThirdTest(File data) {
        super(data);
    }

    @Override
    double getValidationPercentage() {
        return 0.4;
    }

    @Override
    StatusReportable getReport() {
        return new ConsoleStatusReportable();
    }

    @Override
    int crossValidationK() {
        return 8;
    }
    
    
}
