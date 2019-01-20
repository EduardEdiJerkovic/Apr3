package hr.fer;

import hr.fer.algorithm.GoldenRatio;
import hr.fer.algorithm.GradientDescent;
import hr.fer.function.impl.F2;
import hr.fer.function.impl.F3;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        var f = new F2();
        var x1 = GradientDescent.gradientDescent(new GoldenRatio(1e-6, 0.5), f, 0.1, 0.3);
        System.out.println(Arrays.toString(x1));
        var x2 = GradientDescent.gradientDescent(0.001, f, 0.1, 0.3);
        System.out.println(Arrays.toString(x2));
    }
}
