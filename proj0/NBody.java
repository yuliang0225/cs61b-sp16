/**
 * Created by ZZH on 2017/2/23.
 */
public class NBody {

    public static double readRadius(String filename) {
        In in = new In(filename);
        int N = in.readInt();
        double R = in.readDouble();
        return R;
    }

    public static Planet[] readPlanets(String filename) {
        In in = new In(filename);
        int N = in.readInt();
        double R = in.readDouble();

        Planet[] allPlanets = new Planet[N];

        for(int i=0; i<N; i++) {
            double xxPos = in.readDouble();
            double yyPos = in.readDouble();
            double xxVel = in.readDouble();
            double yyVel = in.readDouble();
            double mass = in.readDouble();
            String imgFileName = in.readString();
            allPlanets[i] = new Planet(xxPos, yyPos, xxVel, yyVel, mass, "/images/"+imgFileName);
        }

        return allPlanets;
    }

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];

        Planet[] allPlanets = NBody.readPlanets(filename);
        StdDraw.setScale(0, 512);

        for(double t=0; t<T; t+=dt) {
            StdDraw.clear();
            StdDraw.picture(256, 256, "images/starfield.jpg");

            double xForce[] = new double[allPlanets.length];
            double yForce[] = new double[allPlanets.length];

            for (int i = 0; i < allPlanets.length; i++) {
                xForce[i] = allPlanets[i].calcNetForceExertedByX(allPlanets);
                yForce[i] = allPlanets[i].calcNetForceExertedByY(allPlanets);
            }

            for (int i = 0; i < allPlanets.length; i++) {
                allPlanets[i].update(dt, xForce[i], yForce[i]);
                allPlanets[i].draw();
            }
            StdDraw.show(10);
        }
    }

}