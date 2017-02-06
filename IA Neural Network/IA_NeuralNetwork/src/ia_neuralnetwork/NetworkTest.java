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
    /**
     * Adiciona um neurônio de bias
     * na camada escondida e um
     * na camada de entrada.
     */
    protected static final int INPUT_HIDDEN_BIAS=0;
    /**
     * Não adiciona neurônios de Bias.
     */
    protected static final int NO_BIAS=3;
    
    private final int inputNeurons;
    private final int hiddenNeurons;
    private final int outputNeurons;
    
    private final BasicNetwork network;

    protected NetworkTest(int ENABLE_BIAS, int inputNeurons, int hiddenNeurons, int outputNeurons) {
        this.inputNeurons = inputNeurons;
        this.hiddenNeurons = hiddenNeurons;
        this.outputNeurons = outputNeurons;
        
        switch(ENABLE_BIAS){
            case INPUT_HIDDEN_BIAS:
                inputNeurons++;
                hiddenNeurons++;
                break;
            case NO_BIAS:
                break;
            default:
                throw new IllegalArgumentException("Código inválido para tipo de Bias.");
        }
        
        this.network = new BasicNetwork();
    }

    protected NetworkTest(int inputNeurons, int hiddenNeurons, int outputNeurons) {
        this(INPUT_HIDDEN_BIAS, inputNeurons, hiddenNeurons, outputNeurons, network);
    }

    protected int getInputNeurons() {
        return inputNeurons;
    }

    protected int getHiddenNeurons() {
        return hiddenNeurons;
    }

    protected BasicNetwork getNetwork() {
        return network;
    }
    
    protected void setLayers(BasicLayer input, BasicLayer hidden, BasicLayer output){
        network.addLayer(input);
        network.addLayer(hidden);
        network.addLayer(output);
        network.getStructure().finalizeStructure();
        network.reset();
    }
    
}
