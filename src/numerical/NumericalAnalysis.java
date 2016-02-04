package numerical;

import java.util.*;

import com.mathworks.toolbox.javabuilder.*;
import convolution.*;


public class NumericalAnalysis {
	
	static boolean firstRun = true; 
	public static double[][] cdf1_current;
	public static double[][] cdf2_current;
	static MWNumericArray cdf1MW_current;
	static MWNumericArray cdf2MW_current;
	static MWNumericArray cdf1MW_once;
	static MWNumericArray cdf2MW_once;
	static MWNumericArray timesMW;
	
	public static List<Object> run(double p_1_c1, double p_1_c2, int runTimes) throws MWException {		
	
	double[] cdf1_once = {(double)1 - p_1_c1, p_1_c1};
	double[] cdf2_once = {(double)1 - p_1_c2, p_1_c2};
	
	if (firstRun) {
		cdf1MW_once = new MWNumericArray(cdf1_once, MWClassID.DOUBLE);
		cdf2MW_once = new MWNumericArray(cdf2_once, MWClassID.DOUBLE);
		cdf1MW_current = new MWNumericArray(1, MWClassID.DOUBLE);
		cdf2MW_current = new MWNumericArray(1, MWClassID.DOUBLE);
		timesMW = new MWNumericArray(0, MWClassID.INT32);
		firstRun = false;
	}else {
		cdf1MW_current = new MWNumericArray(cdf1_current[0], MWClassID.DOUBLE);
		cdf2MW_current = new MWNumericArray(cdf2_current[0], MWClassID.DOUBLE);
		timesMW = new MWNumericArray(runTimes - cdf1_current[0].length + 1, MWClassID.INT32);
	}
	
	Convolution conv = new Convolution();	
	Object[] output1 = conv.convolution(1, cdf1MW_current ,cdf1MW_once, timesMW);
	Object[] output2 = conv.convolution(1, cdf2MW_current ,cdf2MW_once, timesMW);
	cdf1_current = (double[][])(((MWArray)output1[0]).toArray());
	cdf2_current = (double[][])(((MWArray)output2[0]).toArray());
	
	int decisionValue= 0;
	int maxIndex = -1;
	if (runTimes != 0) {
		for (int i = 0; i <= runTimes; i++) {
			if (cdf1_current[0][i] > cdf1_current[0][i + 1]) {		
				maxIndex = i;
				break;
			}
		}
		for (int i = maxIndex; i <= runTimes; i++) {	
			if (cdf1_current[0][i] <= cdf2_current[0][i]) {			// == decisionValue -> treat as coin2
				decisionValue = i;
				break;
			}
		}	
	}
	System.out.println("The decision value is: " + (decisionValue));
	
	double numerical_ErrorRate = 0;
	for (int i = 0; i <= runTimes; i++) {
		if (i < decisionValue) {
			numerical_ErrorRate += cdf2_current[0][i];
		}else {
			numerical_ErrorRate += cdf1_current[0][i];
		}
	}
	numerical_ErrorRate *= 50;		// errorProbability = erroeProbability / 2 * 100;
	System.out.println("The numerical error rate is: " + numerical_ErrorRate + "%");
	
	List<Object> numerical_Result = new ArrayList<Object>();
	numerical_Result.add(numerical_ErrorRate);
	numerical_Result.add(decisionValue);
	return numerical_Result;
	}
}
