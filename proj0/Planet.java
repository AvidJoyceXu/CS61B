public class Planet{
    public static final double G = 6.67e-11;
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    public Planet(double xP,double yP,double xV,
        double yV,double m,String img){
            xxPos = xP;
            yyPos = yP;
            xxVel = xV;
            yyVel = yV;
            mass = m;
            imgFileName =img;
        }
    public Planet(Planet b){
        xxPos = b.xxPos;
        yyPos = b.yyPos;
        xxVel = b.xxVel;
        yyVel = b.yyVel;
        mass = b.mass;
        imgFileName = b.imgFileName;
    }
    public double calcDistance(Planet b){
        return Math.sqrt(Math.pow((xxPos-b.xxPos),2)+Math.pow((yyPos-b.yyPos),2));
    }
    public double calcForceExertedBy(Planet b){
        return G * mass * b.mass / Math.pow(calcDistance(b),2);
    }
    public double calcForceExertedByX(Planet b){
        return calcForceExertedBy(b) * (b.xxPos - xxPos)/calcDistance(b);
    }
    public double calcForceExertedByY(Planet b){
        return calcForceExertedBy(b) * (b.yyPos - yyPos)/calcDistance(b);
    }
    public double calcNetForceExertedByX(Planet[] allPlanets){
        double ret = 0;
        for(Planet p:allPlanets){
            if(! equals(p)){
                ret += calcForceExertedByX(p);
            }
        }
        return ret;
    }
    public double calcNetForceExertedByY(Planet[] allPlanets){
        double ret = 0;
        for(Planet p:allPlanets){
            if(! equals(p)){
                ret += calcForceExertedByY(p);
            }
        }
        return ret;
    }
    public void update(double t,double Fxx,double Fyy){
        double axx = Fxx/mass;
        double ayy = Fyy/mass;
        xxVel += t * axx;
        yyVel += t * ayy;
        xxPos += t * xxVel;
        yyPos += t * yyVel;
    }
    public void draw(){
        StdDraw.picture(xxPos,yyPos,"images/"+imgFileName);
    }
}
