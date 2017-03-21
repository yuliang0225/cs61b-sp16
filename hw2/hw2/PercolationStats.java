package hw2;
import edu.princeton.cs.introcs.*;

public class PercolationStats {

    private int N, T;
    private double[] x;
    private double u, sigma, confidenceL, confidenceH;

    // perform T independent experiments on an N-by-N grid
    public PercolationStats(int N, int T) {
        this.N = N;
        this.T = T;
        x = new double[T];

        int[] order = new int[N*N];
        for (int i=0; i<N*N; i++) {
            order[i] = i;
        }

        for (int i=0; i<T; i++) {
            StdRandom.shuffle(order);
            int numberOfOpenSites = 0;
            Percolation pc = new Percolation(N);
            for (int j=0; j<N*N && !pc.percolates(); j++) {
                pc.open(order[j]/N, order[j]%N);
                numberOfOpenSites++;
            }
            x[i] = (double)numberOfOpenSites / (N*N);
        }

        u = StdStats.mean(x);
        sigma = StdStats.stddev(x);
        confidenceL = u - 1.96*sigma/Math.sqrt(T);
        confidenceH = u + 1.96*sigma/Math.sqrt(T);
    }

    // sample mean of percolation threshold
    public double mean() {
        return u;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return sigma;
    }

    // low endpoint of 95% confidence interval
    public double confidenceLow() {
        return confidenceL;
    }

    // high endpoint of 95% confidence interval
    public double confidenceHigh() {
        return confidenceH;
    }


    public static void main(String[] args) {
        PercolationStats ps = new PercolationStats(20, 50);
        System.out.printf("sample mean: %f\nsample standard deviation: %f\nconfidence: (%f,%f)",
        ps.mean(), ps.stddev(), ps.confidenceLow(), ps.confidenceHigh());
    }
}
