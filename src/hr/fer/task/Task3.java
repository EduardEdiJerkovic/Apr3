package hr.fer.task;


import hr.fer.algorithm.Box;
import hr.fer.algorithm.implicit.IImplicit;
import hr.fer.function.impl.F1;
import hr.fer.function.impl.F2;

public class Task3 {
    public static void main(String[] args) {
       var imp1 = new IImplicit() {
           @Override
           public boolean check(double x, double y) {
               return y - x >= 0;
           }
       };

        var imp2 = new IImplicit() {
            @Override
            public boolean check(double x, double y) {
                return 2 - x >= 0;
            }
        };

        var box = new Box(1E-6, 1.3, 0.5, 2.0, 0.5);

        var f1 = new F1();
        System.out.println("Function f1");
        box.run(f1, f1.staringPoint(), new IImplicit[]{imp1, imp2}, -100, 100);

        System.out.println();

        var f2 = new F2();
        System.out.println("Function f2");
        box.run(f2, f2.staringPoint(), new IImplicit[]{imp1, imp2}, -100, 100);
    }
}
