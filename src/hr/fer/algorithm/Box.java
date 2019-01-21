package hr.fer.algorithm;

import hr.fer.algorithm.implicit.IImplicit;
import hr.fer.function.IFunction;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by Ken on 23.11.2018..
 */
public class Box {

    private double epsilon;
    private double alpha;
    private double beta ;
    private double gamma;
    private double sigma;
    private int noi;


    public Box(double epsilon, double alpha, double beta, double gamma, double sigma) {
        this.epsilon = epsilon;
        this.alpha = alpha;
        this.beta = beta;
        this.gamma = gamma;
        this.sigma = sigma;
    }

    public void run(IFunction f, double[] x0, IImplicit[] implicits, double xD, double xG) {
        for (int i = 0; i < x0.length; i++) {
            if (x0[i] >= xD && x0[i] <= xG) {
                continue;
            } else {
                System.err.println("Explicit limitations for x0 are not satisfied.");
            }
        }

        for (var i : implicits) {
            if (!(i.check(x0[0], x0[1]))) {
                System.err.println("Implicit limitations for x0 are not satisfied.");
                break;
            }
        }

        double[] solution = new double[x0.length];
        double[] xC = new double[x0.length];
        double check;
        double[][] X = new double[2 * x0.length][x0.length];

        for (int i = 0; i < x0.length; i++) {
            xC[i] = x0[i];
        }

        for (int t = 0; t < 2 * x0.length; t++) {
            for (int i = 0; i < x0.length; i++) {
                Random r = new Random();
                double R = r.nextDouble();
                X[t][i] = xD + R * (xG - xD);
            }

            while (!(implicits[0].check(X[t][0], X[t][1]) && implicits[1].check(X[t][0], X[t][1]))) {
                for (int i = 0; i < x0.length; i++) {
                    X[t][i] = 0.5 * (X[t][i] + xC[i]);
                }
            }
            xC[0] = 0.5 * (X[t][0] + xC[0]);
            xC[1] = 0.5 * (X[t][1] + xC[1]);
        }
        int m = 0;
        do {
            m++;
            int[] hh = calculateMaxMax(f, X);
            int h = hh[0];
            int h2 = hh[1];
            xC = getCentroid(X, h, x0);
            double[] xR = new double[x0.length];

            for (int i = 0; i < x0.length; i++) {
                xR[i] = (1 + alpha) * xC[i] - alpha * X[h][i];
                if (xR[i] < xD) xR[i] = xD;
                else if (xR[i] > xG) xR[i] = xG;
            }

            while (!(implicits[0].check(xR[0], xR[1]) && implicits[1].check(xR[0], xR[1]))) {
                for (int i = 0; i < x0.length; i++) {
                    xR[i] = 0.5 * (xR[i] + xC[i]);
                }
            }

            if (f.getValue(xR) > f.getValue(X[h2])) {
                for (int i = 0; i < x0.length; i++) {
                    xR[i] = 0.5 * (xR[i] + xC[i]);
                }
            }

            for (int i = 0; i < x0.length; i++) {
                X[h][i] = xR[i];
            }
            check = calculateError(f, X, xC);
        } while (check > epsilon && m < 1000000);

        this.noi = m;
        for (int i = 0; i < x0.length; i++) solution[i] = xC[i];
        System.out.println("Box algorithm\nminimum: " + Arrays.toString(solution) + "\nvalue:" + f.getValue(solution));
    }

    private double calculateError(IFunction f, double[][] simplexPoints, double[] xC) {
        double sum = 0.0;
        int n = 0;
        for (int i = 0; i < simplexPoints.length; i++) {
            sum += Math.pow(f.getValue(simplexPoints[i]) - f.getValue(xC), 2);
            n++;
        }
        double result = sum / n;
        return Math.sqrt(result);
    }

    private double[][] shiftToBest(double[][] simplexPoints, int l, double[] x0) {
        double[][] shiftedSimplexPoints = new double[simplexPoints.length][x0.length];
        double[] b = new double[x0.length];
        for (int i = 0; i < x0.length; i++) {
            b[i] = simplexPoints[l][i];
        }
        for (int i = 0; i < simplexPoints.length; i++) {
            for (int j = 0; j < x0.length; j++) {
                shiftedSimplexPoints[i][j] = sigma * (simplexPoints[i][j] + b[j]);
            }
        }
        return shiftedSimplexPoints;
    }

    private double[] contraction(double[] xC, double[] x, double[] x0) {
        double[] xK = new double[x0.length];
        for (int i = 0; i < x0.length; i++) {
            xK[i] = (1 - beta) * xC[i] + beta * x[i];
        }
        return xK;
    }

    private double[] expansion(double[] xC, double[] xR, double[] x0) {
        double[] xE = new double[x0.length];
        for (int i = 0; i < x0.length; i++) {
            xE[i] = (1 - gamma) * xC[i] + gamma * xR[i];
        }
        return xE;
    }

    private double[] reflection(double[] xC, double[] x, double[] x0) {
        double[] xR = new double[x0.length];
        for (int i = 0; i < x0.length; i++) {
            xR[i] = (1 + alpha) * xC[i] - alpha * x[i];
        }
        return xR;
    }

    private double[] getCentroid(double[][] simplexPoints, int h, double[] x0) {
        double[] xC = new double[x0.length];
        double sum = 0.0;
        int n = 0;
        for (int i = 0; i < x0.length; i++) {
            for (int j = 0; j < simplexPoints.length; j++) {
                if (j != h) {
                    sum += simplexPoints[j][i];
                    n++;
                }
            }
            xC[i] = sum / n;
            sum = 0.0;
            n = 0;
        }
        return xC;
    }

    private int[] calculateMaxMax(IFunction f, double[][] simplexPoints) {
        int[] hh = new int[2];
        double max = Double.NEGATIVE_INFINITY;
        double max2 = Double.NEGATIVE_INFINITY;
        for (int i = 0; i < simplexPoints.length; i++) {
            double v = f.getValue(simplexPoints[i]);
            if (v > max) {
                max = v;
                if (v > max2 && v < max) {
                    max2 = v;
                }
            }
        }
        for (int i = 0; i < simplexPoints.length; i++) {
            if (max == f.getValue(simplexPoints[i])) {
                hh[0] = i;
            }
            if (max2 == f.getValue(simplexPoints[i])) {
                hh[1] = i;
            }
        }
        return hh;
    }

    private double[][] calculateSimplexPoints(double[] x0) {
        double[][] simplexPoints = new double[x0.length + 1][x0.length];
        for (int k = 0; k < x0.length; k++) {
            simplexPoints[0][k] = x0[k];
        }
        int n = 0;
        for (int i = 0; i < simplexPoints.length; i++) {
            for (int j = 0; j < x0.length; j++) {
                simplexPoints[i][j] = x0[j];
                if (n == j) {
                    simplexPoints[i][j] += alpha;
                }
            }
            n++;
        }
        return simplexPoints;
    }

    public String numberOfIterations() {
        return Integer.toString(this.noi);
    }
}
