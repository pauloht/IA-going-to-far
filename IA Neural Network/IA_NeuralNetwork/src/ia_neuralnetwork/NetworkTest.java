/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia_neuralnetwork;

import java.io.File;
import java.util.Arrays;
import org.encog.ConsoleStatusReportable;
import org.encog.Encog;
import org.encog.app.analyst.util.AnalystReportBridge;
import org.encog.ml.MLRegression;
import org.encog.ml.data.MLData;
import org.encog.ml.data.versatile.NormalizationHelper;
import org.encog.ml.data.versatile.VersatileMLDataSet;
import org.encog.ml.data.versatile.columns.ColumnDefinition;
import org.encog.ml.data.versatile.columns.ColumnType;
import org.encog.ml.data.versatile.sources.CSVDataSource;
import org.encog.ml.data.versatile.sources.VersatileDataSource;
import org.encog.ml.factory.MLMethodFactory;
import org.encog.ml.model.EncogModel;
import org.encog.neural.networks.BasicNetwork;
import org.encog.neural.networks.layers.BasicLayer;
import org.encog.util.csv.CSVFormat;
import org.encog.util.csv.ReadCSV;
import org.encog.util.simple.EncogUtility;

/**
 * Classe para criar um teste
 * de rede neural com três camadas.
 * @author JFPS
 */
public abstract class NetworkTest {
    public static final int SEED_TO_SHUFFLE = 666;
    public static final boolean SHUFFLE_DATA = true;
    
    private final BasicNetwork network;
    private final VersatileMLDataSet dataSet;
    
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
    
    protected NetworkTest(File glassDataSet) {
        this.network = new BasicNetwork();
        dataSet = defineSource(glassDataSet);
    }
    
    protected void setLayers(BasicLayer input, BasicLayer hidden, BasicLayer output){
        network.addLayer(input);
        network.addLayer(hidden);
        network.addLayer(output);
        network.getStructure().finalizeStructure();
        network.reset();
    }
    
    abstract double getValidationPercentage();
    
    protected void run(){
        try {
            EncogModel model = new EncogModel(dataSet);
            model.selectMethod(dataSet, MLMethodFactory.TYPE_FEEDFORWARD);

            model.setReport(new ConsoleStatusReportable());
            dataSet.normalize();

            model.holdBackValidation(getValidationPercentage(), SHUFFLE_DATA, SEED_TO_SHUFFLE);
            model.selectTrainingType(dataSet);

            MLRegression bestMethod = (MLRegression)model.crossvalidate(5, true);

            
            
            asfdaskdfjasdçlfkjasdfkçl
                    
                    
                    MUDAR AQUIç
                            
                            açslkfajsçf
                            
                            sdfçaksfjçasf
                                    fas
                                    
                                    fas
                                            fsda
                                            fdsa
            
            // Display the training and validation errors.
            System.out.println( "Training error: " + EncogUtility.calculateRegressionError(bestMethod, model.getTrainingDataset()));
            System.out.println( "Validation error: " + EncogUtility.calculateRegressionError(bestMethod, model.getValidationDataset()));

            // Display our normalization parameters.
            NormalizationHelper helper = dataSet.getNormHelper();
            System.out.println(helper.toString());

            // Display the final model.
            System.out.println("Final model: " + bestMethod);

            // Loop over the entire, original, dataset and feed it through the model.
            // This also shows how you would process new data, that was not part of your
            // training set.  You do not need to retrain, simply use the NormalizationHelper
            // class.  After you train, you can save the NormalizationHelper to later
            // normalize and denormalize your data.
            ReadCSV csv = new ReadCSV(irisFile, false, CSVFormat.DECIMAL_POINT);
            String[] line = new String[4];
            MLData input = helper.allocateInputVector();

            while(csv.next()) {
                    StringBuilder result = new StringBuilder();
                    line[0] = csv.get(0);
                    line[1] = csv.get(1);
                    line[2] = csv.get(2);
                    line[3] = csv.get(3);
                    String correct = csv.get(4);
                    helper.normalizeInputVector(line,input.getData(),false);
                    MLData output = bestMethod.compute(input);
                    String irisChosen = helper.denormalizeOutputVectorToString(output)[0];

                    result.append(Arrays.toString(line));
                    result.append(" -> predicted: ");
                    result.append(irisChosen);
                    result.append("(correct: ");
                    result.append(correct);
                    result.append(")");

                    System.out.println(result.toString());
            }

            // Delete data file ande shut down.
            irisFile.delete();
            Encog.getInstance().shutdown();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    
}
