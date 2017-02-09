/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NeuralNetworks;

import EncogExtra.PanelAndFileStatusReportable;
import ia_neuralnetwork.Glass;
import java.io.File;
import java.util.Arrays;
import org.encog.ConsoleStatusReportable;
import org.encog.Encog;
import org.encog.StatusReportable;
import org.encog.engine.network.activation.ActivationSigmoid;
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
    public final BasicNetwork network = new BasicNetwork();
    
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
    
    protected NetworkTest(File glassDataSet) {
        dataFile = glassDataSet;
        dataSet = defineSource(glassDataSet);
    }
    
    abstract double getValidationPercentage();
    
    abstract PanelAndFileStatusReportable getReport();
    
    abstract int crossValidationK();
    
    //abstract PanelAndFileStatusReportable getR();
    
    private void print(String st){
        System.out.println(st + "\n");
        getReport().print(st + "\n");
    }
    
    public void run(){
        try {
            EncogModel model = new EncogModel(dataSet);
            model.selectMethod(dataSet, MLMethodFactory.TYPE_FEEDFORWARD);
            
            //model.setReport(new ConsoleStatusReportable());
            model.setReport(getReport());
            dataSet.normalize();

            model.holdBackValidation(getValidationPercentage(), SHUFFLE_DATA, SEED_TO_SHUFFLE);
            model.selectTrainingType(dataSet);
            //MLRegression regr = (MLRegression)model.
            MLRegression regr = (MLRegression)model.crossvalidate(crossValidationK(), SHUFFLE_DATA);

                       
            // Display the training and validation errors.
            print( "Training error: " + EncogUtility.calculateRegressionError(regr, model.getTrainingDataset()));
            print( "Validation error: " + EncogUtility.calculateRegressionError(regr, model.getValidationDataset()));

            NormalizationHelper helper = dataSet.getNormHelper();
            print(helper.toString());

            print("Final model: " + regr);

            ReadCSV csv = new ReadCSV(dataFile, false, CSVFormat.DECIMAL_POINT);
            String[] line = new String[10];
            MLData input = helper.allocateInputVector();

            while(csv.next()) {
                    StringBuilder result = new StringBuilder();
                    for (int i=0; i<line.length; i++){
                        line[i] = csv.get(i+1);
                    }
                    
                    String correct = csv.get(10);
                    helper.normalizeInputVector(line,input.getData(),false);
                    MLData output = regr.compute(input);
                    String glass = helper.denormalizeOutputVectorToString(output)[0];

                    result.append(Arrays.toString(line));
                    result.append(" -> previsto: ");
                    result.append(Glass.viaID(Integer.parseInt(glass)).getNome())
                          .append("(").append(glass).append(").");
                    result.append(" -> correto(Do Dataset): ");
                    result.append(Glass.viaID(Integer.parseInt(correct)).getNome())
                          .append("(").append(correct).append(").");
                    result.append(")");

                    print(result.toString());
            }
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
        }
    }
    
    public void shutdown(){
        print("SHUTING DOWN.\n\n\n\n\n\n");
        Encog.getInstance().shutdown();
    }
}
