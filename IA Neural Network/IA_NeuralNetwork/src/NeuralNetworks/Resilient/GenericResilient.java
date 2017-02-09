/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NeuralNetworks.Resilient;

import static NeuralNetworks.Resilient.NetworkTestResilient.ENTRADA;
import java.io.File;
import org.encog.engine.network.activation.ActivationSigmoid;
import org.encog.neural.networks.layers.BasicLayer;

/**
 *
 * @author JFPS
 */
public class GenericResilient extends NetworkTestResilient{

    private final int nOcultos;
    private final int nSaida;
    
    
    public GenericResilient(File glassDataSet, int nOcultos, int nSaida) {
        super(glassDataSet);
        
        this.nOcultos = nOcultos;
        this.nSaida   = nSaida;
        
        BasicLayer entrada = new BasicLayer(null, true, ENTRADA);
        BasicLayer oculta  = new BasicLayer(new ActivationSigmoid(), true, nOcultos);
        BasicLayer saida   = new BasicLayer(new ActivationSigmoid(), false, nSaida);
    }    
}
