public class NBody{

    public String fileName;

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double universeRadius = readRadius(filename);
        Planet[] planetsArray = readPlanets(filename);
        StdDraw.setScale(-universeRadius,universeRadius);
        StdDraw.clear();
        StdDraw.picture(T,dt,"images/starfield.jpg");
        for (int i = 0; i < planetsArray.length ; i++) {
            planetsArray[i].draw();
        }
        StdDraw.enableDoubleBuffering();
        double time = 0;
        while(time < T){
            Double[] xForces = new Double[planetsArray.length];
            Double[] yForces = new Double[planetsArray.length];
            for (int i = 0; i < planetsArray.length ; i++) {

                    xForces[i] = planetsArray[i].calcNetForceExertedByX(planetsArray);
                    yForces[i] = planetsArray[i].calcNetForceExertedByY(planetsArray);

            }
            for (int i = 0; i < planetsArray.length ; i++) {
                planetsArray[i].update(dt, xForces[i], yForces[i]);
            }
            StdDraw.picture(T,dt,"images/starfield.jpg");
            for (int i = 0; i < planetsArray.length ; i++) {
                planetsArray[i].draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
            time += dt;
        }
    }

    public static double readRadius(String fileName){
        In in = new In(fileName);
        int planets = in.readInt();
        double radius = in.readDouble();
        return radius;
    }

    public static Planet[] readPlanets(String fileName){
        In in = new In(fileName);
        int planetsNum = in.readInt();
        double radius = in.readDouble();
        Planet[] planets = new Planet[planetsNum];
        for (int i = 0; i < planetsNum; i++) {
            double xxPos = in.readDouble();
            double yyPos = in.readDouble();
            double xxVel = in.readDouble();
            double yyVel = in.readDouble();
            double mass = in.readDouble();
            String img = in.readString();
            planets[i] = new Planet(xxPos,yyPos,xxVel,yyVel,mass,img);
        }
        return planets;
    }
}