package hr.fer.algorithm;

import hr.fer.function.IFunction;
import hr.fer.function.impl.UnimodalEncapsulation;

public class GradientDescent {

    private static double epsilon = 1e-6;

    public static double[] gradientDescent(GoldenRatio gRatio, IFunction f, double... x) {
        var xx = x.clone();
        var unimodalF = new UnimodalEncapsulation(f, xx);
        double[] grad;
        double l;
        while (true) {
            l = gRatio.goldenRatio(unimodalF, 0.0);
            grad = unimodalF.getF().getGrad(xx);
            for (int i = 0; i < xx.length; ++i) {
                xx[i] += l * grad[i];
            }
            unimodalF.setPoint(xx);

            if (gradEuclidSum(grad) <= epsilon) {
                break;
            }
        }
        return xx;
    }

    public static double[] gradientDescent(double step, IFunction f, double... x) {
        var xx = x.clone();
        var unimodalF = new UnimodalEncapsulation(f, xx);
        double[] grad;
        while (true) {
            grad = unimodalF.getF().getGrad(xx);
            for (int i = 0; i < xx.length; ++i) {
                xx[i] -= step * grad[i];
            }
            unimodalF.setPoint(xx);

            if (gradEuclidSum(grad) <= epsilon) {
                break;
            }
        }
        return xx;
    }

    private static double gradEuclidSum(double[] grad) {
        double gSum = 0;
        for (var g : grad) {
            gSum += Math.pow(g, 2);
        }
        return Math.sqrt(gSum);
    }
}
