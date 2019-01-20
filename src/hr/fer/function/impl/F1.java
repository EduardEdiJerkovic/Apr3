package hr.fer.function.impl;

import hr.fer.function.IFunction;

public class F1 implements IFunction {

    @Override
    public double getValue(double... x) {
        return 100 * Math.pow(x[1] + Math.pow(x[0], 2), 2) + Math.pow(1 - x[0], 2);
    }

    @Override
    public double[] getGrad(double... x) {
        return null;
    }
}
