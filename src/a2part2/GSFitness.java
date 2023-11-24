package a2part2;

import org.jgap.gp.GPFitnessFunction;
import org.jgap.gp.IGPProgram;
import org.jgap.gp.terminal.Variable;

/**
 * This class represents the fitness function for the population. 
 * @author Jack Grunfeld
 *
 */
class GSFitness extends GPFitnessFunction {
    private double[] _input1;
    private double[] _output;
    private Variable _xVariable;
   

    private static Object[] NO_ARGS = new Object[0];

    public GSFitness(double[] iNPUT_1,
            double[] oUTPUT, Variable x) {
        _input1 = iNPUT_1;
        
        _output = oUTPUT;
        _xVariable = x;
       
    }

    @Override
    protected double evaluate(IGPProgram arg0) {
        double result = 0.0f;

        long longResult = 0;
        for (int i = 0; i < _input1.length; i++) {
            // Set the input values
            _xVariable.set(_input1[i]);
 
            // Execute the genetically engineered algorithm
            Double value = arg0.execute_double(0, new Object[0]);

            // The closer longResult gets to 0 the better the algorithm.
            longResult += Math.abs(value - _output[i]);
        }

        result = longResult;

        return result;
    }

}
