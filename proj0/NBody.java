public class NBody {
    public static double readRadius(String f){
        In in = new In(f);
        in.readInt();
        return in.readDouble();
    }
    public static Planet[] readPlanets(String f){
        In in = new In(f);
        int n = in.readInt();
        in.readDouble();
        Planet[] ret = new Planet[n];
        for(int i=0;i<n;i++){
            ret[i] = new Planet(in.readDouble(),in.readDouble(),
            in.readDouble(),in.readDouble(),in.readDouble(),in.readString());
        }
        return ret;
    }
    public static void main(String[] args){
        double T = Double.parseDouble(args[0]),dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = readRadius(filename);
        Planet[] universe = readPlanets(filename);
        StdDraw.enableDoubleBuffering();
        StdDraw.setScale(-radius, radius);
        StdDraw.clear();
        for(Planet p:universe){
            p.draw();
        }
        double t = 0;
        while(t<T){
            int n = universe.length;
            double[] xForces = new double[n],yForces = new double[n];
            for(int i=0;i<n;i++){
                xForces[i] = universe[i].calcNetForceExertedByX(universe);
                yForces[i] = universe[i].calcNetForceExertedByY(universe);
            }
            for(int i=0;i<n;i++){
                universe[i].update(dt,xForces[i],yForces[i]);
            }
            StdDraw.picture(0,0,"images/starfield.jpg");
            for(Planet p:universe){
                p.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
            StdDraw.show();
        }
        StdOut.printf("%d\n", universe.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < universe.length; i++) {
        StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                  universe[i].xxPos, universe[i].yyPos, universe[i].xxVel,
                  universe[i].yyVel, universe[i].mass, universe[i].imgFileName);   
}
        
    }
}
