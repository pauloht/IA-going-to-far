/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia_neuralnetwork;

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
public final class FirstTest extends NetworkTest{
    /*
    Neste teste, a "Hidden Layer" possuirá o número de neurônios
    igual a média entre o número de entradas e saídas, como a maioria 
    das pesquisas realizadas. Considera-se:
    
    - Como o a função de saída será "Softmax", temos 7 neurônios de saída.
    - Como o número de colunas na matriz de entrada é 9, temos o tal número.
    - Adiciona-se um neurônio "Bias" na camada de entrada e na camada oculta.
    
    Finalmente, temos que o número de neurônios nas 3 camadas são distribuídos
    como:
    
    Entrada - 9 mais um bias.
    Oculta  - 8 mais um bias.
    Saída   - 7.
    */
    
    private static final int ENTRADA=9;
    private static final int OCULTA=8;
    private static final int SAIDA=7;
    
    public FirstTest(File data) {
        super(data);
        
        BasicLayer entrada = new BasicLayer(null, true, ENTRADA);
        BasicLayer oculta  = new BasicLayer(new ActivationSigmoid(), true, OCULTA);
        BasicLayer saida   = new BasicLayer(new ActivationSigmoid(), false, SAIDA);
        
        super.setLayers(entrada, oculta, saida);
    }

    @Override
    double getValidationPercentage() {
        return 0.3;
    }

    @Override
    StatusReportable getReport() {
        return new ConsoleStatusReportable();
    }

    @Override
    int crossValidationK() {
        return 5;
    }
    
    
}
