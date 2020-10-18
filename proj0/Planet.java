public class Planet {
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
    public String imgFileName;
    private static final double g = 6.67e-11;
    public Planet (double xP, double yP, double xV, double yV, double m, String img){
    	xxPos = xP;
    	yyPos = yP;
    	xxVel = xV;
    	yyVel = yV;
    	mass = m;
    	imgFileName = img;
    }
    public Planet (Planet p){
    	xxPos = p.xxPos;
    	yyPos = p.yyPos;
    	xxVel = p.xxVel;
    	yyVel = p.yyVel;
    	mass  = p.mass;
    	imgFileName = p.imgFileName;
    }
    public double calcDistance (Planet p){
    	double sum = (p.xxPos - this.xxPos)*(p.xxPos - this.xxPos) + (p.yyPos - this.yyPos)*(p.yyPos - this.yyPos);
    	return Math.sqrt(sum);
    }
    public double calcForceExertedBy (Planet p){
    	double r = calcDistance(p);
    	return (g * p.mass * this.mass)/(r*r);
    }
    public double calcForceExertedByX (Planet p){
    	double r = calcDistance(p);
    	double f = calcForceExertedBy(p);
    	return f*(p.xxPos-this.xxPos)/r;
    }
    public double calcForceExertedByY (Planet p){
    	double r = calcDistance(p);
    	double f = calcForceExertedBy(p);
    	return f*(p.yyPos-this.yyPos)/r;
    }
    public double calcNetForceExertedByX (Planet[] p){
    	double res = 0;
    	for (int i = 0 ; i < p.length ; i++) {
    		if (this.equals(p[i])) {
    			continue;
    		}
    		else {
    			res += calcForceExertedByX(p[i]);
    		}
    	}  
    	return res;
    }
    public double calcNetForceExertedByY (Planet[] p){
    	double res = 0;
    	for (int i = 0 ; i < p.length ; i++) {
    		if (this.equals(p[i])) {
    			continue;
    		}
    		else {
    			res += calcForceExertedByY(p[i]);
    		}
    	}  
    	return res; 	
    }
    public void update (double dt, double fx, double fy) {
    	double ax = fx/mass;
    	double ay = fy/mass;
    	xxVel = xxVel + dt * ax;
    	yyVel = yyVel + dt * ay;
    	xxPos = xxPos + dt * xxVel;
    	yyPos = yyPos + dt * yyVel;

    }
    public void draw () {
    	StdDraw.picture(xxPos, yyPos, "images/"+ imgFileName);
		StdDraw.show();
    }
}