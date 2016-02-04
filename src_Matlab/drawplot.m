function drawplot(y_cdf1, y_cdf2, x_runtimes, y_numerical_errorRate, y_simulation_errorRate)

[m, n] = size(y_cdf1);
x_sum = 0:1:n - 1; 

subplot(2,1,1);
plot(x_sum, 100 * y_cdf1, x_sum, 100 * y_cdf2);
xlabel('Number of 1''s');
ylabel('Probability (%)');
title('Cumulative Distribution Function');
legend('coin with lower chance of face 1', 'coin with higher chance of face 1');

subplot(2,1,2);
plot(x_runtimes, y_numerical_errorRate, x_runtimes, y_simulation_errorRate);
xlabel('Number of Runtimes');
ylabel('Error Rate (%)');
title('Error Rate (%) vs Number of Runtimes');
legend('Numerical Error Rate', 'Simulation Error Rate');

end