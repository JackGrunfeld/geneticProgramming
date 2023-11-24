package a2part2;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import org.jgap.*;
import org.jgap.gp.CommandGene;
import org.jgap.gp.GPProblem;
import org.jgap.gp.IGPProgram;
import org.jgap.gp.function.Add;
import org.jgap.gp.function.Divide;
import org.jgap.gp.function.Multiply;
import org.jgap.gp.function.Subtract;
import org.jgap.gp.impl.DeltaGPFitnessEvaluator;
import org.jgap.gp.impl.GPConfiguration;
import org.jgap.gp.impl.GPGenotype;
import org.jgap.gp.impl.GPProgram;
import org.jgap.gp.terminal.Terminal;
import org.jgap.gp.terminal.Variable;

/**
 *  
 * @author Jack Grunfeld
 *
 */
class SimpleMathTest extends GPProgram {
	   
    private static double[] INPUT_1 = new double[20];
    private static double[] OUTPUT = new double[20] ;

    private Variable _xVariable;
   

    public SimpleMathTest() throws InvalidConfigurationException {
        super((IGPProgram) new GPConfiguration());
        try {
            parseFile();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        GPConfiguration config = getGPConfiguration();

        _xVariable = Variable.create(config, "X", CommandGene.DoubleClass);
        
        config.setGPFitnessEvaluator(new DeltaGPFitnessEvaluator());
        config.setMaxInitDepth(8);
        config.setPopulationSize(1000);
        config.setMaxCrossoverDepth(8);
        config.setFitnessFunction(new GSFitness(INPUT_1 , OUTPUT, _xVariable));
        config.setStrictProgramCreation(true);
    }


    public GPGenotype create() throws InvalidConfigurationException {
        GPConfiguration config = getGPConfiguration();
        // The return type of the GP program.
        Class<?>[] types = { CommandGene.DoubleClass };

        // Arguments of result-producing chromosome: none
        Class<?>[][] argTypes = { {} };

        // Next, we define the set of available GP commands and terminals to
        // use.
        CommandGene[][] nodeSets = {
            {
                _xVariable,
            
                new Add(config, CommandGene.DoubleClass),
                new Subtract(config, CommandGene.DoubleClass),
                new Multiply(config, CommandGene.DoubleClass),
                new Divide(config, CommandGene.DoubleClass),

                
                new Terminal(config, CommandGene.DoubleClass, -99, 99, true)
            }

        };

        GPGenotype result = GPGenotype.randomInitialGenotype(config, types, argTypes,
                nodeSets, 20, true);

        return result;
    }

    public void parseFile() throws IOException{
        try {
            FileInputStream regFile = new FileInputStream("part2/regression.txt"); 
            double[] input1 = new double[20];
            double[] input2 = new double[20];
         
            try (BufferedReader br = new BufferedReader(new InputStreamReader(regFile))) {
				String line;

				int LineNum = 0;
				while ((line = br.readLine()) != null) {
				    if(LineNum >= 2){
				        String[] values = line.split("//s+)");
				        input1[LineNum - 2] = Double.parseDouble(values[0]);
				        input2[LineNum - 2] = Double.parseDouble(values[1]);
				       
				    }
				}
			} catch (FileNotFoundException e) {
				throw e;
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            INPUT_1 = input1;
            OUTPUT = input2;
          
        } catch (FileNotFoundException e) {
            
            e.printStackTrace();
        }

    
      
    }
}
