package hr.fer;

public class GoldenRatio {
    private double epsilon;
    private double h;

    public GoldenRatio(double epsilon, double h) {
        this.epsilon = epsilon;
        this.h = h;
    }

    public static double goldenRatio(IUnimodalFunction f, Double a, Double b, Double e) {
        var c = b - k * (b - a);
        var d = a + k * (b - a);
        var fc = f.getValue(c);
        var fd = f.getValue(d);

        while ((b - a) > a) {
            if (fc >)
        }


        return 0;
    }

    public static double goldenRatio(IUnimodalFunction f, Double startPoint, Double e) {
        return 0;
    }
    /*
    Zlatni_rez(a, b, e)
    {	c = b - k * (b - a);
        d = a + k * (b - a);
        fc = f(c);
        fd = f(d);
        dokje((b - a) > e) {
        ako(fc < fd) {
            b = d;
            d = c;
            c = b - k * (b - a);
            fd = fc;
            fc = f(c);
        } inace {
            a = c;
            c = d;
            d = a + k * (b - a);
            fc = fd;
            fd = f(d);
        }
    }
}
