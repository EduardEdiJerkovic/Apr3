package hr.fer.function.impl;

import hr.fer.function.IFunction;
import hr.fer.function.IUnimodalFunction;

public class UnimodalEncapsulation implements IUnimodalFunction {
    private IFunction f;
    private double[] point;

    public UnimodalEncapsulation(IFunction f, double[] point) {
        this.f = f;
        this.point = point;
    }

    public IFunction getF() {
        return f;
    }

    public double[] getPoint() {
        return point.clone();
    }

    public void setPoint(double[] point) {
        this.point = point.clone();
    }

    @Override
    public double getValue(double x) {
        var newPoint = new double[point.length];
        var grad = f.getGrad(point);
        for (int i = 0; i < point.length; ++i) {
            newPoint[i] = point[i] + grad[i] * x;
        }
        return f.getValue(newPoint);
    }
}
