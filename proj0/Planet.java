/**
 * Created by ZZH on 2017/2/23.
 */
public class Planet {

    double xxPos, yyPos;
    double xxVel, yyVel;
    double mass;
    String imgFileName;

    public Planet(double xP, double yP, double xV,
                  double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Planet(Planet p) {
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet body) {
        double dx = body.xxPos - xxPos;
        double dy = body.yyPos - yyPos;
        double r = Math.sqrt( dx*dx + dy*dy  );
        return r;
    }

    public double calcForceExertedBy(Planet body) {
        double r = calcDistance(body);
        double F = 6.67E-11 * mass * body.mass / (r * r);
        return F;
    }

    public double calcForceExertedByX(Planet body) {
        double dx = body.xxPos - xxPos;
        double r = calcDistance(body);
        double F = calcForceExertedBy(body);
        double Fx = F * dx / r;
        return Fx;
    }

    public double calcForceExertedByY(Planet body) {
        double dy = body.yyPos - yyPos;
        double r = calcDistance(body);
        double F = calcForceExertedBy(body);
        double Fy = F * dy / r;
        return Fy;
    }

    public boolean equals(Planet body) {
        if( xxPos==body.xxPos && yyPos==body.yyPos ) {
            return true;
        }
        return false;
    }

    public double calcNetForceExertedByX(Planet[] allBody) {
        double sumFx = 0;
        for (Planet body : allBody) {
            if (!equals(body)) {
                sumFx += calcForceExertedByX(body);
            }
        }
        return sumFx;
    }

    public double calcNetForceExertedByY(Planet[] allBody) {
        double sumFy = 0;
        for (Planet body : allBody) {
            if (!equals(body)) {
                sumFy += calcForceExertedByY(body);
            }
        }
        return sumFy;
    }

    public void update(double dt, double Fx, double Fy) {
        double ax = Fx / mass;
        double ay = Fy / mass;
        xxVel += dt * ax;
        yyVel += dt * ay;
        xxPos += dt * xxVel;
        yyPos += dt * yyVel;
    }

    public void draw() {
        StdDraw.picture(xxPos/1E9 + 256, yyPos/1E9 + 256, imgFileName);
    }

}
