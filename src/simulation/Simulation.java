package simulation;

import java.util.*;

public class Simulation {
	
	static Random rand = new Random();
	
	public static double run(double p_1_c1, double p_1_c2, int runTimes, int simulationTimes, int decisionValue) {
		int errorTimes = 0;
		for (int i = 0; i < simulationTimes; i++) {
			Coin coin;
			if (rand.nextDouble() < 0.5) {
				coin = new Coin(1, 0);
			}else {
				coin = new Coin(2, 0);
			}
			if (coin.type == 1) {
				simulate(coin, runTimes, p_1_c1);
			}else {
				simulate(coin, runTimes, p_1_c2);
			}
			int type = (coin.sum < decisionValue)? 1: 2;
			if (type != coin.type) {
				errorTimes++;
			}
		}	
		double simulation_ErrorRate = (double)errorTimes / simulationTimes * 100;
		System.out.println("The simulized error rate is: " + simulation_ErrorRate + "%");
		return simulation_ErrorRate;
	}	
	
	private static void simulate(Coin coin, int runTimes, double p_1_c) {
		for (int i = 0; i < runTimes; i++) {
			if (rand.nextDouble() < p_1_c) {
				coin.sum++;
			}
		}
	}
}
