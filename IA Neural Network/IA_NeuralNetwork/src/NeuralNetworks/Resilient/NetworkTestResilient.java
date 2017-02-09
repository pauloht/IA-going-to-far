/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NeuralNetworks.Resilient;

import java.io.File;
import org.encog.ConsoleStatusReportable;
import org.encog.Encog;
import org.encog.StatusReportable;
import org.encog.ml.data.MLData;
import org.encog.ml.data.MLDataPair;
import org.encog.ml.data.versatile.VersatileMLDataSet;
import org.encog.ml.data.versatile.columns.ColumnDefinition;
import org.encog.ml.data.versatile.columns.ColumnType;
import org.encog.ml.data.versatile.sources.CSVDataSource;
import org.encog.ml.data.versatile.sources.VersatileDataSource;
import org.encog.neural.networks.BasicNetwork;
import org.encog.neural.networks.layers.BasicLayer;
import org.encog.neural.networks.training.propagation.resilient.ResilientPropagation;
import org.encog.util.csv.CSVFormat;

/**
 * Classe para criar um teste
 * de rede neural com três camadas.
 * @author JFPS
 */
public abstract class NetworkTestResilient {
    public static final int ENTRADA=9;
    public static final int MAX_EPOCH=1000;
    
    private final BasicNetwork network;
    private final VersatileMLDataSet dataSet;
    private final File dataFile;
    
    private VersatileMLDataSet defineSource(File glassDataSet){
        VersatileDataSource source = new CSVDataSource(glassDataSet, false, CSVFormat.DECIMAL_POINT);
        VersatileMLDataSet data = new VersatileMLDataSet(source);
        
        data.defineSourceColumn("ID",               0, ColumnType.ignore);
        data.defineSourceColumn("Refractive Index", 1, ColumnType.continuous);
        data.defineSourceColumn("Sodium",           2, ColumnType.continuous);
        data.defineSourceColumn("Magnesium",        3, ColumnType.continuous);
        data.defineSourceColumn("Aluminum",         4, ColumnType.continuous);
        data.defineSourceColumn("Silicon",          5, ColumnType.continuous);
        data.defineSourceColumn("Potassium",        6, ColumnType.continuous);
        data.defineSourceColumn("Calcium",          7, ColumnType.continuous);
        data.defineSourceColumn("Barium",           8, ColumnType.continuous);
        data.defineSourceColumn("Iron",             9, ColumnType.continuous);

        ColumnDefinition outputColumn = data.defineSourceColumn("Type of Glass", 10, ColumnType.nominal);
        data.analyze();
        data.defineSingleOutputOthersInput(outputColumn);
        
        return data;
    }
    
    protected NetworkTestResilient(File glassDataSet) {
        this.network = new BasicNetwork();
        dataFile = glassDataSet;
        dataSet = defineSource(glassDataSet);
    }
    
    protected void setLayers(BasicLayer input, BasicLayer hidden, BasicLayer output){
        network.addLayer(input);
        network.addLayer(hidden);
        network.addLayer(output);
        network.getStructure().finalizeStructure();
        network.reset();    
    }
    
    public void runResilient(){
        ResilientPropagation train = new ResilientPropagation(network, dataSet);
        StatusReportable re = new ConsoleStatusReportable();
        
        int epoch = 1;

        do {
            if (epoch > MAX_EPOCH){
                System.out.println("MAX EPOCH REACHED. EXITING TRAINING.");
                break;
            }
            
            train.iteration();
            System.out.println("Epoch #" + epoch + " Error:" + train.getError());
            epoch++;
        } while(train.getError() > 0.01);
        train.finishTraining();

        // test the neural network
        System.out.println("Neural Network Results:");
        for(MLDataPair pair: dataSet ) {
            final MLData output = network.compute(pair.getInput());
            System.out.println(pair.getInput().getData(0) + "," + pair.getInput().getData(1)
                            + ", actual=" + output.getData(0) + ",ideal=" + pair.getIdeal().getData(0));
        }
    }
    
    public void shutdown(){
        Encog.getInstance().shutdown();
    }
}
