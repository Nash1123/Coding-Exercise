package main;

import java.util.*;

import com.mathworks.toolbox.javabuilder.*;
import plotter.*;

public class Plot {	
	public static void drawplot(double[] cdf1,double[] cdf2, List<Integer> runTimeList, List<Double> numerical_errorRateList, List<Double> simulation_errorRateList) throws MWException {
		int len = cdf1.length;
		int[] dims1 = {1, len};
		MWNumericArray y_cdf1 = MWNumericArray.newInstance(dims1, MWClassID.DOUBLE, MWComplexity.REAL);
		MWNumericArray y_cdf2 = MWNumericArray.newInstance(dims1, MWClassID.DOUBLE, MWComplexity.REAL);
		for (int i = 0; i < len; i++) {
			y_cdf1.set(i + 1, cdf1[i]);
			y_cdf2.set(i + 1, cdf2[i]);
		}
		int size = runTimeList.size();
		int[] dims2 = {1, size};
		MWNumericArray x_runtimes = MWNumericArray.newInstance(dims2, MWClassID.INT32, MWComplexity.REAL);
		MWNumericArray y_numerical_errorRate = MWNumericArray.newInstance(dims2, MWClassID.DOUBLE, MWComplexity.REAL);
		MWNumericArray y_simulation_errorRate = MWNumericArray.newInstance(dims2, MWClassID.DOUBLE, MWComplexity.REAL);
		for (int i = 0; i < size; i++) {
			x_runtimes.set(i + 1, runTimeList.get(i));
			y_numerical_errorRate.set(i + 1, numerical_errorRateList.get(i));
			y_simulation_errorRate.set(i + 1, simulation_errorRateList.get(i));
		}
		Plotter plotter = new Plotter();
		plotter.drawplot(y_cdf1, y_cdf2, x_runtimes, y_numerical_errorRate, y_simulation_errorRate);
	}
}
