package hr.fer.algorithm;

import hr.fer.function.IUnimodalFunction;

public class GoldenRatio {
    private double epsilon;
    private double h;
    private double k = 0.5 * (Math.sqrt(5) - 1);

    public GoldenRatio(double epsilon, double h) {
        this.epsilon = epsilon;
        this.h = h;
    }

    public double goldenRatio(IUnimodalFunction f, double startPoint) {
        var points = unimodal(f, startPoint);
        return goldenRatio(f, points[0], points[1]);
    }

    public double goldenRatio(IUnimodalFunction f, double a, double b) {
        var c = b - k * (b - a);
        var d = a + k * (b - a);
        var fc = f.getValue(c);
        var fd = f.getValue(d);

        while ((b - a) > epsilon) {
            if (fc < fd) {
                b = d;
                d = c;
                c = b - k * (b - a);
                fd = fc;
                fc = f.getValue(c);
            } else {
                a = c;
                c = d;
                d = a + k * (b - a);
                fc = fd;
                fd = f.getValue(d);
            }
        }
        return (a + b) / 2;
    }

    private double[] unimodal(IUnimodalFunction f, double point) {
        var l = point - h;
        var r = point + h;
        var m = point;
        var step = 1;

        var fm = f.getValue(point);
        var fl = f.getValue(l);
        var fr = f.getValue(r);

        if (fm < fr && fm < fl) {
            return new double[]{l, r};
        } else if (fm > fr) {
            while (true) {
                l = m;
                m = r;
                fm = fr;
                step *= 2;
                r = point + h * step;
                fr = f.getValue(r);
                if (!(fm > fr)) {
                    break;
                }
            }
        } else {
            while (true) {
                r = m;
                m = l;
                fm = fl;
                step *= 2;
                l = point - h * step;
                fl = f.getValue(l);
                if (!(fm > fl)) {
                    break;
                }
            }
        }
        return new double[]{l, r};
    }
}
