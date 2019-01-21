package hr.fer.function.impl;

import hr.fer.function.IFunction;

public class F4 implements IFunction {
    @Override
    public double getValue(double... x) {
        return Math.pow(x[0] - 3, 2) + Math.pow(x[1], 2);
    }

    @Override
    public double[] getGrad(double... x) {
        var result = new double[x.length];
        result[0] = 2 * (x[0] - 3);
        result[1] = 2 * x[1];
        return result;
    }

    @Override
    public double[] staringPoint(double... x) {
        return new double[]{0, 0};
    }
}
