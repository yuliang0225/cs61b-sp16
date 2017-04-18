import edu.princeton.cs.algs4.Picture;
import java.awt.Color;
import java.util.Collections;


public class SeamCarver {

    private static final boolean VERTICAL = true;
    private static final boolean HORIZONTAL = false;

    private Picture picture;
    private int width, height;
    private double[][] energy;

    private SeamRemover seamremover;

    public SeamCarver(Picture picture) {
        this.picture = picture;
        width = picture.width();
        height = picture.height();

        energy = new double[width][height];
        for (int i=0; i<width; i++) {
            for (int j=0; j<height; j++) {
                energy[i][j] = private_energy(i, j);
            }
        }

        seamremover = new SeamRemover();
    }


    private double private_energy(int x, int y) {
        int w = width(), h = height();
        Color x1 = picture.get((x+1)%w,y), x2 = picture.get((x+w-1)%w,y);
        int rx = x1.getRed() - x2.getRed();
        int gx = x1.getGreen() - x2.getGreen();
        int bx = x1.getBlue() - x2.getBlue();
        int d2x = rx*rx + gx*gx + bx*bx;

        Color y1 = picture.get(x,(y+1)%h), y2 = picture.get(x,(y+h-1)%h);
        int ry = y1.getRed() - y2.getRed();
        int gy = y1.getGreen() - y2.getGreen();
        int by = y1.getBlue() - y2.getBlue();
        int d2y = ry*ry + gy*gy + by*by;

        return d2x + d2y;
    }


     // current picture
    public Picture picture() {
        return picture;
    }


    // width of current picture
    public int width() {
        return width;
    }


    // height of current picture
    public int height() {
        return height;
    }


    // energy of pixel at column x and row y
    public double energy(int x, int y) {
        return energy[x][y];
    }


    private double[][] energy() {
        return energy;
    }


    private double[][] energy_transpose() {
        int width = width(), height = height();
        double[][] transposeEnergy = new double[height][width];

        for (int i=0; i<width; i++) {
            for (int j=0; j<height; j++) {
                transposeEnergy[j][i] = energy[i][j];
            }
        }
        return transposeEnergy;
    }


    private void findMinCost(double[][] M, int[][] D,
        double[][] energy, int width, int height, int i, int j) {

        M[i][j] = energy[i][j];

        if (j == 0) {
            return;
        }

        double min = Double.MAX_VALUE;
        int label = Integer.MAX_VALUE;
        if (i == 0) {
            min = Math.min(M[i][j-1], M[i+1][j-1]);
            label = min==M[i][j-1] ? i : i+1;
            // label = M[i][j-1] < M[i+1][j-1] ? i : i+1;
        } else if (i == width-1) {
            min = Math.min(M[i-1][j-1], M[i][j-1]);
            label = min==M[i-1][j-1] ? i-1 : i;
            // label = M[i-1][j-1] < M[i][j-1] ? i-1 : i;
        } else {
            min = Math.min(Math.min(M[i-1][j-1], M[i][j-1]), M[i+1][j-1]);
            label = min==M[i-1][j-1] ? i-1 : (min==M[i][j-1] ? i : i+1);
            // label = M[i-1][j-1] < M[i][j-1] ? (M[i-1][j-1] < M[i+1][j-1] ? i-1 : i+1) : (M[i][j-1] < M[i+1][j-1] ? i : i+1);
        }

        M[i][j] += min;
        D[i][j] = label;
    }


    private int[] findSeam(boolean direction) {
        double[][] energy = null;
        int width = 0, height = 0;

        if (direction == VERTICAL) {
            energy = energy();
            width = width();
            height = height();
        } else {
            energy = energy_transpose();
            width = height();
            height = width();
        }

        double[][] M = new double[width][height];
        int[][] D = new int[width][height];
        for(int j=0; j<height; j++) {
            for (int i=0; i<width; i++) {
                findMinCost(M, D, energy, width, height, i, j);
            }
        }

        double min = M[0][height-1];
        int[] path = new int[height];
        for (int i=0; i<width; i++) {
            if (M[i][height-1] < min) {
                min = M[i][height-1];
                path[height-1] = i;
            }
        }

        for (int j=height-1; j>0; j--) {
            path[j-1] = D[path[j]][j];
        }

        return path;
    }


    // sequence of indices for horizontal seam
    public int[] findHorizontalSeam() {
        return findSeam(HORIZONTAL);
    }


    // sequence of indices for vertical seam
    public int[] findVerticalSeam() {
        return findSeam(VERTICAL);
    }

    // remove horizontal seam from picture
    public void removeHorizontalSeam(int[] seam) {
        picture = seamremover.removeHorizontalSeam(picture, seam);
        width = picture.width();
        height = picture.height();

        energy = new double[width][height];
        for (int i=0; i<width; i++) {
            for (int j=0; j<height; j++) {
                energy[i][j] = private_energy(i, j);
            }
        }
    }


    // remove vertical seam from picture
    public void removeVerticalSeam(int[] seam) {
        picture = seamremover.removeVerticalSeam(picture, seam);
        
        width = picture.width();
        height = picture.height();

        energy = new double[width][height];
        for (int i=0; i<width; i++) {
            for (int j=0; j<height; j++) {
                energy[i][j] = private_energy(i, j);
            }
        }
    }


    public static void main(String[] args) {
        Picture picture = new Picture("images/6x5.png");
        SeamCarver carver = new SeamCarver(picture);
        // carver.findVerticalSeam();
    }

}
