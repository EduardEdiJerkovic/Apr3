package hr.fer.function.impl;

import hr.fer.function.IFunction;

public class F1 implements IFunction {

    @Override
    public double getValue(double... x) {
        return 100 * Math.pow(x[1] - Math.pow(x[0], 2), 2) + Math.pow(1 - x[0], 2);
    }

    @Override
    public double[] getGrad(double... x) {
        var result = new double[x.length];
        result[0] = 2 * (200 * (Math.pow(x[0], 3) - x[0] * x[1]) + x[0] - 1);
        result[1] = 200 * (x[1] - Math.pow(x[0], 2));
        return result;
    }

    @Override
    public double[] staringPoint(double... x) {
        return new double[]{1.9, 2};
    }
}
