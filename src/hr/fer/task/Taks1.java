package hr.fer.task;

import hr.fer.algorithm.GoldenRatio;
import hr.fer.algorithm.GradientDescent;
import hr.fer.function.impl.F3;

import java.util.Arrays;

public class Taks1 {


    public static void main(String[] args) {
        var f3 = new F3();
        var x1 = GradientDescent.gradientDescent(new GoldenRatio(1e-6, 0.5), f3, f3.staringPoint());
        System.out.println(Arrays.toString(x1));
        var x2 = GradientDescent.gradientDescent(0.01, f3, f3.staringPoint());
        System.out.println(Arrays.toString(x2));
    }
}
