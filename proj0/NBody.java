public class NBody {
	public static double readRadius (String filename) {
		In in = new In(filename);
		in.readInt();
		return in.readDouble();
	}
	public static Planet[] readPlanets (String filename) {
		In in = new In(filename);
		Planet planets[] = new Planet[in.readInt()];
		in.readDouble();
		for (int i = 0 ; i < planets.length ; i++) {
			planets[i] = new Planet(in.readDouble() , in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), in.readString());
		}
		return planets;
	}
	public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
  		double RADIUS = readRadius(filename);
		Planet[] planets = readPlanets(filename);
		double t = 0;
		while (t < T) {
			double xForces[] = new double[planets.length];
			double yForces[] = new double[planets.length];
			for (int i = 0 ; i < planets.length ; i++) {
				xForces[i] = planets[i].calcNetForceExertedByX(planets);
				yForces[i] = planets[i].calcNetForceExertedByY(planets);
			}
			String background = "images/starfield.jpg";	
			StdDraw.setScale(-RADIUS , RADIUS);
			StdDraw.clear();
			StdDraw.picture(0, 0, background);
			StdDraw.show();
			for (int i = 0; i < planets.length; i++) {
				planets[i].update(dt, xForces[i], yForces[i]);
				planets[i].draw();
			}
			StdDraw.enableDoubleBuffering();
			StdDraw.pause(10);
			t += dt;

		}
		StdOut.printf("%d\n", planets.length);
		StdOut.printf("%.2e\n", RADIUS);
		for (int i = 0; i < planets.length; i++) {
    			StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
			}

	}
}