package hr.fer;

import hr.fer.algorithm.GoldenRatio;
import hr.fer.algorithm.GradientDescent;
import hr.fer.function.impl.F1;
import hr.fer.function.impl.F2;
import hr.fer.function.impl.F3;
import hr.fer.function.impl.F4;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        var f = new F1();
        var x1 = GradientDescent.gradientDescent(new GoldenRatio(1e-6, 0.5), f, -1.9, 2);
        System.out.println(Arrays.toString(x1));
        var x2 = GradientDescent.gradientDescent(0.01, f, -1.9, 2);
        System.out.println(Arrays.toString(x2));
    }
}
