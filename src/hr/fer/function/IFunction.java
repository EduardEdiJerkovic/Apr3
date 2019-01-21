package hr.fer.function;

public interface IFunction {
    double getValue(double... x);
    double[] getGrad(double... x);
    double[] staringPoint(double... x);
}
