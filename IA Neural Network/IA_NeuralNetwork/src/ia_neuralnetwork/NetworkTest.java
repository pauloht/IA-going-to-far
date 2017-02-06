/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia_neuralnetwork;

import org.encog.neural.networks.BasicNetwork;
import org.encog.neural.networks.layers.BasicLayer;

/**
 * Classe para criar um teste
 * de rede neural com três camadas.
 * @author JFPS
 */
public abstract class NetworkTest {
    private final BasicNetwork network;

    protected NetworkTest() {
        this.network = new BasicNetwork();
    }
    
    protected void setLayers(BasicLayer input, BasicLayer hidden, BasicLayer output){
        network.addLayer(input);
        network.addLayer(hidden);
        network.addLayer(output);
        network.getStructure().finalizeStructure();
        network.reset();
    }
    
}
