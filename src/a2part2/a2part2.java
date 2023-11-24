package a2part2;

import java.util.*;
import org.jgap.*;
import org.jgap.gp.GPProblem;
import org.jgap.gp.impl.GPGenotype;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


/**
 * The main class of the program - i.e. Everything stems from here
 * 
 *
 * 
 * @author Jack Gruneld
 *
 */
public class a2part2 {
	static double [][] data;
	
	/**
	 * Main method of the program - Loads in all of the data in the regression.txt file
	 * @param args
	 * @throws InvalidConfigurationException
	 */
	//  throws InvalidConfigurationException
	 public static void main(String[] args) throws Exception {
	        SimpleMathTest problem = new SimpleMathTest();
	    
	        GPGenotype gp = problem.create();
	        gp.setVerboseOutput(true);
	        gp.evolve(30);

	        System.out.println("Formula to discover: x^2 + 2y + 3x + 5");
	        gp.outputSolution(gp.getAllTimeBest());
	    }

	
	
	/**
	 * Load in all of the relevant data (x and y vals) from 'regression.txt'
	 */
	public static void loadData() {
		String filePath = "regression.txt";
		try {
			FileReader fileReader = new FileReader(filePath);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String line; 
			int lineNumber = 0; 
			while ((line = bufferedReader.readLine()) != null) {
				if (lineNumber >= 2) { // Skip the first two lines
					 String[] values = line.split("\\s+");
		             data[lineNumber - 2][0] = Double.valueOf(values[1]);
		             data[lineNumber - 2][1] = Double.valueOf(values[2]);
				}
                lineNumber++;
	            }
	        bufferedReader.close();
		}catch (IOException e) {
            e.printStackTrace();
        }
		
	}

}
