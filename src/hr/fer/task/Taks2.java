package hr.fer.task;

import hr.fer.algorithm.GoldenRatio;
import hr.fer.algorithm.GradientDescent;
import hr.fer.function.impl.F1;
import hr.fer.function.impl.F2;

import java.util.Arrays;

public class Taks2 {

    public static void main(String[] args) {
        var f1 = new F1();
        var f2 = new F2();
        var goldenRatio = new GoldenRatio(1e-6, 0.5);
        var x1 = GradientDescent.gradientDescent(goldenRatio, f1, f1.staringPoint());
        System.out.println(Arrays.toString(x1));
        var x2 = GradientDescent.gradientDescent(goldenRatio, f2, f2.staringPoint());
        System.out.println(Arrays.toString(x2));


    }
}
