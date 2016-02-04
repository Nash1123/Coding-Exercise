package main;

import java.util.*;

import com.mathworks.toolbox.javabuilder.*;

import convolution.*;

public class FindRuntimes {
	public static void main(String[] args) throws MWException {		
		Scanner in = new Scanner(System.in);
		System.out.println("Please enter the probability of 1 of coin 1 (%): ");
		double p_1_c1 = in.nextDouble();
		System.out.println("Please enter the probability of 1 of coin 2 (%): ");
		double p_1_c2 = in.nextDouble();
		System.out.println("Please enter the expected error rate (%): ");
		double errorRate = in.nextDouble();
		
		if (p_1_c1 > p_1_c2) {
			double temp = p_1_c1;
			p_1_c1 = p_1_c2;
			p_1_c2 = temp;
		}
		p_1_c1 /= 100;
		p_1_c2 /= 100;
		
		double[] cdf1_once = {(double)1 - p_1_c1, p_1_c1};
		double[] cdf2_once = {(double)1 - p_1_c2, p_1_c2};
		double[][] cdf1_current;
		double[][] cdf2_current;
		
		MWNumericArray cdf1MW_once = new MWNumericArray(cdf1_once, MWClassID.DOUBLE);
		MWNumericArray cdf2MW_once = new MWNumericArray(cdf2_once, MWClassID.DOUBLE);
		MWNumericArray cdf1MW_current = new MWNumericArray(1, MWClassID.DOUBLE);;
		MWNumericArray cdf2MW_current = new MWNumericArray(1, MWClassID.DOUBLE);
		MWNumericArray timesMW = new MWNumericArray(1, MWClassID.INT32);
		
		double numerical_ErrorRate = 50;
		Convolution conv = new Convolution();
		
		int runTimes = 0;
		while (numerical_ErrorRate > errorRate) {
			Object[] output1 = conv.convolution(1, cdf1MW_current ,cdf1MW_once, timesMW);
			Object[] output2 = conv.convolution(1, cdf2MW_current ,cdf2MW_once, timesMW);
			cdf1_current = (double[][])(((MWArray)output1[0]).toArray());
			cdf2_current = (double[][])(((MWArray)output2[0]).toArray());
			
			int len = cdf1_current[0].length;
			int decisionValue= 0;
			int maxIndex = -1;
			for (int i = 0; i < len; i++) {
				if (cdf1_current[0][i] > cdf1_current[0][i + 1]) {		
					maxIndex = i;
					break;
				}
			}
			for (int i = maxIndex; i < len; i++) {	
				if (cdf1_current[0][i] <= cdf2_current[0][i]) {			// == decisionValue -> treat as coin2
					decisionValue = i;
					break;
				}
			}	
			
			numerical_ErrorRate = 0;
			for (int i = 0; i < len; i++) {
				if (i < decisionValue) {
					numerical_ErrorRate += cdf2_current[0][i];
				}else {
					numerical_ErrorRate += cdf1_current[0][i];
				}
			}
			cdf1MW_current = new MWNumericArray(cdf1_current[0], MWClassID.DOUBLE);
			cdf2MW_current = new MWNumericArray(cdf2_current[0], MWClassID.DOUBLE);
			numerical_ErrorRate *= 50;
			runTimes++;
		}
		System.out.println(runTimes);
	}
}
