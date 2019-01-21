package hr.fer.function.impl;

import hr.fer.function.IFunction;

public class F2 implements IFunction {
    @Override
    public double getValue(double... x) {
        return Math.pow(x[0] - 4, 2) + 4 * Math.pow(x[1] - 2, 2);
    }

    @Override
    public double[] getGrad(double... x) {
        var result = new double[x.length];
        result[0] = 2 * (x[0] - 4);
        result[1] = 8 * (x[1] - 2);
        return result;
    }

    @Override
    public double[] staringPoint(double... x) {
        return new double[]{0.1, 0.3};
    }
}
