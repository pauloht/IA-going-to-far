/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NeuralNetworks.Resilient;

import NeuralNetworks.*;
import java.io.Console;
import java.io.File;
import org.encog.ConsoleStatusReportable;
import org.encog.StatusReportable;
import org.encog.engine.network.activation.ActivationSigmoid;
import org.encog.engine.network.activation.ActivationSoftMax;
import org.encog.neural.networks.layers.BasicLayer;

/**
 *
 * @author JFPS
 */
public final class FirstResilient extends NetworkTestResilient{

    private static final int OCULTA=8;
    private static final int SAIDA=7;
    
    public FirstResilient(File data) {
        super(data);
        
        BasicLayer entrada = new BasicLayer(null, true, ENTRADA);
        BasicLayer oculta  = new BasicLayer(new ActivationSigmoid(), true, OCULTA);
        BasicLayer saida   = new BasicLayer(new ActivationSigmoid(), false, SAIDA);
        
        super.setLayers(entrada, oculta, saida);
    } 
    
}
