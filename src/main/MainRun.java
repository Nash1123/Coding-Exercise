package main;

import java.util.*;
import simulation.*;
import numerical.*;

import com.mathworks.toolbox.javabuilder.*;


public class MainRun {
	public static void main(String[] args) throws MWException {
		Scanner in = new Scanner(System.in);
		System.out.println("Please enter the probability of face 1 of coin 1 (%): ");
		double p_1_c1 = in.nextDouble();
		System.out.println("Please enter the probability of face 1 of coin 2 (%): ");
		double p_1_c2 = in.nextDouble();
		System.out.println("Please enter the prefered run times: ");
		int runTimes = in.nextInt();
		System.out.println("Please enter the prefered simulation times: ");
		int simulationTimes = in.nextInt();
		
		if (p_1_c1 > p_1_c2) {
			double temp = p_1_c1;
			p_1_c1 = p_1_c2;
			p_1_c2 = temp;
		}
		p_1_c1 /= 100;
		p_1_c2 /= 100;
		
		int runTimesStep = runTimes / 100;
		List<Double> numerical_errorRateList = new ArrayList<Double>();
		List<Double> simulation_errorRateList = new ArrayList<Double>();
		List<Integer> runTimeList = new ArrayList<Integer>();
		for (int i = 0; i <= runTimes; i += runTimesStep) {
			System.out.println("Run Times: " + i);
			List<Object> numerical_Result = NumericalAnalysis.run(p_1_c1, p_1_c2, i);
			double numerical_ErrorRate = Simulation.run(p_1_c1, p_1_c2, i, simulationTimes, (Integer)numerical_Result.get(1));
			System.out.println();
			runTimeList.add(i);
			numerical_errorRateList.add((Double)numerical_Result.get(0));
			simulation_errorRateList.add(numerical_ErrorRate);
		}
		Plot.drawplot(NumericalAnalysis.cdf1_current[0], NumericalAnalysis.cdf2_current[0], runTimeList, numerical_errorRateList, simulation_errorRateList);
	}
}
